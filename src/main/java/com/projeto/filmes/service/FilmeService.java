package com.projeto.filmes.service;

import com.projeto.filmes.dto.FilmeDTO;
import com.projeto.filmes.exception.ResourceNotFoundException;
import com.projeto.filmes.model.Filme;
import com.projeto.filmes.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmeService {
    
    private final FilmeRepository filmeRepository;
    
    // Buscar todos os filmes
    @Transactional(readOnly = true)
    public List<FilmeDTO> buscarTodos() {
        return filmeRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    // Buscar filme por ID
    @Transactional(readOnly = true)
    public FilmeDTO buscarPorId(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com ID: " + id));
        return converterParaDTO(filme);
    }
    
    // Criar novo filme
    @Transactional
    public FilmeDTO criar(FilmeDTO filmeDTO) {
        Filme filme = converterParaEntidade(filmeDTO);
        Filme filmeSalvo = filmeRepository.save(filme);
        return converterParaDTO(filmeSalvo);
    }
    
    // Atualizar filme completamente (PUT)
    @Transactional
    public FilmeDTO atualizar(Long id, FilmeDTO filmeDTO) {
        Filme filmeExistente = filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com ID: " + id));
        
        filmeExistente.setTitulo(filmeDTO.getTitulo());
        filmeExistente.setDiretor(filmeDTO.getDiretor());
        filmeExistente.setAnoLancamento(filmeDTO.getAnoLancamento());
        filmeExistente.setGenero(filmeDTO.getGenero());
        filmeExistente.setSinopse(filmeDTO.getSinopse());
        filmeExistente.setDuracaoMinutos(filmeDTO.getDuracaoMinutos());
        filmeExistente.setAvaliacao(filmeDTO.getAvaliacao());
        filmeExistente.setUrlPoster(filmeDTO.getUrlPoster());
        filmeExistente.setImdbId(filmeDTO.getImdbId());
        
        Filme filmeAtualizado = filmeRepository.save(filmeExistente);
        return converterParaDTO(filmeAtualizado);
    }
    
    // Atualizar filme parcialmente (PATCH)
    @Transactional
    public FilmeDTO atualizarParcial(Long id, Map<String, Object> updates) {
        Filme filmeExistente = filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com ID: " + id));
        
        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "titulo" -> filmeExistente.setTitulo((String) valor);
                case "diretor" -> filmeExistente.setDiretor((String) valor);
                case "anoLancamento" -> filmeExistente.setAnoLancamento((Integer) valor);
                case "genero" -> filmeExistente.setGenero((String) valor);
                case "sinopse" -> filmeExistente.setSinopse((String) valor);
                case "duracaoMinutos" -> filmeExistente.setDuracaoMinutos((Integer) valor);
                case "avaliacao" -> {
                    if (valor instanceof Number) {
                        filmeExistente.setAvaliacao(((Number) valor).doubleValue());
                    }
                }
                case "urlPoster" -> filmeExistente.setUrlPoster((String) valor);
                case "imdbId" -> filmeExistente.setImdbId((String) valor);
            }
        });
        
        Filme filmeAtualizado = filmeRepository.save(filmeExistente);
        return converterParaDTO(filmeAtualizado);
    }
    
    // Deletar filme
    @Transactional
    public void deletar(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Filme não encontrado com ID: " + id);
        }
        filmeRepository.deleteById(id);
    }
    
    // Buscar filmes por filtros
    @Transactional(readOnly = true)
    public List<FilmeDTO> buscarPorFiltros(String titulo, String diretor, String genero, Integer ano) {
        List<Filme> filmes;
        
        if (titulo != null && !titulo.isEmpty()) {
            filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
        } else if (diretor != null && !diretor.isEmpty()) {
            filmes = filmeRepository.findByDiretorContainingIgnoreCase(diretor);
        } else if (genero != null && !genero.isEmpty()) {
            filmes = filmeRepository.findByGeneroContainingIgnoreCase(genero);
        } else if (ano != null) {
            filmes = filmeRepository.findByAnoLancamento(ano);
        } else {
            filmes = filmeRepository.findAll();
        }
        
        return filmes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    // Buscar filmes mais bem avaliados
    @Transactional(readOnly = true)
    public List<FilmeDTO> buscarMaisBemAvaliados() {
        return filmeRepository.findTopRatedFilmes()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    // Buscar filmes recentes
    @Transactional(readOnly = true)
    public List<FilmeDTO> buscarRecentes() {
        return filmeRepository.findRecentFilmes()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    // Métodos auxiliares de conversão
    private FilmeDTO converterParaDTO(Filme filme) {
        return FilmeDTO.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .diretor(filme.getDiretor())
                .anoLancamento(filme.getAnoLancamento())
                .genero(filme.getGenero())
                .sinopse(filme.getSinopse())
                .duracaoMinutos(filme.getDuracaoMinutos())
                .avaliacao(filme.getAvaliacao())
                .urlPoster(filme.getUrlPoster())
                .imdbId(filme.getImdbId())
                .build();
    }
    
    private Filme converterParaEntidade(FilmeDTO dto) {
        return Filme.builder()
                .titulo(dto.getTitulo())
                .diretor(dto.getDiretor())
                .anoLancamento(dto.getAnoLancamento())
                .genero(dto.getGenero())
                .sinopse(dto.getSinopse())
                .duracaoMinutos(dto.getDuracaoMinutos())
                .avaliacao(dto.getAvaliacao())
                .urlPoster(dto.getUrlPoster())
                .imdbId(dto.getImdbId())
                .build();
    }
}