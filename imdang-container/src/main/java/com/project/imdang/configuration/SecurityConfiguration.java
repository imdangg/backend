package com.project.imdang.configuration;

import com.project.imdang.jwt.JwtGenerateFilter;
import com.project.imdang.jwt.JwtValidateFilter;
import com.project.imdang.member.persistence.provider.JwtTokenProvider;
import com.project.imdang.security.CustomAccessDeniedHandler;
import com.project.imdang.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {

    @Value("${security.allowed-ip}")
    private String allowedIp;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

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
//                .csrf(csrfConfigurer -> csrfConfigurer
                        // TODO - CsrfTokenRepository: Session -> DB OR Cookie로 변경
//                        .disable()
//                )
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 인증 후 실행
                .addFilterAfter(new JwtGenerateFilter(), BasicAuthenticationFilter.class) // UsernamePasswordAuthenticationFilter.class
                // 인증 전 실행
                .addFilterBefore(new JwtValidateFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/members", "/members/info").permitAll()
//                        .access(this::hasIpAddress)
                        .requestMatchers("/auth/kakao", "/auth/google", "/auth/apple", "/auth/reissue",
                                "/swagger-resources/**", "/swagger-ui/**","/v3/api-docs/**", "/apartment-complexes").permitAll()
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
