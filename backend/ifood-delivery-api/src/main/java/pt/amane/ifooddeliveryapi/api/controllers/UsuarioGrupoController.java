package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.GrupoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.GrupoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.services.CadastroUsuarioService;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService service;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @GetMapping
    public List<GrupoDTO> findAll(@PathVariable Long usuarioId) {
        Usuario usuario = service.findById(usuarioId);

        return grupoDTOAssembler.toCollectionModel(usuario.getGrupos());
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        service.desassociarGrupo(usuarioId, grupoId);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        service.associarGrupo(usuarioId, grupoId);
    }

}
