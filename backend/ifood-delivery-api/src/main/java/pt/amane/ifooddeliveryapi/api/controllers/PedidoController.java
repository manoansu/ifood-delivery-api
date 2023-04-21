package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPedidoService;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private CadastroPedidoService service;

    @GetMapping
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{pedidoId}")
    public Pedido findById(@PathVariable Long pedidoId) {
        return service.findById(pedidoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido create(@RequestBody Pedido pedido) {
        return service.create(pedido);
    }

    @PutMapping("/{pedidoId}")
    public Pedido update(@PathVariable Long pedidoId, @RequestBody Pedido pedido) {
        Pedido pedidoPersistidoBd = service.findById(pedidoId);

        BeanUtils.copyProperties(pedido, pedidoPersistidoBd, "id");

        return service.create(pedidoPersistidoBd);
    }
    @DeleteMapping(value = "/{pedidoId}")
    public void delete(@PathVariable Long pedidoId) {
        service.delete(pedidoId);
    }
}
