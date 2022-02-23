package fr.semifir.rpgjunit.services.impl;

import fr.semifir.rpgjunit.models.Personnage;
import fr.semifir.rpgjunit.repositories.PersonnageRepository;
import fr.semifir.rpgjunit.services.GenericService;

import java.util.List;
import java.util.Optional;

public class PersonnageService implements GenericService<Personnage> {

    private PersonnageRepository repository;

    public PersonnageService(PersonnageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Personnage> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Personnage findById(String id) {
        Personnage returnedPersonnage = null;
        Optional<Personnage> p = this.repository.findById(id);

        if(p.isPresent()) {
            returnedPersonnage = p.get();
        } else {
            throw new NullPointerException("L'utilisateur est introuvable");
        }

        return returnedPersonnage;
    }

    @Override
    public Personnage create(Personnage entity) {
        if(entity.getNom().equals("") || entity.getNom() == null) {
            throw new IllegalArgumentException("Le personnage doit avoir un nom");
        }
        return this.repository.save(entity);
    }

    @Override
    public Personnage update(String id, Personnage entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }
}
