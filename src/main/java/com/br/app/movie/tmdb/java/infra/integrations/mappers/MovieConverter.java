package com.br.app.movie.tmdb.java.infra.integrations.mappers;

import com.br.app.movie.tmdb.java.domain.MovieDetail;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieConverter {


    MovieDetail convertDetail(final MovieDetailResponse movieDetails);

}
