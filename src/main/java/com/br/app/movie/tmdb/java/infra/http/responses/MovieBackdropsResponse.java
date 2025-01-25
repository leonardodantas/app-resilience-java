package com.br.app.movie.tmdb.java.infra.http.responses;

import com.br.app.movie.tmdb.java.domain.Backdrop;

import java.util.List;

public record MovieBackdropsResponse(
        String id,
        List<BackdropResponse> backdrops
) {
}
