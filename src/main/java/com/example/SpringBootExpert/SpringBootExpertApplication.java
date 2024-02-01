package com.example.SpringBootExpert;

import com.example.SpringBootExpert.domain.entity.Cliente;
import com.example.SpringBootExpert.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootExpertApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			clientes.save(new Cliente("João"));
			clientes.save(new Cliente("Outro cliente"));

			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);

			System.out.println("Atualizando clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.save(c);
			});

			todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando clientes");
			clientes.findByNomeLike("Cli").forEach(System.out::println);

			System.out.println("deletando clientes");
			clientes.findAll().forEach(c -> {
				clientes.delete(c);
			});

			todosClientes = clientes.findAll();
			if(todosClientes.isEmpty()){
				System.out.println("Nenhum cliente encontrado.");
			}else{
				todosClientes.forEach(System.out::println);
			}
		};
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}
