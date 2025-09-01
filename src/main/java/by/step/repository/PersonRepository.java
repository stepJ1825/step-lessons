package by.step.repository;

import by.step.model.Person;

import java.util.Map;

public class PersonRepository {

    private final Map<Long, Person> persons;

    public PersonRepository(Map<Long, Person> persons) {
        this.persons = persons;
    }

    public Person findById(Long id) {
        return persons.get(id);
    }

}