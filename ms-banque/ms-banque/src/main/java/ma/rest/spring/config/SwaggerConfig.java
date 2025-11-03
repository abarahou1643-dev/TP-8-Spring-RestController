package ma.rest.spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration de Swagger/OpenAPI
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI msBanqueOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservice Banque API")
                        .description("API REST pour la gestion des comptes bancaires")
                        .version("v1.0.0"));
    }
}