package com.github.ivansjr.apispring.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<DadosErroValidacao> dadosErroValidacaos = fieldErrors.stream().map(DadosErroValidacao::new).toList();
        return ResponseEntity.badRequest().body(dadosErroValidacaos);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> tratarErroDeIntegridade(DataIntegrityViolationException ex) {
        String mensagem = "Erro de integridade de dados. " + extrairMensagemMaisEspecifica(ex);
        return ResponseEntity.status(409).body(mensagem);
    }

    public record DadosErroValidacao(String campo, String message) {
        public DadosErroValidacao(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    private String extrairMensagemMaisEspecifica(DataIntegrityViolationException ex) {
        Throwable cause = ex.getMostSpecificCause();
        return cause.getMessage();
    }
}
