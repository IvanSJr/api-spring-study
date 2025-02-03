package com.github.ivansjr.apispring.medico.service;

import com.github.ivansjr.apispring.endereco.dto.DadosAtualizarMedicoDTO;
import com.github.ivansjr.apispring.medico.dto.DadosCadastroMedicoDTO;
import com.github.ivansjr.apispring.medico.dto.DadosDetalhementoMedico;
import com.github.ivansjr.apispring.medico.dto.DadosListagemMedico;
import com.github.ivansjr.apispring.medico.entity.Medico;
import com.github.ivansjr.apispring.medico.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public DadosDetalhementoMedico cadastrar(DadosCadastroMedicoDTO dadosCadastroMedicoDTO) {
        Medico medico = new Medico(dadosCadastroMedicoDTO);
        Medico medicoSalvoNoBanco = medicoRepository.save(medico);
        return new DadosDetalhementoMedico(medicoSalvoNoBanco);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemMedico> listarMedicos(Pageable pageable) {
        return medicoRepository.findAlLByAtivoIsTrue(pageable).map(DadosListagemMedico::new);
    }

    public DadosListagemMedico atualizarMedico(Long id, DadosAtualizarMedicoDTO medicoDTO) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.atualizarInformacoes(medicoDTO);
            return new DadosListagemMedico(optionalMedico.get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void excluirMedico(Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.inativarMedico();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public DadosDetalhementoMedico detalharMedicoPeloId(Long id) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            return new DadosDetalhementoMedico(medico);
        } else {
            throw new EntityNotFoundException();
        }

    }
}
