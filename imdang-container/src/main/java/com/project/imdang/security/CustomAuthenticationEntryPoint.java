package com.project.imdang.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.application.response.Response;
import com.project.imdang.common.application.response.code.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ApiResponse<Void> error = (authException != null && authException.getMessage() != null) ?
                ApiResponse.error(ErrorCode.UNAUTHORIZED, authException.getMessage()) : ApiResponse.error(ErrorCode.UNAUTHORIZED);
        Response.json(response, HttpServletResponse.SC_UNAUTHORIZED, objectMapper.writeValueAsString(error));
    }
}
