package com.br.app.movie.tmdb.java.infra.database.impl;

import com.br.app.movie.tmdb.java.app.respositories.IMovieRepository;
import com.br.app.movie.tmdb.java.domain.*;
import com.br.app.movie.tmdb.java.infra.database.mappers.MovieMapper;
import com.br.app.movie.tmdb.java.infra.database.mongorepository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieRepository implements IMovieRepository {

    private final MovieMongoRepository movieMongoRepository;
    private final MovieDetailMongoRepository movieDetailMongoRepository;
    private final MovieBackdropsMongoRepository movieBackdropsMongoRepository;
    private final MovieRecommendedMongoRepository movieRecommendedMongoRepository;
    private final MovieKeywordsMongoRepository movieKeywordsMongoRepository;
    private final MovieMapper movieMapper;

    @Override
    public Page<Movie> findBySizeAndPage(final int page, final int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        return movieMongoRepository.findAll(pageRequest).map(movieMapper::convert);
    }

    @Override
    public Optional<MovieDetail> findMovieDetail(final String movieId) {
        log.info("Try find movie {} in database", movieId);
        return movieDetailMongoRepository.findById(movieId)
                .map(movieMapper::convert);
    }

    @Override
    public Optional<MovieBackdrops> findBackdrop(final String movieId) {
        return movieBackdropsMongoRepository.findById(movieId).map(movieMapper::convert);
    }

    @Override
    public Optional<MovieKeywords> findMovieKeywords(final String movieId) {
        return movieKeywordsMongoRepository.findById(movieId)
                .map(movieMapper::convert);
    }

    @Override
    public List<MovieRecommended> findMovieRecommended(final String movieId) {
        return movieRecommendedMongoRepository.findAllByMovieId(movieId)
                .stream().map(movieMapper::convert)
                .toList();
    }
}
