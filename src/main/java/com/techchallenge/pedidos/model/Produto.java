package com.techchallenge.pedidos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private String id;
    private String nome;
    //nome do produto em snake case para facilitar buscas no banco de dados
    private String nomeBanco;
    private String descricao;
    private float preco;

    public Produto(String nome, String nomeBanco, String descricao, float preco) {
        this.nome = nome;
        this.nomeBanco = nomeBanco;
        this.descricao = descricao;
        this.preco = preco;
    }
}
