package by.step;

import by.step.model.Person;
import by.step.repository.PersonRepository;

import java.util.Map;

public class Main2WithNPE {

    private static final PersonRepository personRepository =
            new PersonRepository(Map.of(1L,new Person(1, null)));

    public static void main(String[] args) {
        final Person person = personRepository.findById(1L);
        if (person != null) {
            final String firstName = person.getFirstName();
            System.out.println("Длина твоего имени: " + firstName.length());
        }
    }
}
