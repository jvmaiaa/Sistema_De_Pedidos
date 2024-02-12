package com.example.SpringBootExpert.rest.dto;

import com.example.SpringBootExpert.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente.")
    private Integer idCliente;

    @NotNull(message = "Campo Total do pedido é obrigatório.")
    private BigDecimal total;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> items;
}
