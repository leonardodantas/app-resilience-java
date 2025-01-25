package com.br.app.movie.tmdb.java.domain;

import java.util.List;

public record MovieBackdrops(
        String id,
        List<Backdrop> backdrops
) {
}
