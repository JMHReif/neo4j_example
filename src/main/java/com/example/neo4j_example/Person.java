package com.example.neo4j_example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnoreProperties("teammates")
    @Relationship(type = "IS_TEAMMATE_OF", direction = "UNDIRECTED")
    private Set<Person> teammates;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public void worksWith(Person person) {
        if (this.teammates == null) {
            this.teammates = new HashSet<>();
        }
        this.teammates.add(person);
    }

    public String toString() {
        return this.id + ": " + this.name + "'s teammates => "
                + Optional.ofNullable(teammates).orElse(
                Collections.emptySet()).stream().map(
                Person::getName).collect(Collectors.toList());
    }

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}