package com.example.sistema_gestao_biblioteca.responses;

import com.example.sistema_gestao_biblioteca.enums.Disponibilidade;
import com.example.sistema_gestao_biblioteca.models.AutorModel;

public class LivroResponse {
    private Long id;
    private String titulo;
    private Integer ano_publicacao;
    private String isbn;
    private Disponibilidade disponibilidade;
    private AutorResponse autor;

    public LivroResponse() {
    }

    public LivroResponse(Long id, String titulo, Integer ano_publicacao, String isbn, Disponibilidade disponibilidade, AutorResponse autor) {
        this.id = id;
        this.titulo = titulo;
        this.ano_publicacao = ano_publicacao;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
        this.autor = autor;
    }

    public LivroResponse(String titulo, Integer ano_publicacao, String isbn, Disponibilidade disponibilidade, AutorResponse autor) {
        this.titulo = titulo;
        this.ano_publicacao = ano_publicacao;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Integer ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    public void setAutor(AutorResponse autor) {
        this.autor = autor;
    }
}
