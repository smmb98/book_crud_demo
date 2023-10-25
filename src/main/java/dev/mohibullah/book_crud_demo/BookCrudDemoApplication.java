package dev.mohibullah.book_crud_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookCrudDemoApplication {
	@Value("${spring.datasource.url}")
	private String databaseUrl;
	public static void main(String[] args) {
		SpringApplication.run(BookCrudDemoApplication.class, args);


	}

}
