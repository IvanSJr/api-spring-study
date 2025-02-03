package com.github.ivansjr.apispring.controller;

import com.github.ivansjr.apispring.endereco.dto.DadosAtualizarMedicoDTO;
import com.github.ivansjr.apispring.medico.dto.DadosCadastroMedicoDTO;
import com.github.ivansjr.apispring.medico.dto.DadosDetalhementoMedico;
import com.github.ivansjr.apispring.medico.dto.DadosListagemMedico;
import com.github.ivansjr.apispring.medico.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhementoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        DadosDetalhementoMedico medico = medicoService.cadastrar(medicoDTO);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.id()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DadosListagemMedico>>> listar(
        @PageableDefault(size = 10, sort = { "nome" }) Pageable page,
        PagedResourcesAssembler<DadosListagemMedico> pagedResourcesAssembler
    ) {
        return ResponseEntity.ok().body(
            pagedResourcesAssembler.toModel(medicoService.listarMedicos(page))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhementoMedico> detalharMedico(@PathVariable Long id) {
        return ResponseEntity.ok().body(medicoService.detalharMedicoPeloId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosListagemMedico> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizarMedicoDTO medicoDTO
    ) {
        return ResponseEntity.ok().body(medicoService.atualizarMedico(id, medicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }

}
