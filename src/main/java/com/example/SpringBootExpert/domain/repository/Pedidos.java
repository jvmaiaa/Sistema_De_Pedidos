package com.example.SpringBootExpert.domain.repository;

import com.example.SpringBootExpert.domain.entity.Cliente;
import com.example.SpringBootExpert.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);
}
