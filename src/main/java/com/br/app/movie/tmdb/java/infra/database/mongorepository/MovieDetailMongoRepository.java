package com.br.app.movie.tmdb.java.infra.database.mongorepository;

import com.br.app.movie.tmdb.java.infra.database.documents.MovieDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieDetailMongoRepository extends MongoRepository<MovieDetailDocument, String> {
}
