package com.project.imdang.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

import static com.project.imdang.common.application.constant.Header.AUTHORIZATION;
import static com.project.imdang.common.application.constant.RequestPath.DETAIL_MEMBER;
import static com.project.imdang.common.application.constant.RequestPath.LIST_MEMBER;
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

    @Value("${security.allowed-ip}")
    private String allowedIp;
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
                // TODO - CsrfTokenRepository: Session -> DB OR Cookie로 변경
                 .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new AccessTokenValidateFilter(tokenHandler), BasicAuthenticationFilter.class)
                .addFilterBefore(new OAuthAuthenticationFilter(authenticationManager(), objectMapper), BasicAuthenticationFilter.class)
                .addFilterBefore(new CachingFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(LIST_MEMBER, DETAIL_MEMBER).permitAll()
//                        .access(this::hasIpAddress)
                        .requestMatchers(LOGIN, REISSUE, SWAGGER_RESOURCE, SWAGGER_UI, SWAGGER_DOC).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler))
                .build();
    }

    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        log.info("Local Request[IP : {}] is requested", object.getRequest().getRemoteAddr());
        return new AuthorizationDecision(allowedIp.matches(object.getRequest().getRemoteAddr()));
    }
}
