package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PageMovieRecommendedResponse(
        int page,
        List<MovieRecommendedResponse> results,
        int totalPages,
        int totalResults
) {
}
