package fr.semifir.rpgjunit.repositories;

import fr.semifir.rpgjunit.models.Personnage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonnageRepository extends MongoRepository<Personnage, String> {
}
