package com.example.SpringBootExpert.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private Integer idCliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
