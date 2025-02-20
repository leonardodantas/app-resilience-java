package com.br.app.movie.tmdb.java.infra.integrations.mappers;

import com.br.app.movie.tmdb.java.domain.MovieBackdrops;
import com.br.app.movie.tmdb.java.domain.MovieDetail;
import com.br.app.movie.tmdb.java.domain.MovieRecommended;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieBackdropsResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieDetailResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieRecommendedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieConverter {


    MovieDetail convertDetail(final MovieDetailResponse movieDetails);

    MovieBackdrops convertBackdrops(final String id, final MovieBackdropsResponse backdrop);

    MovieRecommended convertRecommended(final MovieRecommendedResponse movieRecommended);
}
