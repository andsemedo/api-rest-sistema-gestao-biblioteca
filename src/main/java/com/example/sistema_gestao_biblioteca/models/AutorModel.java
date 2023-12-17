package com.example.sistema_gestao_biblioteca.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AutorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<LivroModel> listaLivros;

    public AutorModel() {
    }

    public AutorModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public AutorModel(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LivroModel> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<LivroModel> listaLivros) {
        this.listaLivros = listaLivros;
    }
}
