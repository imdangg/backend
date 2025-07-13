package com.project.imdang.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.application.response.Response;
import com.project.imdang.common.application.response.code.ErrorCode;
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
        String pathInfo = request.getPathInfo();
        return !pathInfo.startsWith(LOGIN);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            Map<String, Object> requestBody = parseRequestBodyToMap(request);
            String name = (String) requestBody.get("provider");
            OAuthProvider oAuthProvider = OAuthProvider.getProvider(name);
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

        } catch (Exception e) {
            e.printStackTrace();

            ApiResponse<Void> error = ApiResponse.error(ErrorCode.FORBIDDEN);
            Response.json(response, HttpServletResponse.SC_FORBIDDEN, objectMapper.writeValueAsString(error));
        }
    }

    private Map<String, Object> parseRequestBodyToMap(HttpServletRequest request) throws IOException {
        byte[] body;
        if (request instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
            body = wrapper.getContentAsByteArray();
        } else {
            body = request.getInputStream().readAllBytes();
        }
        return objectMapper.readValue(body, Map.class);
    }
}
