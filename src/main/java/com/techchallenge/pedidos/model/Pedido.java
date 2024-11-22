package com.techchallenge.pedidos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techchallenge.pedidos.model.produtos.Acompanhamento;
import com.techchallenge.pedidos.model.produtos.Bebida;
import com.techchallenge.pedidos.model.produtos.Lanche;
import com.techchallenge.pedidos.model.produtos.Sobremesa;
import com.techchallenge.pedidos.statemachine.EstadoPedido;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Pedido {

    @Id
    private String id;

    @JsonProperty("estado_pedido")
    private EstadoPedido estadoPedido;

    //numero do pedido
    private long sequencia;

    @Nullable
    private Cliente cliente;
    @Nullable
    private List<Lanche> lanches;
    @Nullable
    private List<Bebida> bebidas;
    @Nullable
    private List<Sobremesa> sobremesas;
    @Nullable
    private List<Acompanhamento> acompanhamentos;

    @JsonProperty(value = "data_pedido", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataPedido;

    public Pedido(long sequencia, @Nullable Cliente cliente, @Nullable List<Lanche> lanches, @Nullable List<Bebida> bebidas, @Nullable List<Sobremesa> sobremesas, @Nullable List<Acompanhamento> acompanhamentos) {
        this.estadoPedido = EstadoPedido.A_PAGAR;
        this.sequencia = sequencia;
        this.cliente = cliente;
        this.lanches = lanches;
        this.bebidas = bebidas;
        this.sobremesas = sobremesas;
        this.acompanhamentos = acompanhamentos;
        this.dataPedido = LocalDateTime.now();
    }
}
