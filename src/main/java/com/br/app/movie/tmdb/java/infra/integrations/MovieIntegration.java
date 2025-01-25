package com.br.app.movie.tmdb.java.infra.integrations;

import com.br.app.movie.tmdb.java.app.integrations.IMovieIntegration;
import com.br.app.movie.tmdb.java.config.TmdbConfigProperties;
import com.br.app.movie.tmdb.java.domain.MovieDetail;
import com.br.app.movie.tmdb.java.infra.integrations.feign.MovieFeign;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieDetailResponse;
import com.br.app.movie.tmdb.java.infra.integrations.mappers.MovieConverter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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
            final MovieDetailResponse movieResponse = movieFeign.findByMovieId(movieId, tmdbConfigProperties.isIncludeVideo(), tmdbConfigProperties.getLanguage());
            return Optional.of(movieConverter.convertDetail(movieResponse));
        } catch (final FeignException e) {
            return Optional.empty();
        }
    }

    private String getURI(final String movieId) {
        return UriComponentsBuilder.fromUriString("/movie/{movieId}")
                .queryParam("include_video", tmdbConfigProperties.isIncludeVideo())
                .queryParam("language", tmdbConfigProperties.getLanguage())
                .buildAndExpand(movieId)
                .toUriString();
    }

}
