package dev.mohibullah.book_crud_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

////////////////////////////////////////////////////////////////////////////////////////////////
// This class is the main entry point for the Spring Boot application.
// It defines the application's configuration, initializes the Spring context,
// and provides a custom message that is displayed when the application starts, 
// showing the server's address and the database's URL.
////////////////////////////////////////////////////////////////////////////////////////////////

@SpringBootApplication
public class BookCrudDemoApplication {
    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    public static void main(String[] args) {
        // Entry point of the Spring Boot application.
        SpringApplication.run(BookCrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        // CommandLineRunner bean that provides a custom startup message.
        return args -> {
            // Display server and database information upon application startup.
            System.out.println("\n\nServer started on http://localhost:" + serverPort);
            System.out.println("Database running on " + databaseUrl + "\n\n");
        };
    }
}
