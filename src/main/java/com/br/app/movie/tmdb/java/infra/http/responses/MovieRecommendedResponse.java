package com.br.app.movie.tmdb.java.infra.http.responses;

public record MovieRecommendedResponse(
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
