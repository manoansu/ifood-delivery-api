package pt.amane.ifooddeliveryapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.amane.ifooddeliveryapi.api.assembler.CozinhaDTOAssembler;
import pt.amane.ifooddeliveryapi.api.assembler.CozinhaInputDataDisassembler;
import pt.amane.ifooddeliveryapi.api.model.modeldto.CozinhaDTO;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.CozinhaInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Cozinha;
import pt.amane.ifooddeliveryapi.domain.repositories.CozinhaRepository;
import pt.amane.ifooddeliveryapi.domain.services.CadastroCozinhaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private CadastroCozinhaService service;

    @Autowired
    private CozinhaDTOAssembler cozinhaDTOAssembler;

    @Autowired
    private CozinhaInputDataDisassembler cozinhaInputDataDisassembler;

    @GetMapping
    public Page<CozinhaDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhaPage = repository.findAll(pageable);

        List<CozinhaDTO> cozinhaDTOS =  cozinhaDTOAssembler.toCollectionModel(cozinhaPage.getContent());

        Page<CozinhaDTO> cozinhaDTOPage = new PageImpl<>(cozinhaDTOS,pageable,cozinhaPage.getTotalPages());

        return cozinhaDTOPage;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO findById(@PathVariable Long cozinhaId) {
        Cozinha cozinha = service.findById(cozinhaId);

        return cozinhaDTOAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO create(@RequestBody @Valid CozinhaInputData cozinhaInputData) {
        Cozinha cozinha = cozinhaInputDataDisassembler.toDomainObject(cozinhaInputData);
        cozinha = service.create(cozinha);

        return cozinhaDTOAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO upadate(@PathVariable Long cozinhaId,
                                  @RequestBody @Valid CozinhaInputData cozinhaInputData) {
        Cozinha cozinhaAtual = service.findById(cozinhaId);
        cozinhaInputDataDisassembler.copyToDomainObject(cozinhaInputData, cozinhaAtual);
        cozinhaAtual = service.create(cozinhaAtual);

        return cozinhaDTOAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {
        service.delete(cozinhaId);
    }

}
