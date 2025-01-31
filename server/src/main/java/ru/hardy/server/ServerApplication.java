package ru.hardy.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		log.info("ServerApplication START");

	}
//	@Bean
//	public WebServerFactoryCustomizer webServerFactoryCustomizer() {
//		return factory -> {
//            factory.
//        };
//	}

}
