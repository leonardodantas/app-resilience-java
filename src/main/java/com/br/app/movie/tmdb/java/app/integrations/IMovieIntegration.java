package com.br.app.movie.tmdb.java.app.integrations;

import com.br.app.movie.tmdb.java.domain.MovieBackdrops;
import com.br.app.movie.tmdb.java.domain.MovieDetail;

import java.util.List;
import java.util.Optional;

public interface IMovieIntegration {
    Optional<MovieDetail> findMovieDetail(final String movieId);

    Optional<MovieBackdrops> findMovieBackdrops(final String movieId);
}
