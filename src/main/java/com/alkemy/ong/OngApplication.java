package com.alkemy.ong;

import com.alkemy.ong.service.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class OngApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OngApplication.class, args);

	}

	// Esta parte es solo para hacer la prueba del método. Colocar un mail real para comprobar la recepción
	@Bean
	CommandLineRunner run(EmailService emailService) {
		return args -> {
			emailService.sendWelcomeMail("mail_generico@mail_generico.com");
		};

	}
}
