package com.example.SpringBootExpert.rest.controller;

import com.example.SpringBootExpert.exception.PedidoNaoEncontradoExeption;
import com.example.SpringBootExpert.exception.RegraNegocioExeption;
import com.example.SpringBootExpert.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioExeption ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoExeption ex){
        return new ApiErrors(ex.getMessage());
    }

}
