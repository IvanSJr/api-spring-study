package com.github.ivansjr.apispring.medico.entity;

import com.github.ivansjr.apispring.endereco.dto.DadosAtualizarMedicoDTO;
import com.github.ivansjr.apispring.endereco.entity.Endereco;
import com.github.ivansjr.apispring.medico.dto.DadosCadastroMedicoDTO;
import com.github.ivansjr.apispring.medico.dto.Especialidade;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private String email;

    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = true;

    public Medico() {
    }

    public Medico(Long id, String nome, String crm, Especialidade especialidade, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.ativo = true;
    }

    public Medico(DadosCadastroMedicoDTO dadosCadastroMedicoDTO) {
        this.nome = dadosCadastroMedicoDTO.nome();
        this.crm = dadosCadastroMedicoDTO.crm();
        this.especialidade = dadosCadastroMedicoDTO.especialidade();
        this.email = dadosCadastroMedicoDTO.email();
        this.telefone = dadosCadastroMedicoDTO.telefone();
        this.endereco = new Endereco(dadosCadastroMedicoDTO.endereco());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Medico medico = (Medico) object;
        return Objects.equals(getId(), medico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public void atualizarInformacoes(DadosAtualizarMedicoDTO medicoDTO) {
        if (medicoDTO != null) {
            if (medicoDTO.nome() != null) {
                this.nome = medicoDTO.nome();
            }

            if (medicoDTO.telefone() != null) {
                this.telefone = medicoDTO.telefone();
            }

            if (medicoDTO.endereco() != null) {
                this.endereco.atualizarInformacoes(medicoDTO.endereco());
            }
        }
    }

    public void inativarMedico() {
        this.ativo = false;
    }
}
