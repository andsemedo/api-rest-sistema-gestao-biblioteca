package com.example.sistema_gestao_biblioteca.models;

import com.example.sistema_gestao_biblioteca.enums.Disponibilidade;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "livro")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer ano_publicacao;
    @Column(unique = true)
    private String isbn;
    private Disponibilidade disponibilidade;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;

    public LivroModel() {
    }

    public LivroModel(String titulo, Integer ano_publicacao, String isbn, Disponibilidade disponibilidade, AutorModel autor) {
        this.titulo = titulo;
        this.ano_publicacao = ano_publicacao;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
        this.autor = autor;
    }

    public LivroModel(Long id, String titulo, Integer ano_publicacao, String isbn, Disponibilidade disponibilidade, AutorModel autor) {
        this.id = id;
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

    public AutorModel getAutor() {
        return autor;
    }

    public void setAutor(AutorModel autor) {
        this.autor = autor;
    }
}
