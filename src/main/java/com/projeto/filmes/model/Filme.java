package com.projeto.filmes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filme {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 1, max = 200, message = "O título deve ter entre 1 e 200 caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;
    
    @NotBlank(message = "O diretor é obrigatório")
    @Size(min = 1, max = 150, message = "O nome do diretor deve ter entre 1 e 150 caracteres")
    @Column(nullable = false, length = 150)
    private String diretor;
    
    @NotNull(message = "O ano de lançamento é obrigatório")
    @Min(value = 1888, message = "O ano deve ser maior ou igual a 1888")
    @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100")
    @Column(nullable = false)
    private Integer anoLancamento;
    
    @NotBlank(message = "O gênero é obrigatório")
    @Size(min = 1, max = 100, message = "O gênero deve ter entre 1 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String genero;
    
    @Column(length = 1000)
    private String sinopse;
    
    @Min(value = 1, message = "A duração deve ser maior que 0")
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;
    
    @DecimalMin(value = "0.0", message = "A avaliação deve ser maior ou igual a 0")
    @DecimalMax(value = "10.0", message = "A avaliação deve ser menor ou igual a 10")
    @Column
    private Double avaliacao;
    
    @Column(name = "url_poster", length = 500)
    private String urlPoster;
    
    @Column(name = "imdb_id", length = 20)
    private String imdbId;
    
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDate dataCadastro;
    
    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;
    
    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDate.now();
        dataAtualizacao = LocalDate.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDate.now();
    }
}