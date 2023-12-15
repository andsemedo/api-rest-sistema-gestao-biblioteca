package com.example.sistema_gestao_biblioteca.repositories;

import com.example.sistema_gestao_biblioteca.models.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroModel, Long> {
}
