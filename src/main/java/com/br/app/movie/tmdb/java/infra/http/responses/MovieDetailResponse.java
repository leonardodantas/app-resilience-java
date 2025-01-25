package com.br.app.movie.tmdb.java.infra.http.responses;

import java.util.List;

public record MovieDetailResponse(
        String id,
        String backdropPath,
        int budget,
        String homepage,
        String imdbId,
        List<String> originCountry,
        List<ProductionCompanyResponse> productionCompanies,
        List<ProductionCountryResponse> productionCountries,
        long revenue,
        int runtime,
        String status,
        String tagline
) {
}
