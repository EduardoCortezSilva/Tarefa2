package com.eep.tarefa2.controller;

import java.util.NoSuchElementException;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Converte exceções em respostas JSON {"erro": "..."} (montadas com org.json),
 * que o JavaScript das páginas exibe ao usuário.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private ResponseEntity<String> resposta(HttpStatus status, String mensagem) {
        String corpo = new JSONObject().put("erro", mensagem).toString();
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(corpo);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> naoEncontrado(NoSuchElementException e) {
        return resposta(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> conflito(IllegalStateException e) {
        return resposta(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> invalido(IllegalArgumentException e) {
        return resposta(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validacao(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getDefaultMessage())
                .findFirst().orElse("Dados inválidos");
        return resposta(HttpStatus.BAD_REQUEST, msg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> jsonInvalido(HttpMessageNotReadableException e) {
        return resposta(HttpStatus.BAD_REQUEST, "Dados em formato inválido");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> integridade(DataIntegrityViolationException e) {
        return resposta(HttpStatus.CONFLICT, "Operação viola a integridade dos dados do banco");
    }
}
