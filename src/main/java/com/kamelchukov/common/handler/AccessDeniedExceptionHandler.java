package com.kamelchukov.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamelchukov.common.exception.response.AuthorizationResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedExceptionHandler implements
        org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        var authResponse = new AuthorizationResponse(
                response.getStatus(), "Access denied", request.getRequestURI());

        var outputStream = response.getOutputStream();
        var mapper = new ObjectMapper();

        mapper.writeValue(outputStream, authResponse);
        outputStream.flush();
    }
}
