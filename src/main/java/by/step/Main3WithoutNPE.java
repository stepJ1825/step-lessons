package by.step;

import by.step.model.Person;
import by.step.repository.PersonRepository;

import java.util.Map;

public class Main3WithoutNPE {

    private static final PersonRepository personRepository =
            new PersonRepository(Map.of(1L,new Person(1, null)));

    public static void main(String[] args) {
        Person person = personRepository.findById(1L);
        if (person != null) {
            String firstName = person.getFirstName();
            if (firstName != null) {
                System.out.println("Длина твоего имени: " + firstName.length());
            }
        }
    }
}
