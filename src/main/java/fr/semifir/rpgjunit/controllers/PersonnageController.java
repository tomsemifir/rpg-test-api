package fr.semifir.rpgjunit.controllers;

import fr.semifir.rpgjunit.models.Personnage;
import fr.semifir.rpgjunit.services.impl.PersonnageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personnages")
public class PersonnageController {

    @Autowired
    private PersonnageService service;

    @GetMapping()
    public List<Personnage> findAll() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public Personnage findById(@PathVariable String id) {
        return this.service.findById(id);
    }

    @PostMapping()
    public Personnage create(@RequestBody Personnage personnage) {
        return this.service.create(personnage);
    }
}
