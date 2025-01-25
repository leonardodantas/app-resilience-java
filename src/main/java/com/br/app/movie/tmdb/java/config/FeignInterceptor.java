package com.br.app.movie.tmdb.java.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    @Value("${spring.integration.tmdb.bearer}")
    private String bearerToken;

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION, BEARER.concat(bearerToken));
    }
}
