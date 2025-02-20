package com.br.app.movie.tmdb.java.app.usecases;

import com.br.app.movie.tmdb.java.app.exceptions.ResourceNotFoundException;
import com.br.app.movie.tmdb.java.app.integrations.IMovieIntegration;
import com.br.app.movie.tmdb.java.app.respositories.IMovieRepository;
import com.br.app.movie.tmdb.java.domain.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoviesUseCase {

    private final IMovieRepository movieRepository;
    private final IMovieIntegration movieClient;

    private final ApplicationContext applicationContext;

    @Cacheable(value = "moviesCache", key = "#page + '-' + #size")
    public Page<Movie> findMoviesBySizeAndPage(final int page, final int size) {
        log.info("Find page {} with size {} ", page, size);
        return movieRepository.findBySizeAndPage(page, size);
    }

    @CircuitBreaker(name = "movieDetailCircuitBreaker", fallbackMethod = "fallbackMovieDetail")
    public MovieDetail findMovieDetail(final String movieId) {
        final MovieDetail movieDetail = movieRepository.findMovieDetail(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie detail %s not found in database", movieId)));
        log.info("Find detail movie {} in database", movieId);
        return movieDetail;
    }

    private MovieDetail fallbackMovieDetail(final String movieId, final Throwable ex) throws Throwable {
        if (ex instanceof ResourceNotFoundException) {
            throw ex;
        }
        log.info("Find detail movie {} by fallback", movieId);
        return movieClient.findMovieDetail(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie detail %s not found in fallback", movieId)));
    }

    @TimeLimiter(name = "movieBackdropsTimeLimiter", fallbackMethod = "fallbackMovieBackdrops")
    public CompletionStage<MovieBackdrops> findBackdrop(final String movieId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Find backdrops movie {} in database", movieId);

            return movieRepository.findBackdrop(movieId)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie backdrop %s not found", movieId)));
        });
    }

    public CompletionStage<MovieBackdrops> fallbackMovieBackdrops(final String movieId, final Exception ex) {
        return CompletableFuture.supplyAsync(() -> {
            if (ex instanceof ResourceNotFoundException) {
                throw new ResourceNotFoundException(String.format("Movie backdrop %s not found", movieId));
            }
            log.info("Find backdrops movie {} by fallback", movieId);

            return movieClient.findMovieBackdrops(movieId)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie backdrop %s not found", movieId)));
        });
    }

    @RateLimiter(name = "movieKeywordsRateLimiter")
    public MovieKeywords findMovieKeywords(final String movieId) {
        log.info("Find keywords movie {}", movieId);
        return movieRepository.findMovieKeywords(movieId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movie keywords %s not found", movieId)));
    }


    @Retry(name = "movieRecommendedRetry", fallbackMethod = "fallbackMovieRecommended")
    public List<MovieRecommended> findMovieRecommended(final String movieId) {
        log.info("Find recommended movie {}", movieId);

        final List<MovieRecommended> movieRecommended = movieRepository.findMovieRecommended(movieId);

        if (movieRecommended.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Movie recommended %s not found", movieId));
        }

        return movieRecommended;
    }

    public List<MovieRecommended> fallbackMovieRecommended(final String movieId, final Exception ex) {
        log.info("Find recommended movie {} retry", movieId);

        final List<MovieRecommended> movieRecommended = movieClient.findMovieRecommended(movieId);

        if (movieRecommended.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Movie recommended %s not found", movieId));
        }

        return movieRecommended;
    }
}
