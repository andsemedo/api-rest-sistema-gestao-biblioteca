package com.example.sistema_gestao_biblioteca.dto;

import com.example.sistema_gestao_biblioteca.enums.Disponibilidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTO(@NotBlank String titulo, @NotNull Integer ano_publicacao, @NotBlank String isbn, @NotNull Disponibilidade disponibilidade, @NotNull Long autor_id) {
}
