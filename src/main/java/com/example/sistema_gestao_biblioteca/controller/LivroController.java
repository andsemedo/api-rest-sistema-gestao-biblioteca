package com.example.sistema_gestao_biblioteca.controller;

import com.example.sistema_gestao_biblioteca.dto.LivroDTO;
import com.example.sistema_gestao_biblioteca.models.LivroModel;
import com.example.sistema_gestao_biblioteca.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @PostMapping("/livros")
    public ResponseEntity<LivroModel> adicionarNovoLivro(@RequestBody @Valid LivroDTO livroDTO) {
        LivroModel livroModel = new LivroModel();
        BeanUtils.copyProperties(livroDTO, livroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }

    @GetMapping("/livros")
    public ResponseEntity<List<LivroModel>> listarLivros() {
        List<LivroModel> livroModelList = livroRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(livroModelList);
    }

    // metodo para procurar livro pelo id
    @GetMapping("/livros/{id}")
    public ResponseEntity<Object> obterLivrosById(@PathVariable(value = "id") Long id) {
        Optional<LivroModel> livroOptional = livroRepository.findById(id);
        if(livroOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(livroOptional.get());
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<Object> atualizarLivro(@PathVariable(value = "id") Long id, @RequestBody @Valid LivroDTO livroDTO) {
        Optional<LivroModel> livroModelOptional = livroRepository.findById(id);
        if(livroModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        LivroModel livroModel = livroModelOptional.get();
        BeanUtils.copyProperties(livroDTO, livroModel);
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livroModel));
    }

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
