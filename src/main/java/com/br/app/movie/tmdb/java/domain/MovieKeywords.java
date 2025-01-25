package com.br.app.movie.tmdb.java.domain;

import java.util.List;

public record MovieKeywords(
        String id,
        List<Keywords> keywords
) {
}
