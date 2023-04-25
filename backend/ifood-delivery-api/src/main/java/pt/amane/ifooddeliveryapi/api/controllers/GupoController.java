package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.GrupoDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.GrupoInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.GrupoDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.GrupoInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Grupo;
import pt.amane.ifooddeliveryapi.domain.repositories.GrupoRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroGruoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GupoController {

    @Autowired
    private GrupoRepository repository;

    @Autowired
    private CadastroGruoService service;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoInputDataDisassembler grupoInputDataDisassembler;

    @GetMapping
    public List<GrupoDTO> findAll() {
        List<Grupo> grupos = repository.findAll();

        return grupoDTOAssembler.toCollectionModel(grupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO findById(@PathVariable Long grupoId) {
        Grupo grupo = service.findById(grupoId);

        return grupoDTOAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO create(@RequestBody @Valid GrupoInputData grupoInputData) {
        Grupo grupo = grupoInputDataDisassembler.toDomainObject(grupoInputData);
        grupo = service.create(grupo);

        return grupoDTOAssembler.toModel(grupo);
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO upadate(@PathVariable Long grupoId,
                              @RequestBody @Valid GrupoInputData grupoInputData) {
        Grupo grupoAtual = service.findById(grupoId);
        grupoInputDataDisassembler.copyToDomainObject(grupoInputData, grupoAtual);
        grupoAtual = service.create(grupoAtual);

        return grupoDTOAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {
        service.delete(cozinhaId);
    }

}
