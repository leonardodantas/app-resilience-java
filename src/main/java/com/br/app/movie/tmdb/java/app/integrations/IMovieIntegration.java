package com.br.app.movie.tmdb.java.app.integrations;

import com.br.app.movie.tmdb.java.domain.MovieDetail;

import java.util.Optional;

public interface IMovieIntegration {
    Optional<MovieDetail> findMovieDetail(final String movieId);
}
