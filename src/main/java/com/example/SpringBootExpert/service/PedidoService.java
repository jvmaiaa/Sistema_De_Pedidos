package com.example.SpringBootExpert.service;

import com.example.SpringBootExpert.domain.entity.Pedido;
import com.example.SpringBootExpert.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
