package com.example.sistema_gestao_biblioteca.dto;

import com.example.sistema_gestao_biblioteca.enums.Disponibilidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTO(@NotBlank String titulo, @NotBlank String autor, @NotNull int ano_publicacao, @NotBlank String isbn, @NotBlank Disponibilidade disponibilidade) {
}