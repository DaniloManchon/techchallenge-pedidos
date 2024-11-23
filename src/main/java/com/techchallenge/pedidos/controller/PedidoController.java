package com.techchallenge.pedidos.controller;

import com.techchallenge.pedidos.model.Pedido;
import com.techchallenge.pedidos.statemachine.EstadoPedido;
import com.techchallenge.pedidos.statemachine.EventoPedido;
import com.techchallenge.pedidos.usecase.PedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoUseCase pedidoUseCase;

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
        try {
            return pedidoUseCase.validarClientePedido(pedido);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Pedido> buscarPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.buscarPedido(sequencia);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity listarPorEstado(@RequestParam("status") EstadoPedido estadoPedido) {
        try {
            return pedidoUseCase.listarPedidoPorEstado(estadoPedido);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paymentstatus")
    public ResponseEntity buscarStatusPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.buscarStatusPedido(sequencia);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //1 - pagar
    @GetMapping("/pago")
    public ResponseEntity<String> pagarPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.atualizarEstadoPedido(sequencia, EventoPedido.PAGAR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //2 - pedido recebido pela cozinha
    @PatchMapping("/recebido")
    public ResponseEntity<String> receberPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.atualizarEstadoPedido(sequencia, EventoPedido.RECEBER);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //3 - pedido em preparação
    @PatchMapping("/empreparacao")
    public ResponseEntity<String> prepararPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.atualizarEstadoPedido(sequencia, EventoPedido.PREPARAR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //4 - pedido pronto para ser entregue
    @PatchMapping("/pronto")
    public ResponseEntity<String> finalizarPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.atualizarEstadoPedido(sequencia, EventoPedido.APRONTAR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //5 - pedido entregue para o cliente
    @PatchMapping("/finalizado")
    public ResponseEntity<String> entregarPedido(@RequestParam("pedido") long sequencia) {
        try {
            return pedidoUseCase.atualizarEstadoPedido(sequencia, EventoPedido.ENTREGAR);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Pedido>> listarPedidosEmAndamento() {
        try {
            return pedidoUseCase.listarPedidosEmAndamento();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
