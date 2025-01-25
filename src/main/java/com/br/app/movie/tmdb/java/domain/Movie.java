package com.br.app.movie.tmdb.java.domain;

public record Movie(
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
