package com.project.imdang.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.application.response.ErrorResponse;
import com.project.imdang.common.application.response.code.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String code = String.valueOf(request.getAttribute("exception"));

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorCode errorCode = ErrorCode.getErrorCode(code);
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        response.getWriter().print(objectMapper.writeValueAsString(errorResponse));
    }
}
