package com.projeto.filmes.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmeDTO {
    
    private Long id;
    
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 1, max = 200, message = "O título deve ter entre 1 e 200 caracteres")
    private String titulo;
    
    @NotBlank(message = "O diretor é obrigatório")
    @Size(min = 1, max = 150, message = "O nome do diretor deve ter entre 1 e 150 caracteres")
    private String diretor;
    
    @NotNull(message = "O ano de lançamento é obrigatório")
    @Min(value = 1888, message = "O ano deve ser maior ou igual a 1888")
    @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100")
    private Integer anoLancamento;
    
    @NotBlank(message = "O gênero é obrigatório")
    @Size(min = 1, max = 100, message = "O gênero deve ter entre 1 e 100 caracteres")
    private String genero;
    
    private String sinopse;
    
    @Min(value = 1, message = "A duração deve ser maior que 0")
    private Integer duracaoMinutos;
    
    @DecimalMin(value = "0.0", message = "A avaliação deve ser maior ou igual a 0")
    @DecimalMax(value = "10.0", message = "A avaliação deve ser menor ou igual a 10")
    private Double avaliacao;
    
    private String urlPoster;
    
    private String imdbId;
}

// DTO para atualização parcial (PATCH)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class FilmePatchDTO {
    
    @Size(min = 1, max = 200, message = "O título deve ter entre 1 e 200 caracteres")
    private String titulo;
    
    @Size(min = 1, max = 150, message = "O nome do diretor deve ter entre 1 e 150 caracteres")
    private String diretor;
    
    @Min(value = 1888, message = "O ano deve ser maior ou igual a 1888")
    @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100")
    private Integer anoLancamento;
    
    @Size(min = 1, max = 100, message = "O gênero deve ter entre 1 e 100 caracteres")
    private String genero;
    
    private String sinopse;
    
    @Min(value = 1, message = "A duração deve ser maior que 0")
    private Integer duracaoMinutos;
    
    @DecimalMin(value = "0.0", message = "A avaliação deve ser maior ou igual a 0")
    @DecimalMax(value = "10.0", message = "A avaliação deve ser menor ou igual a 10")
    private Double avaliacao;
    
    private String urlPoster;
    
    private String imdbId;
}