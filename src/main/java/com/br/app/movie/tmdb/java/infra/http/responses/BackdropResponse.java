package com.br.app.movie.tmdb.java.infra.http.responses;

public record BackdropResponse(
        double aspectRatio,
        int height,
        String filePath,
        double voteAverage,
        int voteCount,
        int width
) {
}
