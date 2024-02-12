package com.example.SpringBootExpert.service;

import com.example.SpringBootExpert.domain.entity.Pedido;
import com.example.SpringBootExpert.domain.enums.StatusPedido;
import com.example.SpringBootExpert.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void AtualizaStatus(Integer id, StatusPedido statusPedido);
}
