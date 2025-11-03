package ma.rest.spring;

import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;
import ma.rest.spring.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MsBanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBanqueApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            // Initialisation des données de test
            compteRepository.save(new Compte(null, 5000.0, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 2500.0, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 8000.0, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 1200.0, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 15000.0, new Date(), TypeCompte.EPARGNE));

            // Affichage des comptes créés
            compteRepository.findAll().forEach(c -> {
                System.out.println("✅ Compte créé: " + c.toString());
            });

            System.out.println("\n🎉 ===========================================");
            System.out.println("🎉 Application Spring Boot DÉMARRÉE !");
            System.out.println("🎉 ===========================================");
            System.out.println("🌐 URL de l'application: http://localhost:8082");
            System.out.println("📚 Swagger UI: http://localhost:8082/swagger-ui.html");
            System.out.println("🗄️ Console H2: http://localhost:8082/h2-console");
            System.out.println("📊 API Docs: http://localhost:8082/api-docs");
            System.out.println("🎉 ===========================================\n");
        };
    }
}