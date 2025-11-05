package com.projeto.filmes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.filmes.dto.FilmeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OmdbService {
    
    @Value("${omdb.api.key:sua-chave-aqui}")
    private String apiKey;
    
    @Value("${omdb.api.url:http://www.omdbapi.com/}")
    private String apiUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Busca informações de um filme na API OMDB pelo título
     * Para usar esta funcionalidade, você precisa obter uma chave gratuita em:
     * http://www.omdbapi.com/apikey.aspx
     */
    public FilmeDTO buscarFilmePorTitulo(String titulo) {
        try {
            String url = String.format("%s?apikey=%s&t=%s", apiUrl, apiKey, titulo);
            String response = restTemplate.getForObject(url, String.class);
            
            return parseOmdbResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar filme na API OMDB: " + e.getMessage());
        }
    }
    
    /**
     * Busca informações de um filme na API OMDB pelo ID do IMDB
     */
    public FilmeDTO buscarFilmePorImdbId(String imdbId) {
        try {
            String url = String.format("%s?apikey=%s&i=%s", apiUrl, apiKey, imdbId);
            String response = restTemplate.getForObject(url, String.class);
            
            return parseOmdbResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar filme na API OMDB: " + e.getMessage());
        }
    }
    
    private FilmeDTO parseOmdbResponse(String response) throws Exception {
        JsonNode root = objectMapper.readTree(response);
        
        if (root.has("Error")) {
            throw new RuntimeException("Filme não encontrado na API OMDB");
        }
        
        return FilmeDTO.builder()
                .titulo(root.path("Title").asText())
                .diretor(root.path("Director").asText())
                .anoLancamento(parseYear(root.path("Year").asText()))
                .genero(root.path("Genre").asText())
                .sinopse(root.path("Plot").asText())
                .duracaoMinutos(parseRuntime(root.path("Runtime").asText()))
                .avaliacao(parseRating(root.path("imdbRating").asText()))
                .urlPoster(root.path("Poster").asText())
                .imdbId(root.path("imdbID").asText())
                .build();
    }
    
    private Integer parseYear(String year) {
        try {
            // Remove tudo que não é número (ex: "2020–2021" vira "2020")
            String cleanYear = year.replaceAll("[^0-9].*", "");
            return Integer.parseInt(cleanYear);
        } catch (Exception e) {
            return null;
        }
    }
    
    private Integer parseRuntime(String runtime) {
        try {
            // Remove " min" e converte para inteiro
            return Integer.parseInt(runtime.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return null;
        }
    }
    
    private Double parseRating(String rating) {
        try {
            return Double.parseDouble(rating);
        } catch (Exception e) {
            return null;
        }
    }
}