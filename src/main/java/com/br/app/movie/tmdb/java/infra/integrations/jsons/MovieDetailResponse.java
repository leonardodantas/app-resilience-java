package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
