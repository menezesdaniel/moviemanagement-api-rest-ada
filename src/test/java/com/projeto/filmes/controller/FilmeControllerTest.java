package com.projeto.filmes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.filmes.dto.FilmeDTO;
import com.projeto.filmes.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmeController.class)
class FilmeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private FilmeService filmeService;
    
    private FilmeDTO filmeDTO;
    
    @BeforeEach
    void setUp() {
        filmeDTO = FilmeDTO.builder()
                .id(1L)
                .titulo("A Origem")
                .diretor("Christopher Nolan")
                .anoLancamento(2010)
                .genero("Ficção Científica")
                .sinopse("Teste de sinopse")
                .duracaoMinutos(148)
                .avaliacao(8.8)
                .build();
    }
    
    @Test
    void deveListarTodosOsFilmes() throws Exception {
        List<FilmeDTO> filmes = Arrays.asList(filmeDTO);
        when(filmeService.buscarPorFiltros(null, null, null, null))
                .thenReturn(filmes);
        
        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("A Origem"))
                .andExpect(jsonPath("$[0].diretor").value("Christopher Nolan"));
        
        verify(filmeService, times(1)).buscarPorFiltros(null, null, null, null);
    }
    
    @Test
    void deveBuscarFilmePorId() throws Exception {
        when(filmeService.buscarPorId(1L)).thenReturn(filmeDTO);
        
        mockMvc.perform(get("/filmes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("A Origem"))
                .andExpect(jsonPath("$.id").value(1));
        
        verify(filmeService, times(1)).buscarPorId(1L);
    }
    
    @Test
    void deveCriarNovoFilme() throws Exception {
        when(filmeService.criar(any(FilmeDTO.class))).thenReturn(filmeDTO);
        
        mockMvc.perform(post("/filmes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filmeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("A Origem"));
        
        verify(filmeService, times(1)).criar(any(FilmeDTO.class));
    }
    
    @Test
    void deveAtualizarFilme() throws Exception {
        when(filmeService.atualizar(eq(1L), any(FilmeDTO.class)))
                .thenReturn(filmeDTO);
        
        mockMvc.perform(put("/filmes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filmeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("A Origem"));
        
        verify(filmeService, times(1)).atualizar(eq(1L), any(FilmeDTO.class));
    }
    
    @Test
    void deveDeletarFilme() throws Exception {
        doNothing().when(filmeService).deletar(1L);
        
        mockMvc.perform(delete("/filmes/1"))
                .andExpect(status().isNoContent());
        
        verify(filmeService, times(1)).deletar(1L);
    }
    
    @Test
    void deveRetornarErroQuandoFilmeNaoEncontrado() throws Exception {
        when(filmeService.buscarPorId(999L))
                .thenThrow(new RuntimeException("Filme não encontrado"));
        
        mockMvc.perform(get("/filmes/999"))
                .andExpect(status().isInternalServerError());
    }
    
    @Test
    void deveValidarCamposObrigatorios() throws Exception {
        FilmeDTO filmeInvalido = FilmeDTO.builder()
                .titulo("")  // título vazio
                .build();
        
        mockMvc.perform(post("/filmes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filmeInvalido)))
                .andExpect(status().isBadRequest());
    }
}