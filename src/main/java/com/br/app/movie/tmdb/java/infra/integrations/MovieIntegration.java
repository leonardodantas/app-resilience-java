package com.br.app.movie.tmdb.java.infra.integrations;

import com.br.app.movie.tmdb.java.app.integrations.IMovieIntegration;
import com.br.app.movie.tmdb.java.config.TmdbConfigProperties;
import com.br.app.movie.tmdb.java.domain.MovieBackdrops;
import com.br.app.movie.tmdb.java.domain.MovieDetail;
import com.br.app.movie.tmdb.java.domain.MovieRecommended;
import com.br.app.movie.tmdb.java.infra.integrations.feign.MovieFeign;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieBackdropsResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieDetailResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.PageMovieRecommendedResponse;
import com.br.app.movie.tmdb.java.infra.integrations.mappers.MovieConverter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieIntegration implements IMovieIntegration {

    private final MovieConverter movieConverter;
    private final TmdbConfigProperties tmdbConfigProperties;
    private final MovieFeign movieFeign;

    @Override
    public Optional<MovieDetail> findMovieDetail(final String movieId) {
        try {
            final MovieDetailResponse movieResponse = movieFeign.findMovieDetailByMovieId(movieId, tmdbConfigProperties.isIncludeVideo(), tmdbConfigProperties.getLanguage());
            return Optional.of(movieConverter.convertDetail(movieResponse));
        } catch (final FeignException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MovieBackdrops> findMovieBackdrops(final String movieId) {
        try {
            final MovieBackdropsResponse movieBackdrop = movieFeign.findMovieBackdropsByMovieId(movieId);
            return Optional.of(movieConverter.convertBackdrops(movieId, movieBackdrop));
        } catch (final FeignException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MovieRecommended> findMovieRecommended(final String movieId) {
        try {
            final PageMovieRecommendedResponse moviesRecommended = movieFeign.findMovieRecommendedByMovieId(movieId);

            return moviesRecommended.results().stream().map(movieConverter::convertRecommended).toList();

        } catch (final FeignException feignException) {
            return List.of();
        }
    }

}
