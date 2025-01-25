package com.br.app.movie.tmdb.java.infra.database.mongorepository;

import com.br.app.movie.tmdb.java.infra.database.documents.MovieDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieMongoRepository extends MongoRepository<MovieDocument, String> {
}
