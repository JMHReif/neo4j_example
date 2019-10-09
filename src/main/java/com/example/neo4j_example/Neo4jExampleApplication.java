package com.example.neo4j_example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jExampleApplication {

    private final static Logger log = LoggerFactory.getLogger(Neo4jExampleApplication.class);

    @Bean
    CommandLineRunner demo(PersonRepository personRepository) {
        return args -> {
            personRepository.deleteAll();

            Person amy = new Person("Amy");
            Person ben = new Person("Ben");
            Person christine = new Person("Christine");

            List<Person> team = Arrays.asList(amy, ben, christine);

            log.info("Before linking up with Neo4j...");

            team.stream().forEach(person -> log.info("\t" + person.toString()));

            personRepository.save(amy);
            personRepository.save(ben);
            personRepository.save(christine);

            amy = personRepository.findByName(amy.getName());
            amy.worksWith(ben);
            amy.worksWith(christine);
            personRepository.save(amy);

            ben = personRepository.findByName(ben.getName());
            ben.worksWith(christine);
            // We already know that ben works with amy
            personRepository.save(ben);

            // We already know christine works with amy and ben

            log.info("Look up each person by name...");
            team.stream().forEach(person -> log.info(
                    // "\t" + personRepository.findByName(person.getName()).toString()));
                    "\t" + personRepository.findByName(person.getName()).toString()));
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(Neo4jExampleApplication.class, args);
	}
}
