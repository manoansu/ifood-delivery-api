package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Pedido;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.PedidoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPedidoService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Pedido> findById(@PathVariable Long pedidoId) {

        Optional<Pedido> pedidoOptional = repository.findById(pedidoId);

        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido create(@RequestBody Pedido pedido) {
        return service.create(pedido);
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> update(@PathVariable Long pedidoId,
                                         @RequestBody Pedido pedido) {
        Optional<Pedido> pedidoPersistidoBd = repository.findById(pedidoId);

        if (pedidoPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(pedido, pedidoPersistidoBd.get(), "id");

            Pedido pedidoSalva = repository.save(pedidoPersistidoBd.get());
            return ResponseEntity.ok(pedidoSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{pedidoId}")
    public ResponseEntity<Pedido> delete(@PathVariable Long pedidoId) {

        try {
            service.delete(pedidoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
