package com.br.app.movie.tmdb.java.infra.integrations.jsons;

import java.util.List;

public record MovieKeywordsResponse(
        String id,
        List<KeywordsResponse> keywords
) {
}
