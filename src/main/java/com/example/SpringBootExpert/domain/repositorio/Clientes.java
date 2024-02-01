package com.example.SpringBootExpert.domain.repositorio;

import com.example.SpringBootExpert.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Método save tanto salva quanto atualiza
@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

    // É possível utilizar SQL nativo ao invés de HQL
    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying // essa anotation é utiliza para os metodos delete ou update para informar que está realizando uma atualização no banco de dados
    void deleteByNome(String nome);

    boolean existsByNome(String nome);
}
