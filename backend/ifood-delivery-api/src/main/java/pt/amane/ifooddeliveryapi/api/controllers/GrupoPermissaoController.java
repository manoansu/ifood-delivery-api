package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.PermissaoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.PermissaoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PermissaoDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroGruoService;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPermissaoService;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController {

    @Autowired
    private PermissaoRepository repository;

    @Autowired
    private CadastroPermissaoService service;

    @Autowired
    private CadastroGruoService cadastroGruoService;

    @Autowired
    private PermissaoDTOAssembler permissaoDTOAssembler;

    @Autowired
    private PermissaoInputDataDisassembler permissaoInputDataDisassembler;

    @GetMapping
    public List<PermissaoDTO> findAll(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGruoService.findById(grupoId);

        return permissaoDTOAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGruoService.desassociarPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGruoService.associarPermissao(grupoId, permissaoId);
    }

}
