package com.projeto.filmes.controller;

import com.projeto.filmes.dto.FilmeDTO;
import com.projeto.filmes.service.FilmeService;
import com.projeto.filmes.service.OmdbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes/omdb")
@RequiredArgsConstructor
@Tag(name = "OMDB Integration", description = "Endpoints para buscar filmes na API externa OMDB")
@CrossOrigin(origins = "*")
public class OmdbController {
    
    private final OmdbService omdbService;
    private final FilmeService filmeService;
    
    @GetMapping("/buscar-por-titulo")
    @Operation(
        summary = "Buscar filme no OMDB por título",
        description = "Consulta a API externa OMDB e retorna informações do filme"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme encontrado"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado na API OMDB")
    })
    public ResponseEntity<FilmeDTO> buscarPorTitulo(
            @Parameter(description = "Título do filme", required = true)
            @RequestParam String titulo
    ) {
        FilmeDTO filme = omdbService.buscarFilmePorTitulo(titulo);
        return ResponseEntity.ok(filme);
    }
    
    @GetMapping("/buscar-por-imdb-id")
    @Operation(
        summary = "Buscar filme no OMDB por ID do IMDB",
        description = "Consulta a API externa OMDB usando o ID do IMDB"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme encontrado"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado na API OMDB")
    })
    public ResponseEntity<FilmeDTO> buscarPorImdbId(
            @Parameter(description = "ID do IMDB (ex: tt0111161)", required = true)
            @RequestParam String imdbId
    ) {
        FilmeDTO filme = omdbService.buscarFilmePorImdbId(imdbId);
        return ResponseEntity.ok(filme);
    }
    
    @PostMapping("/importar-por-titulo")
    @Operation(
        summary = "Importar filme do OMDB e salvar no banco",
        description = "Busca um filme na API OMDB e o cadastra automaticamente no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Filme importado e cadastrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado na API OMDB")
    })
    public ResponseEntity<FilmeDTO> importarPorTitulo(
            @Parameter(description = "Título do filme a ser importado", required = true)
            @RequestParam String titulo
    ) {
        FilmeDTO filmeOmdb = omdbService.buscarFilmePorTitulo(titulo);
        FilmeDTO filmeSalvo = filmeService.criar(filmeOmdb);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeSalvo);
    }
    
    @PostMapping("/importar-por-imdb-id")
    @Operation(
        summary = "Importar filme do OMDB por IMDB ID e salvar no banco",
        description = "Busca um filme na API OMDB usando IMDB ID e o cadastra automaticamente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Filme importado e cadastrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado na API OMDB")
    })
    public ResponseEntity<FilmeDTO> importarPorImdbId(
            @Parameter(description = "ID do IMDB do filme", required = true)
            @RequestParam String imdbId
    ) {
        FilmeDTO filmeOmdb = omdbService.buscarFilmePorImdbId(imdbId);
        FilmeDTO filmeSalvo = filmeService.criar(filmeOmdb);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeSalvo);
    }
}