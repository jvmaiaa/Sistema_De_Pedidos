package com.example.SpringBootExpert.rest.controller;

import com.example.SpringBootExpert.domain.entity.Cliente;
import com.example.SpringBootExpert.domain.repository.Clientes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController( Clientes clientes ){
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteById(@PathVariable("id") Integer id){
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> find(){
        return clientes.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( @RequestBody @Valid Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        clientes.findById(id)
                .map( cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        clientes.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return cliente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

}
