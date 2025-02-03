package com.github.ivansjr.apispring.medico.dto;

import com.github.ivansjr.apispring.endereco.entity.Endereco;
import com.github.ivansjr.apispring.medico.entity.Medico;

public record DadosDetalhementoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhementoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
