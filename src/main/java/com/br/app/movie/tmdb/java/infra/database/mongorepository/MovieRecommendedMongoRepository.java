package com.br.app.movie.tmdb.java.infra.database.mongorepository;

import com.br.app.movie.tmdb.java.infra.database.documents.MovieRecommendedDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRecommendedMongoRepository extends MongoRepository<MovieRecommendedDocument, String> {
    List<MovieRecommendedDocument> findAllByMovieId(final String movieId);
}
