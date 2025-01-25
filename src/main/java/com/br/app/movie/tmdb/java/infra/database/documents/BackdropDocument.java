package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BackdropDocument {

    private double aspectRatio;
    private int height;
    private String filePath;
    private double voteAverage;
    private int voteCount;
    private int width;
}
