package com.techchallenge.pedidos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private String id;
    private String cpf;
    private String nome;
    private String email;
    private boolean marketing;

    public Cliente(String cpf, String nome, String email, boolean marketing) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.marketing = marketing;
    }
}
