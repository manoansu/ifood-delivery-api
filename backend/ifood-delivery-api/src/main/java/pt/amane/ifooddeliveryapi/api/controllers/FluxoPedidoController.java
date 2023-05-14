package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.services.FluxoPedidoService;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedidoId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping(value = "/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String  codigoPedidoId) {
        fluxoPedidoService.confirmar(codigoPedidoId);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String  codigoPedidoId) {
        fluxoPedidoService.cancelar(codigoPedidoId);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String  codigoPedidoId) {
        fluxoPedidoService.entregar(codigoPedidoId);
    }
}
