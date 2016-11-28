package br.edu.unirn.microservices.servicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CursoServMain {

	public static void main(String[] args) {
		SpringApplication.run(CursoServMain.class, args);
	}
}
