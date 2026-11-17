package com.julia.agendamento_mecanico.handler;

import com.julia.agendamento_mecanico.exception.RecursoNaoEncontradoException;
import com.julia.agendamento_mecanico.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> recursoNaoEncontrado(RecursoNaoEncontradoException ex){

        return Map.of("erro", ex.getMessage());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> regraDeNegocio(RegraDeNegocioException ex){

        return Map.of("erro", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validacao(MethodArgumentNotValidException ex){

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        erros.put(error.getField(),
                                error.getDefaultMessage()));

        ex.getBindingResult().getGlobalErrors()
                .forEach(error ->
                        erros.put("global", error.getDefaultMessage()));

        return erros;
    }

    //pensando no enum
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> jsonInvalido(HttpMessageNotReadableException ex){

        if (ex.getCause() instanceof InvalidFormatException ife) {

            String campo = ife.getPath().isEmpty() ? "campo"
                    : ife.getPath().get(ife.getPath().size() - 1).getPropertyName();

            String mensagem = "Valor inválido";
            if (ife.getTargetType() != null && ife.getTargetType().isEnum()) {
                mensagem += ". Valores aceitos: "
                        + Arrays.toString(ife.getTargetType().getEnumConstants());
            }
            return Map.of(campo, mensagem);
        }

        return Map.of("erro", "Corpo da requisição inválido");
    }
}