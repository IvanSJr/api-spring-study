package com.github.ivansjr.apispring.endereco.dto;

public record DadosAtualizarMedicoDTO(
    String telefone,
    String nome,
    DadosEnderecoDTO endereco
) {}
