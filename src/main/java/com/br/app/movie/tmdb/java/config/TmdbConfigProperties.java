package com.br.app.movie.tmdb.java.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.integration.tmdb.config")
public class TmdbConfigProperties {

    private boolean includeVideo;
    private boolean includeAdult;
    private String language;
    private String keywordsPage;
    private String recommendedPage;
}
