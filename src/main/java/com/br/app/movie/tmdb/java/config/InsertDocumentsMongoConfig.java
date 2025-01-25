package com.br.app.movie.tmdb.java.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class InsertDocumentsMongoConfig {

    private final ObjectMapper objectMapper;
    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void insertDocuments() {
        Arrays.stream(Documents.values()).toList()
                .forEach(document -> {
                    insertInDataBase(document.getClazzDocument(), document.getCollectionName());
                });
    }

    private <T> void insertInDataBase(final Class<T> type, final String collection) {
        final List<T> documents = getDocumentAsListObject(type, collection);
        mongoTemplate.remove(new Query(), collection);
        mongoTemplate.insertAll(documents);
    }

    private <T> List<T> getDocumentAsListObject(final Class<T> type, final String json) {
        final var jsonPath = "documents/".concat(json).concat(".json");
        try {
            final var resource = Optional.ofNullable(Main.class.getClassLoader().getResource(jsonPath))
                    .orElseThrow(() -> new IllegalArgumentException("Arquivo JSON não encontrado: " + json));

            final var path = Paths.get(resource.toURI());
            final var jsonContent = Files.readString(path);

            return objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (final URISyntaxException | IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + json, e);
        }
    }
}