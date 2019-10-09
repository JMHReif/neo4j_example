package com.example.neo4j_example;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @GetMapping("/search")
    public Person getByName(@RequestParam String name) {
        return personRepository.findByName(name);
    }

    @GetMapping("/teammates")
    public Set<Person> findTeammates(String name) { return personRepository.findTeammates(name); }
}
