package com.projeto.filmes.repository;

import com.projeto.filmes.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    
    // Buscar filmes por título (contém)
    List<Filme> findByTituloContainingIgnoreCase(String titulo);
    
    // Buscar filmes por diretor
    List<Filme> findByDiretorContainingIgnoreCase(String diretor);
    
    // Buscar filmes por gênero
    List<Filme> findByGeneroContainingIgnoreCase(String genero);
    
    // Buscar filmes por ano de lançamento
    List<Filme> findByAnoLancamento(Integer ano);
    
    // Buscar filmes entre anos
    List<Filme> findByAnoLancamentoBetween(Integer anoInicio, Integer anoFim);
    
    // Buscar filmes com avaliação maior ou igual
    List<Filme> findByAvaliacaoGreaterThanEqual(Double avaliacao);
    
    // Buscar filme por IMDB ID
    Optional<Filme> findByImdbId(String imdbId);
    
    // Buscar filmes ordenados por avaliação (decrescente)
    @Query("SELECT f FROM Filme f ORDER BY f.avaliacao DESC")
    List<Filme> findTopRatedFilmes();
    
    // Buscar filmes lançados recentemente
    @Query("SELECT f FROM Filme f ORDER BY f.anoLancamento DESC, f.dataCadastro DESC")
    List<Filme> findRecentFilmes();
    
    // Contar filmes por gênero
    @Query("SELECT COUNT(f) FROM Filme f WHERE LOWER(f.genero) LIKE LOWER(CONCAT('%', :genero, '%'))")
    Long countByGenero(@Param("genero") String genero);
}