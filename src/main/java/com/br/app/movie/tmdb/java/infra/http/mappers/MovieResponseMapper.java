package com.br.app.movie.tmdb.java.infra.http.mappers;

import com.br.app.movie.tmdb.java.domain.*;
import com.br.app.movie.tmdb.java.infra.http.responses.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieResponseMapper {

    PageMovieResponse convert(final List<Movie> movies, final int size,
                              final int page,
                              final long totalElements,
                              final int totalPages);

    MovieDetailResponse convert(final MovieDetail detail);
    MovieBackdropsResponse convert(final MovieBackdrops backdrops);
    MovieKeywordsResponse convert(final MovieKeywords movieKeywords);
    MovieRecommendedResponse convert(final MovieRecommended recommended);
}
