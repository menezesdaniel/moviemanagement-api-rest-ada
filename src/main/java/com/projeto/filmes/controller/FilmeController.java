package com.projeto.filmes.controller;

import com.projeto.filmes.dto.FilmeDTO;
import com.projeto.filmes.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "API para gerenciamento de filmes")
@CrossOrigin(origins = "*")
public class FilmeController {
    
    private final FilmeService filmeService;
    
    @GetMapping
    @Operation(summary = "Listar todos os filmes", description = "Retorna uma lista com todos os filmes cadastrados ou filtra por parâmetros")
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso")
    public ResponseEntity<List<FilmeDTO>> listarTodos(
            @Parameter(description = "Filtrar por título") @RequestParam(required = false) String titulo,
            @Parameter(description = "Filtrar por diretor") @RequestParam(required = false) String diretor,
            @Parameter(description = "Filtrar por gênero") @RequestParam(required = false) String genero,
            @Parameter(description = "Filtrar por ano de lançamento") @RequestParam(required = false) Integer ano
    ) {
        List<FilmeDTO> filmes = filmeService.buscarPorFiltros(titulo, diretor, genero, ano);
        return ResponseEntity.ok(filmes);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme específico baseado no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<FilmeDTO> buscarPorId(
            @Parameter(description = "ID do filme", required = true) @PathVariable Long id
    ) {
        FilmeDTO filme = filmeService.buscarPorId(id);
        return ResponseEntity.ok(filme);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo filme", description = "Cadastra um novo filme no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Filme criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<FilmeDTO> criar(
            @Parameter(description = "Dados do filme a ser criado", required = true)
            @Valid @RequestBody FilmeDTO filmeDTO
    ) {
        FilmeDTO filmeCriado = filmeService.criar(filmeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeCriado);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar filme completamente", description = "Atualiza todos os dados de um filme existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<FilmeDTO> atualizar(
            @Parameter(description = "ID do filme", required = true) @PathVariable Long id,
            @Parameter(description = "Novos dados do filme", required = true)
            @Valid @RequestBody FilmeDTO filmeDTO
    ) {
        FilmeDTO filmeAtualizado = filmeService.atualizar(id, filmeDTO);
        return ResponseEntity.ok(filmeAtualizado);
    }
    
    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar filme parcialmente", description = "Atualiza apenas os campos especificados de um filme")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<FilmeDTO> atualizarParcial(
            @Parameter(description = "ID do filme", required = true) @PathVariable Long id,
            @Parameter(description = "Campos a serem atualizados", required = true)
            @RequestBody Map<String, Object> updates
    ) {
        FilmeDTO filmeAtualizado = filmeService.atualizarParcial(id, updates);
        return ResponseEntity.ok(filmeAtualizado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar filme", description = "Remove um filme do sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do filme", required = true) @PathVariable Long id
    ) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/top-rated")
    @Operation(summary = "Listar filmes mais bem avaliados", description = "Retorna filmes ordenados por avaliação (maior para menor)")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<FilmeDTO>> listarMaisBemAvaliados() {
        List<FilmeDTO> filmes = filmeService.buscarMaisBemAvaliados();
        return ResponseEntity.ok(filmes);
    }
    
    @GetMapping("/recentes")
    @Operation(summary = "Listar filmes recentes", description = "Retorna filmes mais recentes baseado no ano de lançamento")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<FilmeDTO>> listarRecentes() {
        List<FilmeDTO> filmes = filmeService.buscarRecentes();
        return ResponseEntity.ok(filmes);
    }
}