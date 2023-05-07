package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.GrupoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.UsuarioDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.UsuarioInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.GrupoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.UsuarioDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.SenhaInputData;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.UsuarioComSenhaInputData;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.UsuarioInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;
import pt.amane.ifooddeliveryapi.domain.services.CadastroUsuarioService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService service;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private UsuarioInputDataDisassembler usuarioInputDataDisassembler;

    @GetMapping
    public List<GrupoDTO> findAll(@PathVariable Long usuarioId) {
        Usuario usuario = service.findById(usuarioId);

        return grupoDTOAssembler.toCollectionModel(usuario.getGrupos());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO findById(@PathVariable Long usuarioId) {
        Usuario usuario = service.findById(usuarioId);

        return usuarioDTOAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO create(@RequestBody @Valid UsuarioComSenhaInputData usuarioComSenhaInputData) {
        Usuario usuario = usuarioInputDataDisassembler.toDomainObject(usuarioComSenhaInputData);
        usuario = service.create(usuario);

        return usuarioDTOAssembler.toModel(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO upadate(@PathVariable Long usuarioId,
                              @RequestBody @Valid UsuarioInputData usuarioInputData) {
        Usuario usuarioAtual = service.findById(usuarioId);
        usuarioInputDataDisassembler.copyToDomainObject(usuarioInputData, usuarioAtual);
        usuarioAtual = service.create(usuarioAtual);

        return usuarioDTOAssembler.toModel(usuarioAtual);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long usuarioId) {
        service.delete(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInputData senha) {
        service.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
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
