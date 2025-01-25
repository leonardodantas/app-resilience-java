package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import java.util.List;

public record MovieBackdropsResponse(
        List<BackdropResponse> backdrops
) {
}
