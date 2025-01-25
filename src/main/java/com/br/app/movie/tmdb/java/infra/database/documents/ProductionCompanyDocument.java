package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionCompanyDocument {
    private int id;
    private String logoPath;
    private String name;
    private String originCountry;
}
