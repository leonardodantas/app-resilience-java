package com.br.app.movie.tmdb.java.infra.database.impl;

import com.br.app.movie.tmdb.java.app.respositories.IMovieDetailRepository;
import com.br.app.movie.tmdb.java.domain.MovieDetail;
import com.br.app.movie.tmdb.java.infra.database.documents.MovieDetailDocument;
import com.br.app.movie.tmdb.java.infra.database.mappers.MovieDetailMapper;
import com.br.app.movie.tmdb.java.infra.database.mongorepository.MovieDetailMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieDetailRepository implements IMovieDetailRepository {

    private final MovieDetailMapper movieDetailMapper;
    private final MovieDetailMongoRepository movieDetailMongoRepository;

    @Override
    public Page<MovieDetail> findBySizeAndPage(final int page, final int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<MovieDetailDocument> pageMovies = movieDetailMongoRepository.findAll(pageRequest);
        return pageMovies.map(movieDetailMapper::convert);
    }
}
