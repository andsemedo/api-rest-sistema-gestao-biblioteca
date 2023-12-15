package com.example.sistema_gestao_biblioteca;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Biblioteca API", version = "2.0", description = "Sistema de Gestão de Biblioteca"))
public class SistemaGestaoBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaGestaoBibliotecaApplication.class, args);
	}

}
