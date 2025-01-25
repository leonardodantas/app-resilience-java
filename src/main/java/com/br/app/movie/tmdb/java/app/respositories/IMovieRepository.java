package com.br.app.movie.tmdb.java.app.respositories;

import com.br.app.movie.tmdb.java.domain.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IMovieRepository {
    Page<Movie> findBySizeAndPage(final int page, final int size);

    Optional<MovieDetail> findMovieDetail(final String movieId);

    List<MovieBackdrops> findBackdrop(final String movieId);

    Optional<MovieKeywords> findMovieKeywords(final String movieId);

    List<MovieRecommended> findMovieRecommended(final String movieId);
}
