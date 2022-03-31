package com.kamelchukov.common.handler;

import com.kamelchukov.common.exception.EntityNotFoundException;
import com.kamelchukov.common.exception.IncorrectDataException;
import com.kamelchukov.common.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException ex, ServletWebRequest request) {

        return getExceptionResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<ExceptionResponse> handleException(IncorrectDataException ex, ServletWebRequest request) {

        return getExceptionResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> getExceptionResponse(
            Exception e, ServletWebRequest request, HttpStatus status) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), request.getRequest().getRequestURI()), status);
    }
}
