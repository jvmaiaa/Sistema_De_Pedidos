package com.example.SpringBootExpert.rest.controller;

import com.example.SpringBootExpert.domain.entity.ItemPedido;
import com.example.SpringBootExpert.domain.entity.Pedido;
import com.example.SpringBootExpert.domain.enums.StatusPedido;
import com.example.SpringBootExpert.rest.dto.AtualizacaoStatusPedidoDTO;
import com.example.SpringBootExpert.rest.dto.InformacaoItemPedidoDTO;
import com.example.SpringBootExpert.rest.dto.InformacoesPedidoDTO;
import com.example.SpringBootExpert.rest.dto.PedidoDTO;
import com.example.SpringBootExpert.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus( @RequestBody AtualizacaoStatusPedidoDTO dto, @PathVariable Integer id) {
        String novoStatus = dto.getNovoStatus();
        service.AtualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }


    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
