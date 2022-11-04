package com.claudio.padroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado via Spring Initializr
 * O seguites módulos foram selecionados:
 *  - Spring Data JPA
 *  - Spring Web
 *  - H2 database
 *  - Openfeign
 */
@EnableFeignClients
@SpringBootApplication
public class PadroesProjetoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesProjetoSpringApplication.class, args);
	}

}
