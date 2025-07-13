package com.project.imdang.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.application.response.Response;
import com.project.imdang.filter.CachingFilter;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.handler.auth.TokenHandler;
import com.project.imdang.member.domain.ports.output.client.OAuthClientHandler;
import com.project.imdang.security.AccessTokenValidateFilter;
import com.project.imdang.security.CustomAccessDeniedHandler;
import com.project.imdang.security.CustomAuthenticationEntryPoint;
import com.project.imdang.security.OAuthAuthenticationFilter;
import com.project.imdang.security.OAuthAuthenticationProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

import static com.project.imdang.common.application.constant.Header.AUTHORIZATION;
import static com.project.imdang.common.application.constant.RequestPath.LOGIN;
import static com.project.imdang.common.application.constant.RequestPath.REISSUE;
import static com.project.imdang.common.application.constant.RequestPath.SWAGGER_DOC;
import static com.project.imdang.common.application.constant.RequestPath.SWAGGER_RESOURCE;
import static com.project.imdang.common.application.constant.RequestPath.SWAGGER_UI;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // TODO - 개선
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final OAuthClientHandler oAuthClientHandler;
    private final TokenHandler tokenHandler;

    private final ObjectMapper objectMapper;

    @Bean
    public AuthenticationManager authenticationManager() {
        OAuthAuthenticationProvider oAuthAuthenticationProvider
                = new OAuthAuthenticationProvider(memberDomainService, memberHelper, oAuthClientHandler, tokenHandler);
        return new ProviderManager(oAuthAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // https만 허용
//                .requiresChannel(registry -> registry.anyRequest().requiresSecure())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                // oauth2 - FE에서 수행
//                .oauth2Login()
                .cors(corsConfigurer -> corsConfigurer
                        .configurationSource(request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                            corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                            corsConfiguration.setAllowCredentials(true);    // 쿠키 & 인증 포함 허용
                            // todo - 7일로 변경
                            corsConfiguration.setMaxAge(3600L);
                            // BE → FE
                            corsConfiguration.setExposedHeaders(Arrays.asList(AUTHORIZATION));
                            return corsConfiguration;
                        }))
                // CSRF 활성화
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new AccessTokenValidateFilter(tokenHandler), BasicAuthenticationFilter.class)
                .addFilterBefore(new OAuthAuthenticationFilter(authenticationManager(), objectMapper), BasicAuthenticationFilter.class)
                .addFilterBefore(new CachingFilter(), SecurityContextHolderFilter.class)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(LOGIN, "/login-test", REISSUE, SWAGGER_RESOURCE, SWAGGER_UI, SWAGGER_DOC).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler))
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessHandler((request, response, authentication) -> {
                            ApiResponse<Void> success = ApiResponse.success(null);
                            Response.json(response, HttpServletResponse.SC_OK, objectMapper.writeValueAsString(success));
                }))
                .build();
    }
}
