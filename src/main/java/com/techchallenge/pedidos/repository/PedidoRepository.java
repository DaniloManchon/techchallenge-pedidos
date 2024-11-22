package com.techchallenge.pedidos.repository;


import com.techchallenge.pedidos.model.Pedido;
import com.techchallenge.pedidos.statemachine.EstadoPedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
    Optional<Pedido> findBySequencia(Long sequencia);

    List<Pedido> findAllByEstadoPedido(EstadoPedido estadoPedido);
    List<Pedido> findAllByEstadoPedidoOrderByDataPedido(EstadoPedido estadoPedido);
}
