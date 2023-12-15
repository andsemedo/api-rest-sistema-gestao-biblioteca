package com.example.sistema_gestao_biblioteca.models;

import com.example.sistema_gestao_biblioteca.enums.Disponibilidade;
import jakarta.persistence.*;

@Entity
@Table
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String autor;
    private Integer ano_publicacao;
    private String isbn;
    private Disponibilidade disponibilidade;

    public LivroModel() {
    }

    public LivroModel(String titulo, String autor, Integer ano_publicacao, String isbn, Disponibilidade disponibilidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
    }

    public LivroModel(Long id, String titulo, String autor, Integer ano_publicacao, String isbn, Disponibilidade disponibilidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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
}
