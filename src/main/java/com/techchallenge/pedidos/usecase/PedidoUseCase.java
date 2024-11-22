package com.techchallenge.pedidos.usecase;


import com.techchallenge.pedidos.model.Pedido;
import com.techchallenge.pedidos.statemachine.EstadoPedido;
import com.techchallenge.pedidos.statemachine.EventoPedido;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoUseCase {
    ResponseEntity<String> criarPedido(Pedido pedido);

    ResponseEntity<Pedido> buscarPedido(long sequencia);

    ResponseEntity<String> atualizarEstadoPedido(long sequencia, EventoPedido eventoPedido);

    ResponseEntity<List<Pedido>> listarPedidoPorEstado(EstadoPedido estadoPedido);

    ResponseEntity<String> buscarStatusPedido(long sequencia);

    ResponseEntity<List<Pedido>> listarPedidosEmAndamento();
}
