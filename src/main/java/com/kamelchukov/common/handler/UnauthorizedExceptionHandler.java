package com.kamelchukov.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamelchukov.common.exception.response.AuthorizationResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UnauthorizedExceptionHandler implements org.springframework.security.web.AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        var authResponse = new AuthorizationResponse(
                response.getStatus(), "Unauthorized request", request.getRequestURI());

        var outputStream = response.getOutputStream();
        var mapper = new ObjectMapper();

        mapper.writeValue(outputStream, authResponse);
        outputStream.flush();
    }
}
