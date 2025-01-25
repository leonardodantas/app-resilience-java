package com.br.app.movie.tmdb.java.infra.http.controllers;

import com.br.app.movie.tmdb.java.app.usecases.MoviesUseCase;
import com.br.app.movie.tmdb.java.domain.*;
import com.br.app.movie.tmdb.java.infra.http.mappers.MovieResponseMapper;
import com.br.app.movie.tmdb.java.infra.http.responses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesUseCase moviesUsecase;
    private final MovieResponseMapper movieResponseMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageMovieResponse findBySizeAndPage(@RequestParam(defaultValue = "20") final int size,
                                               @RequestParam(defaultValue = "1") final int page) {
        final Page<Movie> pageMovie = moviesUsecase.findMoviesBySizeAndPage(page, size);
        return movieResponseMapper.convert(pageMovie.getContent(), size, page, pageMovie.getTotalElements(), pageMovie.getTotalPages());
    }

    @GetMapping("{movieId}/details")
    @ResponseStatus(HttpStatus.OK)
    public MovieDetailResponse findDetailsByMovieId(@PathVariable final String movieId) {
        final MovieDetail movieDetail = moviesUsecase.findMovieDetail(movieId);
        return movieResponseMapper.convert(movieDetail);
    }

    @GetMapping("{movieId}/backdrops")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieBackdropsResponse> findBackdropsByMovieId(@PathVariable final String movieId) {
        final List<MovieBackdrops> backdrops = moviesUsecase.findBackdrop(movieId);
        return backdrops.stream().map(movieResponseMapper::convert).toList();
    }

    @GetMapping("{movieId}/keywords")
    @ResponseStatus(HttpStatus.OK)
    public MovieKeywordsResponse findKeywordsByMovieId(@PathVariable final String movieId) {
        final MovieKeywords movieKeywords = moviesUsecase.findMovieKeywords(movieId);
        return movieResponseMapper.convert(movieKeywords);
    }

    @GetMapping("{movieId}/recommended")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieRecommendedResponse> findRecommendedByMovieId(@PathVariable final String movieId) {
        final List<MovieRecommended> movieRecommended = moviesUsecase.findMovieRecommended(movieId);
        return movieRecommended.stream().map(movieResponseMapper::convert).toList();
    }
}
