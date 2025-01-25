package com.br.app.movie.tmdb.java.domain;

public record Backdrop(
        double aspectRatio,
        int height,
        String filePath,
        double voteAverage,
        int voteCount,
        int width
) {
}
