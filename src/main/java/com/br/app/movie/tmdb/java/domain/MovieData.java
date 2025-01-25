package com.br.app.movie.tmdb.java.domain;

import java.util.List;

public record MovieData(
        MovieDetail movieDetail,
        MovieBackdrops backdrop,
        MovieKeywords keywords,
        List<MovieRecommended> recommended
) {

    public String movieId() {
        return movieDetail.id();
    }
}
