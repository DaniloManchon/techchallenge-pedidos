package com.techchallenge.pedidos.model.produtos;

import com.techchallenge.pedidos.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bebida extends Produto {
    private List<String> tamanhos;

    public Bebida(String nome, String nomeBanco, String descricao, float preco, List<String> tamanhos) {
        super(nome, nomeBanco, descricao, preco);
        this.tamanhos = tamanhos;
    }
}
