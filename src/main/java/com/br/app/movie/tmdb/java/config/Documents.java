package com.br.app.movie.tmdb.java.config;

import com.br.app.movie.tmdb.java.infra.database.documents.*;
import lombok.Getter;

@Getter
public enum Documents {

    MOVIE_DOCUMENT(MovieDocument.class, "movies"),
    BACKDROPS_DOCUMENT(MovieBackdropsDocument.class, "backdrops"),
    KEYWORDS_DOCUMENT(MovieKeywordsDocument.class, "keywords"),
    DETAIL_DOCUMENT(MovieDetailDocument.class, "movies_details"),
    RECOMMENDED_DOCUMENT(MovieRecommendedDocument.class, "recommendations");

    private final Class<?> clazzDocument;
    private final String collectionName;

    Documents(final Class<?> clazzDocument, final String collectionName) {
        this.clazzDocument = clazzDocument;
        this.collectionName = collectionName;
    }

}
