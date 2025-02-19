package com.br.app.movie.tmdb.java.infra.http.controllers;

import com.br.app.movie.tmdb.java.app.exceptions.ResourceNotFoundException;
import com.br.app.movie.tmdb.java.infra.http.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class MovieAppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(final Exception ex, final WebRequest request) {

        if(ex.getCause() instanceof ResourceNotFoundException) {
            return ResponseEntity.notFound().build();
        }
        final var response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage(), request.getDescription(false));
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(final ResourceNotFoundException ex, final WebRequest request) {
        log.info(ex.getMessage());
        return ResponseEntity.notFound().build();
    }
}
