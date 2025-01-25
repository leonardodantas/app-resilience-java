package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MovieRecommendedResponse(
        String id,
        String originalLanguage,
        String originalTitle,
        String overview,
        double popularity,
        String releaseDate,
        String title
) {
}
