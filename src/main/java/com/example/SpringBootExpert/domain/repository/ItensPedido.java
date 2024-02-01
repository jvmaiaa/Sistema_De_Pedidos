package com.example.SpringBootExpert.domain.repository;

import com.example.SpringBootExpert.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
