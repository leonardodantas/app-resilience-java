package com.br.app.movie.tmdb.java.domain;

public record MovieRecommended(
        String id,
        String movieId,
        String originalLanguage,
        String originalTitle,
        String overview,
        double popularity,
        String releaseDate,
        String title
) {
}
