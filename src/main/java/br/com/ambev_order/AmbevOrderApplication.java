package br.com.ambev_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AmbevOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmbevOrderApplication.class, args);
	}

}
