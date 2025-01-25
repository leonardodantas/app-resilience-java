package com.br.app.movie.tmdb.java.app.usecases;

import com.br.app.movie.tmdb.java.app.exceptions.ResourceNotFoundException;
import com.br.app.movie.tmdb.java.app.integrations.IMovieIntegration;
import com.br.app.movie.tmdb.java.app.respositories.IMovieRepository;
import com.br.app.movie.tmdb.java.domain.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoviesUseCase {

    private final IMovieRepository movieRepository;
    private final IMovieIntegration movieClient;

    public Page<Movie> findMoviesBySizeAndPage(final int page, final int size) {
        log.info("Find page {} with size {} ", page, size);
        return movieRepository.findBySizeAndPage(page, size);
    }

    @CircuitBreaker(name = "movieIdCircuitBreaker", fallbackMethod = "fallbackMovieId")
    public MovieDetail findMovieDetail(final String movieId) {
        final MovieDetail movieDetail = movieRepository.findMovieDetail(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie detail %s not found in database", movieId)));
        log.info("Find detail movie {} in database", movieId);
        return movieDetail;
    }

    public MovieDetail fallbackMovieId(final String movieId, final Throwable ex) throws Throwable {
        if (ex instanceof ResourceNotFoundException) {
            throw ex;
        }
        log.info("Find detail movie {} by fallback", movieId);
        return movieClient.findMovieDetail(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie detail %s not found in fallback", movieId)));
    }

    public List<MovieBackdrops> findBackdrop(final String movieId) {
        log.info("Find backdrops movie {}", movieId);
        final List<MovieBackdrops> backdrop = movieRepository.findBackdrop(movieId);

        if (backdrop.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Movie backdrop %s not found", movieId));
        }

        return backdrop;
    }

    public MovieKeywords findMovieKeywords(final String movieId) {
        log.info("Find keywords movie {}", movieId);
        return movieRepository.findMovieKeywords(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie keywords %s not found", movieId)));
    }

    public List<MovieRecommended> findMovieRecommended(final String movieId) {
        log.info("Find recommended movie {}", movieId);

        final List<MovieRecommended> movieRecommended = movieRepository.findMovieRecommended(movieId);

        if (movieRecommended.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Movie recommended %s not found", movieId));
        }

        return movieRecommended;
    }
}
