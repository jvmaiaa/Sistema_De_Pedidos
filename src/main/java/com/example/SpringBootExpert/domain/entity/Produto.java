package com.example.SpringBootExpert.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRICAO")
    @NotEmpty(message = "Campo Descrição é obrigatório.")
    private String descricao;

    @Column(name = "PRECO_UNITARIO")
    @NotNull(message = "Campo Preço é obrigatório.")
    private BigDecimal preco;

}



