package br.com.supera.game.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "br.com.supera.game.store.repository")
public class StoreApplication {
    public static void main(String[] args) {
       SpringApplication.run(StoreApplication.class, args);
    }
}
