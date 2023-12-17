package com.example.sistema_gestao_biblioteca.controller;

import com.example.sistema_gestao_biblioteca.dto.LivroDTO;
import com.example.sistema_gestao_biblioteca.models.AutorModel;
import com.example.sistema_gestao_biblioteca.models.LivroModel;
import com.example.sistema_gestao_biblioteca.repositories.AutorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @Operation(description = "endpoint para registar um autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "retorna o autor adicionado")
    })
    @PostMapping("/autores")
    public ResponseEntity<AutorModel> adicionarNovoLivro(@RequestBody AutorModel autorModel) {

        return ResponseEntity.status(HttpStatus.CREATED).body(autorRepository.save(autorModel));

    }


    @Operation(description = "endpoint que retorna todos os autores e a lista dos seus livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna uma lista de autores"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado")
    })
    @GetMapping("/autores")
    public ResponseEntity<Object> listarAutores() {
        List<AutorModel> autorModelList = autorRepository.findAll();

        if (autorModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum autor encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(autorModelList);
    }

    @Operation(description = "endpoint para procurar um autor pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o autor procurado"),
            @ApiResponse(responseCode = "404", description = "autor não encontrado")
    })
    @GetMapping("/autores/{id}")
    public ResponseEntity<Object> obterAutorById(@PathVariable(value = "id") Long id) {
        Optional<AutorModel> optionalAutorModel = autorRepository.findById(id);
        if(optionalAutorModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(optionalAutorModel.get());
    }

    @Operation(description = "endpoint para atualizar um autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o autor atualizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum autor registado com esse ID")
    })
    @PutMapping("/autores/{id}")
    public ResponseEntity<Object> atualizarAutor(@PathVariable(value = "id") Long id, @RequestBody AutorModel autorModel) {
        Optional<AutorModel> optionalAutorModel = autorRepository.findById(id);
        if(optionalAutorModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum autor registado com esse ID");
        }

        AutorModel autor = optionalAutorModel.get();
        autor.setNome(autorModel.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(autorRepository.save(autor));
    }

    @Operation(description = "endpoint para remover um autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum autor registado com esse ID")
    })
    @DeleteMapping("/autores/{id}")
    public ResponseEntity<Object> removerAutor(@PathVariable(value = "id") Long id) {
        Optional<AutorModel> optionalAutorModel = autorRepository.findById(id);
        if(optionalAutorModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum autor registado com esse ID");
        }

        autorRepository.delete(optionalAutorModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Autor removido com sucesso");
    }


}
