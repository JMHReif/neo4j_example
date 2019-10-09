package com.example.neo4j_example;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Set;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person findByName(String name);

    @Query("MATCH (p:Person)-[rel:IS_TEAMMATE_OF]-(other:Person) WHERE p.name = {name} RETURN other")
    Set<Person> findTeammates(String name);
}
