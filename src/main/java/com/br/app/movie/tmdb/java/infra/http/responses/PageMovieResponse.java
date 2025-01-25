package com.br.app.movie.tmdb.java.infra.http.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PageMovieResponse(

        List<MovieResponse> movies,
        int page,
        int size,
        int totalElements,
        int totalPages
) {
}
