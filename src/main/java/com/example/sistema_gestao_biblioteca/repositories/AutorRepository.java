package com.example.sistema_gestao_biblioteca.repositories;

import com.example.sistema_gestao_biblioteca.models.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutorModel, Long> {
}
