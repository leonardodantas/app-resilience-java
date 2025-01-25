package com.br.app.movie.tmdb.java.infra.database.mongorepository;

import com.br.app.movie.tmdb.java.infra.database.documents.MovieBackdropsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieBackdropsMongoRepository extends MongoRepository<MovieBackdropsDocument, String> {
}
