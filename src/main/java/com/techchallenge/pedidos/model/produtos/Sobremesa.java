package com.techchallenge.pedidos.model.produtos;

import com.techchallenge.pedidos.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sobremesa extends Produto {
    public Sobremesa(String nome, String nomeBanco, String descricao, float preco) {
        super(nome, nomeBanco, descricao, preco);
    }
}
