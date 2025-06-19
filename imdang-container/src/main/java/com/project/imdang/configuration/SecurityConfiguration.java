package com.project.imdang.configuration;

import com.project.imdang.jwt.JwtAuthFilter;
import com.project.imdang.jwt.JwtAuthenticationEntryPoint;
import com.project.imdang.member.persistence.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.function.Supplier;

@Configuration("securityConfiguration")
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {

    @Value("${security.allowed-ip}")
    private String allowedIp;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/members", "/members/info").permitAll()
//                        .access(this::hasIpAddress)
                        .requestMatchers("/auth/kakao", "/auth/google", "/auth/apple", "/auth/reissue",
                                "/swagger-resources/**", "/swagger-ui/**","/v3/api-docs/**", "/apartment-complexes").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        log.info("Local Request[IP : {}] is requested", object.getRequest().getRemoteAddr());
        return new AuthorizationDecision(allowedIp.matches(object.getRequest().getRemoteAddr()));
    }
}
