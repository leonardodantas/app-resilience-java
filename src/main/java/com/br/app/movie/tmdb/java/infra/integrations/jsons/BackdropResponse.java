package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BackdropResponse(
        double aspectRatio,
        int height,
        String filePath,
        double voteAverage,
        int voteCount,
        int width
) {
}
