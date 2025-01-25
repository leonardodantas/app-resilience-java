package com.br.app.movie.tmdb.java.infra.database.documents;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document("backdrops")
public class MovieBackdropsDocument {

    @Id
    private String id;
    private List<BackdropDocument> backdrops;

}
