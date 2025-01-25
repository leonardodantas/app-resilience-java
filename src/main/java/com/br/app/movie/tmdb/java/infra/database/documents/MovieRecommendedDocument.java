package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document("recommendations")
public class MovieRecommendedDocument {

    @Id
    private String id;
    private String movieId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String releaseDate;
    private String title;
}
