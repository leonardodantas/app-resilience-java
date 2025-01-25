package com.br.app.movie.tmdb.java.infra.database.impl;

import com.br.app.movie.tmdb.java.app.respositories.IKeywordsRepository;
import com.br.app.movie.tmdb.java.infra.database.mappers.MovieKeywordsMapper;
import com.br.app.movie.tmdb.java.infra.database.mongorepository.MovieKeywordsMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieKeywordsRepository implements IKeywordsRepository {

    private final MovieKeywordsMapper movieKeywordsMapper;
    private final MovieKeywordsMongoRepository movieKeywordsMongoRepository;

}
