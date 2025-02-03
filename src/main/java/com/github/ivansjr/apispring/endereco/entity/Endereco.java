package com.github.ivansjr.apispring.endereco.entity;

import com.github.ivansjr.apispring.endereco.dto.DadosEnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String complemento;
    private String numero;
    private String cep;
    private String uf;

    public Endereco() {
    }

    public Endereco(DadosEnderecoDTO dadosEnderecoDTO) {
        this.logradouro = dadosEnderecoDTO.logradouro();
        this.bairro = dadosEnderecoDTO.bairro();
        this.cidade = dadosEnderecoDTO.cidade();
        this.complemento = dadosEnderecoDTO.complemento();
        this.numero = dadosEnderecoDTO.numero();
        this.cep = dadosEnderecoDTO.cep();
        this.uf = dadosEnderecoDTO.uf();
    }

    public void atualizarInformacoes(DadosEnderecoDTO endereco) {
        if (endereco != null) {
            if (endereco.logradouro() != null) {
                this.logradouro = endereco.logradouro();
            }

            if (endereco.bairro() != null) {
                this.bairro = endereco.bairro();
            }

            if (endereco.cidade() != null) {
                this.cidade = endereco.cidade();
            }

            if (endereco.complemento() != null) {
                this.complemento = endereco.complemento();
            }

            if (endereco.numero() != null) {
                this.numero = endereco.numero();
            }

            if (endereco.cep() != null) {
                this.cep = endereco.cep();
            }

            if (endereco.uf() != null) {
                this.uf = endereco.uf();
            }
        }
    }
}
