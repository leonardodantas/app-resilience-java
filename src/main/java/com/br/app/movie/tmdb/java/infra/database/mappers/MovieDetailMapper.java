package com.br.app.movie.tmdb.java.infra.database.mappers;

import com.br.app.movie.tmdb.java.domain.MovieDetail;
import com.br.app.movie.tmdb.java.infra.database.documents.MovieDetailDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieDetailMapper {

    MovieDetail convert(final MovieDetailDocument document);

}
