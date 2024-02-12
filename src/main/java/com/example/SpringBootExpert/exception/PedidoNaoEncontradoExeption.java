package com.example.SpringBootExpert.exception;

public class PedidoNaoEncontradoExeption extends RuntimeException {

    public PedidoNaoEncontradoExeption() {
        super("Pedido não encontrado");
    }
}
