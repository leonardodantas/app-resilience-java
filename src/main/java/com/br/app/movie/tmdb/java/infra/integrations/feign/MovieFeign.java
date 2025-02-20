package com.br.app.movie.tmdb.java.infra.integrations.feign;

import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieBackdropsResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieDetailResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.MovieRecommendedResponse;
import com.br.app.movie.tmdb.java.infra.integrations.jsons.PageMovieRecommendedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "tmdbFeign", url = "${spring.integration.tmdb.url}")
public interface MovieFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    MovieDetailResponse findMovieDetailByMovieId(
            @PathVariable final String movieId,
            @RequestParam(value = "include_video") final boolean includeVideo,
            @RequestParam(value = "language") final String language);

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}/images")
    MovieBackdropsResponse findMovieBackdropsByMovieId(@PathVariable final String movieId);

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}/recommendations")
    PageMovieRecommendedResponse findMovieRecommendedByMovieId(@PathVariable final String movieId);
}
