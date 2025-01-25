package com.br.app.movie.tmdb.java.infra.database.mappers;

import com.br.app.movie.tmdb.java.domain.*;
import com.br.app.movie.tmdb.java.infra.database.documents.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie convert(final MovieDocument document);

    MovieDetail convert(final MovieDetailDocument document);

    MovieBackdrops convert(final MovieBackdropsDocument document);

    MovieKeywords convert(final MovieKeywordsDocument document);

    MovieRecommended convert(final MovieRecommendedDocument document);

}
