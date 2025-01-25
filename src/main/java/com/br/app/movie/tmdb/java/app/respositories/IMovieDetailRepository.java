package com.br.app.movie.tmdb.java.app.respositories;

import com.br.app.movie.tmdb.java.domain.MovieDetail;
import org.springframework.data.domain.Page;

public interface IMovieDetailRepository {

    Page<MovieDetail> findBySizeAndPage(final int page, final int size);
}
