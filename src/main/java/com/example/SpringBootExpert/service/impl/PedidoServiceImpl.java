package com.example.SpringBootExpert.service.impl;

import com.example.SpringBootExpert.domain.entity.Cliente;
import com.example.SpringBootExpert.domain.entity.ItemPedido;
import com.example.SpringBootExpert.domain.entity.Pedido;
import com.example.SpringBootExpert.domain.entity.Produto;
import com.example.SpringBootExpert.domain.enums.StatusPedido;
import com.example.SpringBootExpert.domain.repository.Clientes;
import com.example.SpringBootExpert.domain.repository.ItensPedido;
import com.example.SpringBootExpert.domain.repository.Pedidos;
import com.example.SpringBootExpert.domain.repository.Produtos;
import com.example.SpringBootExpert.exception.PedidoNaoEncontradoExeption;
import com.example.SpringBootExpert.exception.RegraNegocioExeption;
import com.example.SpringBootExpert.rest.dto.ItemPedidoDTO;
import com.example.SpringBootExpert.rest.dto.PedidoDTO;
import com.example.SpringBootExpert.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itemsPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioExeption("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void AtualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow( () -> new PedidoNaoEncontradoExeption());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioExeption("Não é possível realizar um pedido sem itens.");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getIdProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioExeption(
                                            "Código de produto Inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
