package fr.semifir.rpgjunit.config;


import fr.semifir.rpgjunit.repositories.PersonnageRepository;
import fr.semifir.rpgjunit.services.impl.PersonnageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigService {

    @Autowired
    private PersonnageRepository personnageRepository;

    @Bean
    public PersonnageService personnageServiceFactory() {
        return new PersonnageService(personnageRepository);
    }
}
