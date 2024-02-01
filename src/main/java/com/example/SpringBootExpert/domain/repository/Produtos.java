package com.example.SpringBootExpert.domain.repository;

import com.example.SpringBootExpert.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produtos extends JpaRepository<Produto, Integer> {
}
