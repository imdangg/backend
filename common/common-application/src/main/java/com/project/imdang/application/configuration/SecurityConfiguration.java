package com.project.imdang.application.configuration;

import com.project.imdang.application.security.JwtAuthenticationEntryPoint;
import com.project.imdang.application.security.JwtAuthFilter;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import java.util.function.Supplier;

@Configuration("securityConfiguration")
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {

    @Value("${security.allowed-ip}")
    private String ALLOWED_IP_ADDRESS_6;
    private final JwtTokenProvider jwtTokenUtil;
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
                        .requestMatchers("/members").access(this::hasIpAddress)
                        .requestMatchers("/auth/kakao", "/auth/google", "/auth/apple",
                                "/swagger-resources/**", "/swagger-ui/**","/v3/api-docs/**", "/apartment-complexes").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        log.info("Local Request[IP : {}] is requested", object.getRequest().getRemoteAddr());
        return new AuthorizationDecision(ALLOWED_IP_ADDRESS_6.matches(object.getRequest().getRemoteAddr()));
    }
}
