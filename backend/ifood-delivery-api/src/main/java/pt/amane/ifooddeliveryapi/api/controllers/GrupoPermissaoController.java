package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.PermissaoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.PermissaoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.PermissaoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.PermissaoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.entities.Permissao;
import pt.amane.ifooddeliveryapi.domain.repositories.PermissaoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroGruoService;
import pt.amane.ifooddeliveryapi.domain.services.CadastroPermissaoService;

import javax.validation.Valid;
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

    @GetMapping("/{permissaoId}")
    public PermissaoDTO findById(@PathVariable Long permissaoId) {
        Permissao permissao = service.findById(permissaoId);

        return permissaoDTOAssembler.toModel(permissao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoDTO create(@RequestBody @Valid PermissaoInputData permissaoInputData) {
        Permissao permissao = permissaoInputDataDisassembler.toDomainObject(permissaoInputData);
        permissao = service.create(permissao);

        return permissaoDTOAssembler.toModel(permissao);
    }

    @PutMapping("/{permissaoId}")
    public PermissaoDTO upadate(@PathVariable Long permissaoId,
                              @RequestBody @Valid PermissaoInputData permissaoInputData) {
        Permissao permissaoAtual = service.findById(permissaoId);
        permissaoInputDataDisassembler.copyToDomainObject(permissaoInputData, permissaoAtual);
        permissaoAtual = service.create(permissaoAtual);

        return permissaoDTOAssembler.toModel(permissaoAtual);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long permissaoId) {
        service.delete(permissaoId);
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
