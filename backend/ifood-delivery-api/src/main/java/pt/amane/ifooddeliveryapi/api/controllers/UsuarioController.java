package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeEmUsoException;
import pt.amane.ifooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import pt.amane.ifooddeliveryapi.domain.repositories.UsuarioRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroUsuarioService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Usuario> findById(@PathVariable Long usuarioId) {

        Optional<Usuario> usuarioOptional = repository.findById(usuarioId);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return service.create(usuario);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> update(@PathVariable Long usuarioId,
                                         @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioPersistidoBd = repository.findById(usuarioId);

        if (usuarioPersistidoBd.isPresent()) {
            BeanUtils.copyProperties(usuario, usuarioPersistidoBd.get(), "id");

            Usuario usuarioSalva = repository.save(usuarioPersistidoBd.get());
            return ResponseEntity.ok(usuarioSalva);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/{usuarioId}")
    public ResponseEntity<Usuario> delete(@PathVariable Long usuarioId) {

        try {
            service.delete(usuarioId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
