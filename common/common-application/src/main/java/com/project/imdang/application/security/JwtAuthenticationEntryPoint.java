package com.project.imdang.application.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.application.handler.ErrorDTO;
import com.project.imdang.application.security.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = String.valueOf(request.getAttribute("exception"));

        switch (exception) {
            case "J007":
                setResponse(response, ErrorCode.ADDITIONAL_REQUIRED_TOKEN);
                break;

            case "J001":
                setResponse(response, ErrorCode.UNKNOWN_ERROR);
                break;

            case "J002":
                setResponse(response, ErrorCode.MAL_FORMED_TOKEN);
                break;

            case "J003":
                setResponse(response, ErrorCode.EXPIRED_TOKEN);
                break;

            case "J004":
                setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
                break;

            case "J006":
                setResponse(response, ErrorCode.ILLEGAL_TOKEN);
                break;

            default:
                setResponse(response, ErrorCode.ACCESS_DENIED);
                break;
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorDTO errorDTO = new ErrorDTO(exceptionCode.getErrorCode(), exceptionCode.getMessage());
        response.getWriter().print(new ObjectMapper().writeValueAsString(errorDTO));
    }
}
