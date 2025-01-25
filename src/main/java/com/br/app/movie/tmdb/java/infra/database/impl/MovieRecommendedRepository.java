package com.br.app.movie.tmdb.java.infra.database.impl;

import com.br.app.movie.tmdb.java.app.respositories.IMovieRecommendedRepository;
import com.br.app.movie.tmdb.java.infra.database.mappers.MovieRecommendedMapper;
import com.br.app.movie.tmdb.java.infra.database.mongorepository.MovieRecommendedMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieRecommendedRepository implements IMovieRecommendedRepository {

    private final MovieRecommendedMapper movieRecommendedMapper;
    private final MovieRecommendedMongoRepository movieRecommendedMongoRepository;

}
