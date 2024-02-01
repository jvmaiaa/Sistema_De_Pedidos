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
			System.out.println("Salvando clientes");
			clientes.save(new Cliente("José"));
			clientes.save(new Cliente("Outro cliente"));

			boolean existe = clientes.existsByNome("João");
			System.out.println("Existe um cliente com o nome João? " + existe);
		};
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootExpertApplication.class, args);
	}

}
