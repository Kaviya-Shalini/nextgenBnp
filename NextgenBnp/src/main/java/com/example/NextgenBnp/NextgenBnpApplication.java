package com.example.NextgenBnp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class NextgenBnpApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextgenBnpApplication.class, args);
	}

}
