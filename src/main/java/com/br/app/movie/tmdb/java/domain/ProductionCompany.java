package com.br.app.movie.tmdb.java.domain;

public record ProductionCompany(
        int id,
        String logoPath,
        String name,
        String originCountry
) {
}
