package com.example.SpringBootExpert.domain.repository;

import com.example.SpringBootExpert.domain.entity.Cliente;
import com.example.SpringBootExpert.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.PresentationDirection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);

    @Query("select p from Pedido p left join fetch p.itens where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
