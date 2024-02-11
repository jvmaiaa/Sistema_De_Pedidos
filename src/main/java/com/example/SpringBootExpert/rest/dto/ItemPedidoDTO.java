package com.example.SpringBootExpert.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private Integer idProduto;
    private Integer quantidade;
}
