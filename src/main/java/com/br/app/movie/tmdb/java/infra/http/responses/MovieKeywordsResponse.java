package com.br.app.movie.tmdb.java.infra.http.responses;

import java.util.List;

public record MovieKeywordsResponse(
        String id,
        List<KeywordsResponse> keywords
) {
}
