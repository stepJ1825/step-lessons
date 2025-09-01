package by.step;

import by.step.model.Person;
import by.step.repository.PersonRepository;
import by.step.repository.PersonRepositoryWithOptional;

import java.util.Map;
import java.util.Optional;

public class Main4 {

    private static final PersonRepositoryWithOptional personRepository =
            new PersonRepositoryWithOptional(Map.of(1L,new Person(1, null)));

    public static void main(String[] args) {
        final Optional<Person> optPerson = personRepository.findById(1L);
        if (optPerson.isPresent()) {
            final Person person = optPerson.get();
            final String firstName = person.getFirstName();
            if (firstName != null) {
                System.out.println("Длина твоего имени: " + firstName.length());
            }
        }
    }
}
