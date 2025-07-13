package com.project.imdang.common.application.response;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class Response {

    public static void json(HttpServletResponse response, int sc, String body) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(sc);
        response.getWriter().print(body);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
