package com.br.app.movie.tmdb.java.domain;

import java.util.List;

public record MovieDetail(
        String id,
        String backdropPath,
        int budget,
        String homepage,
        String imdbId,
        List<String> originCountry,
        List<ProductionCompany> productionCompanies,
        List<ProductionCountry> productionCountries,
        long revenue,
        int runtime,
        String status,
        String tagline
) {
}
