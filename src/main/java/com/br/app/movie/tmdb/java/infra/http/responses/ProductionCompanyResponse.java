package com.br.app.movie.tmdb.java.infra.http.responses;

public record ProductionCompanyResponse(
        String logoPath,
        String name,
        String originCountry
) {
}
