package com.br.app.movie.tmdb.java.infra.database.mongorepository;

import com.br.app.movie.tmdb.java.infra.database.documents.MovieKeywordsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieKeywordsMongoRepository extends MongoRepository<MovieKeywordsDocument, String> {
}
