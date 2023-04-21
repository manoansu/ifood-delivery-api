package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.repositories.UsuarioRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroUsuarioService;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CadastroUsuarioService service;

    @GetMapping
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{usuarioId}")
    public Usuario findById(@PathVariable Long usuarioId) {
        return service.findById(usuarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return service.create(usuario);
    }

    @PutMapping("/{usuarioId}")
    public Usuario update(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
        Usuario usuarioPersistidoBd = service.findById(usuarioId);

        BeanUtils.copyProperties(usuario, usuarioPersistidoBd, "id");

        return service.create(usuarioPersistidoBd);
    }
    @DeleteMapping(value = "/{usuarioId}")
    public void delete(@PathVariable Long usuarioId) {
        service.delete(usuarioId);
    }
}
