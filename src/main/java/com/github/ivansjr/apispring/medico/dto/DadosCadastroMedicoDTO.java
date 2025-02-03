package com.github.ivansjr.apispring.medico.dto;

import com.github.ivansjr.apispring.endereco.dto.DadosEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedicoDTO(
   @NotBlank
   String nome,
   @NotBlank @Email
   String email,
   @NotBlank @Pattern(regexp = "\\d{4,6}")
   String crm,
   @NotNull
   Especialidade especialidade,
   @NotBlank
   String telefone,
   @NotNull @Valid
   DadosEnderecoDTO endereco
) {}
