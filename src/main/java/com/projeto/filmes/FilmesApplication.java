package com.projeto.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FilmesApplication.class, args);
        System.out.println("\n==============================================");
        System.out.println("🎬 API de Gerenciamento de Filmes iniciada!");
        System.out.println("==============================================");
        System.out.println("📚 Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("📖 API Docs: http://localhost:8080/api-docs");
        System.out.println("🗄️ H2 Console: http://localhost:8080/h2-console");
        System.out.println("   JDBC URL: jdbc:h2:mem:filmesdb");
        System.out.println("   Username: sa");
        System.out.println("   Password: (deixe em branco)");
        System.out.println("==============================================\n");
    }
}