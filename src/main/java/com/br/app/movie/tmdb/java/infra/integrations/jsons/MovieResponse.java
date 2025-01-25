package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MovieResponse(
        String id,
        String backdropPath,
        String originalLanguage,
        String originalTitle,
        String overview,
        double popularity,
        String posterPath,
        String releaseDate,
        String title,
        double voteAverage,
        int voteCount
) {
}
