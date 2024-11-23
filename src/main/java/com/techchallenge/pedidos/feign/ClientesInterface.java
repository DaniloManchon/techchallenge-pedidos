package com.techchallenge.pedidos.feign;

import com.techchallenge.pedidos.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "Clientes", name = "cliente", url = "http://localhost:8081/cliente")
public interface ClientesInterface {
    @GetMapping
    ResponseEntity<Cliente> validarCliente (@RequestParam("cpf") String cpf);

    @PostMapping
    ResponseEntity<Cliente> criarCliente (Cliente cliente);
}
