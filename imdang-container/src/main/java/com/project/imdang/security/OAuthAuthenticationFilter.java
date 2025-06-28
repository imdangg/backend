package com.project.imdang.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.Map;

import static com.project.imdang.common.application.constant.RequestPath.LOGIN;

@RequiredArgsConstructor
public class OAuthAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // false: 실행
        String servletPath = request.getServletPath();
        return !servletPath.startsWith(LOGIN);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Map<String, Object> requestBody = parseRequestBodyToMap(request);
        OAuthProvider oAuthProvider = (OAuthProvider) requestBody.get("provider");
        String identifier = (String) requestBody.get("token");
        OAuthAuthenticationToken oAuthAuthenticationToken = new OAuthAuthenticationToken(oAuthProvider, identifier);

        try {
            Authentication authenticatedToken = authenticationManager.authenticate(oAuthAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
        } catch (Exception e) {
            // TODO
            throw new BadCredentialsException("Invalid Token!");
        }
        filterChain.doFilter(request, response);
    }

    private Map<String, Object> parseRequestBodyToMap(HttpServletRequest request) throws IOException {
        ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
        byte[] body = wrapper.getContentAsByteArray();
        return objectMapper.readValue(body, Map.class);
    }
}
