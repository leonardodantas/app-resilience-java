package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KeywordsDocument {

    private String id;
    private String name;
}
