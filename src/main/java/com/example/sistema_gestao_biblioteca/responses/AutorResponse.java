package com.example.sistema_gestao_biblioteca.responses;

import com.example.sistema_gestao_biblioteca.models.LivroModel;

import java.util.ArrayList;
import java.util.List;

public class AutorResponse {
    private Long id;
    private String nome;
    private List<LivroModel> listaLivros = new ArrayList<LivroModel>();

    public AutorResponse() {
    }

    public AutorResponse(String nome, List<LivroModel> listaLivros) {
        this.nome = nome;
        this.listaLivros = listaLivros;
    }

    public AutorResponse(Long id, String nome, List<LivroModel> listaLivros) {
        this.id = id;
        this.nome = nome;
        this.listaLivros = listaLivros;
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
