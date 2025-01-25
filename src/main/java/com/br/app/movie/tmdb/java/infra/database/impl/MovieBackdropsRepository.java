package com.br.app.movie.tmdb.java.infra.database.impl;

import com.br.app.movie.tmdb.java.app.respositories.IMovieBackdropsRepository;
import com.br.app.movie.tmdb.java.infra.database.mappers.MovieBackdropMapper;
import com.br.app.movie.tmdb.java.infra.database.mongorepository.MovieBackdropsMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieBackdropsRepository implements IMovieBackdropsRepository {

    private final MovieBackdropMapper movieBackdropMapper;
    private final MovieBackdropsMongoRepository movieBackdropsMongoRepository;

}
