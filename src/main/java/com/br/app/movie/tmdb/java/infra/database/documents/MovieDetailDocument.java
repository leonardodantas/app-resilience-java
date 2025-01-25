package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document("movies_details")
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailDocument {

    @Id
    private String id;
    private String backdropPath;
    private int budget;
    private String homepage;
    private String imdbId;
    private List<String> originCountry;
    private List<ProductionCompanyDocument> productionCompanies;
    private List<ProductionCountryDocument> productionCountries;
    private long revenue;
    private int runtime;
    private String status;
    private String tagline;
}
