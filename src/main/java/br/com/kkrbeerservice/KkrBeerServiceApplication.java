package br.com.kkrbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class KkrBeerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KkrBeerServiceApplication.class, args);
	}

}
