package com.project.imdang.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.application.response.code.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ApiResponse<Void> error = (accessDeniedException != null && accessDeniedException.getMessage() != null) ?
                ApiResponse.error(ErrorCode.FORBIDDEN, accessDeniedException.getMessage()) : ApiResponse.error(ErrorCode.FORBIDDEN);
        response.getWriter().print(objectMapper.writeValueAsString(error));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
