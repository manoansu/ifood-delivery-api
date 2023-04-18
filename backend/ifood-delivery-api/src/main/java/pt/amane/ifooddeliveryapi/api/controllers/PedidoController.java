package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public List<Pedido> listar() {
        return repository.listar();
    }
}
