package by.step;

import by.step.model.Person;
import by.step.repository.PersonRepository;

import java.util.Map;

public class Main1WithNPE {

    private static final PersonRepository personRepository = new PersonRepository(Map.of());

    public static void main(String[] args) {
        final Person person = personRepository.findById(1L);
        final String firstName = person.getFirstName();
        System.out.println("Длина твоего имени: " + firstName.length());
    }
}
