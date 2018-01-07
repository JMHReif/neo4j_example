package com.example.neo4j_example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
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
    public Person getById(@PathVariable Long id) {
        return personRepository.findOne(id);
    }

    @GetMapping("/search")
    public Person getByName(@RequestParam String name) {
        return personRepository.findByName(name);
    }
}
