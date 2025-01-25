package com.br.app.movie.tmdb.java.app.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
