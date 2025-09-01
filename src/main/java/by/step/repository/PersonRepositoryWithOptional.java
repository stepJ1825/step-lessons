package by.step.repository;

import by.step.model.Person;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonRepositoryWithOptional {

    private final Map<Long, Person> persons;

    public PersonRepositoryWithOptional(Map<Long, Person> persons) {
        this.persons = persons;
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(persons.get(id));

    }

}