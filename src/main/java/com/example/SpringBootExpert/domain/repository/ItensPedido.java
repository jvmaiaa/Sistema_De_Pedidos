package com.example.SpringBootExpert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedido extends JpaRepository<ItensPedido, Integer> {
}
