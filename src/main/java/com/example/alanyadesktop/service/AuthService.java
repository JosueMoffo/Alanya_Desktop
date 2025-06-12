package com.example.alanyadesktop.service;

import com.example.alanyadesktop.model.AuthRequest;
import com.example.alanyadesktop.model.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class AuthService {
    private static final String BASE_URL = "http://localhost:8080/api/auth";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AuthService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Envoie une requête de connexion au serveur
     */
    public AuthResponse login(AuthRequest request) throws Exception {
        String requestBody = objectMapper.writeValueAsString(request);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), AuthResponse.class);
        } else {
            throw new RuntimeException("Échec de la connexion: " + response.body());
        }
    }

    /**
     * Envoie une requête d'inscription au serveur
     */
    public AuthResponse register(AuthRequest request) throws Exception {
        String requestBody = objectMapper.writeValueAsString(request);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), AuthResponse.class);
        } else {
            throw new RuntimeException("Échec de l'inscription: " + response.body());
        }
    }
}