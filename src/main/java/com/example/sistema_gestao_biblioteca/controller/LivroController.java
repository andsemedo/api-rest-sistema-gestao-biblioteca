package com.example.sistema_gestao_biblioteca.controller;

import com.example.sistema_gestao_biblioteca.dto.LivroDTO;
import com.example.sistema_gestao_biblioteca.models.AutorModel;
import com.example.sistema_gestao_biblioteca.models.LivroModel;
import com.example.sistema_gestao_biblioteca.repositories.AutorRepository;
import com.example.sistema_gestao_biblioteca.repositories.LivroRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Operation(description = "endpoint para adicionar um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "retorna o livro adicionado"),
            @ApiResponse(responseCode = "500", description = "Já existe um livro inserido com este ISBN")
    })
    @PostMapping("/livros")
    public ResponseEntity<Object> adicionarNovoLivro(@RequestBody @Valid LivroDTO livroDTO) {
        try {
            Optional<AutorModel> optionalAutorModel = autorRepository.findById(livroDTO.autor_id());

            if (optionalAutorModel.isPresent()) {
                AutorModel autorModel = optionalAutorModel.get();
                LivroModel livroModel = new LivroModel(livroDTO.titulo(),livroDTO.ano_publicacao(),livroDTO.isbn(),livroDTO.disponibilidade(),autorModel);
                return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));

            };
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum autor registado com esse ID");
        } catch (DataIntegrityViolationException e) {
            //if (e.getCause().getMessage().contains("Disponibilidade")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Já existe um livro inserido com este ISBN");
            //}
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @Operation(description = "endpoint para retornar todos os livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de livros"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado")
    })
    @GetMapping("/livros")
    public ResponseEntity<Object> listarLivros() {
        List<LivroModel> livroModelList = livroRepository.findAll();

        if (livroModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum livro encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(livroModelList);
    }

    @Operation(description = "endpoint para procurar um livro pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o livro procurado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/livros/{id}")
    public ResponseEntity<Object> obterLivrosById(@PathVariable(value = "id") Long id) {
        Optional<LivroModel> livroOptional = livroRepository.findById(id);
        if(livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional.get());
    }
    @Operation(description = "endpoint para atualizar um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o livro atualizado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Já existe um livro inserido com este ISBN")
    })
    @PutMapping("/livros/{id}")
    public ResponseEntity<Object> atualizarLivro(@PathVariable(value = "id") Long id, @RequestBody @Valid LivroDTO livroDTO) {
        Optional<LivroModel> livroModelOptional = livroRepository.findById(id);
        if(livroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        try {
            LivroModel livroModel = livroModelOptional.get();
            Optional<AutorModel> optionalAutorModel = autorRepository.findById(livroDTO.autor_id());

            if (optionalAutorModel.isPresent()) {
                AutorModel autorModel = optionalAutorModel.get();
                livroModel.setTitulo(livroDTO.titulo());
                livroModel.setAno_publicacao(livroDTO.ano_publicacao());
                livroModel.setIsbn(livroDTO.isbn());
                livroModel.setDisponibilidade(livroDTO.disponibilidade());
                livroModel.setAutor(autorModel);
                return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));

            };
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum autor registado com esse ID");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Já existe um livro inserido com este ISBN");
        }


    }

    @Operation(description = "endpoint para remover um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Object> removerLivro(@PathVariable(value = "id") Long id) {
        Optional<LivroModel> livroModelOptional = livroRepository.findById(id);
        if(livroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        livroRepository.delete(livroModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Livro removido com sucesso");
    }

}
