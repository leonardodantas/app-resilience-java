package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document("movies")
public class MovieDocument {

    private String id;
    private String backdropPath;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private double voteAverage;
    private int voteCount;
}
