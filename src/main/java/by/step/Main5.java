package by.step;

import by.step.model.Person;
import by.step.repository.PersonRepositoryWithOptional;

import java.util.Map;
import java.util.Optional;

public class Main5 {

    private static final PersonRepositoryWithOptional personRepository =
            new PersonRepositoryWithOptional(Map.of(1L,new Person(1, null)));

    public static void main(String[] args) {


        final Optional<Person> optPerson = personRepository.findById(1L);
        optPerson.map(person -> person.getFirstName())
               .ifPresent(
                       firstName -> System.out.println("Длина твоего имени: " + firstName.length())
               );
        //Мы воспользовались методом map(), который позволяет преобразовать
        //Optional<Person> в Optional<String>. В данном случае — это имя пользователя.


        final Optional<String> optFirstName = optPerson.map(user -> user.getFirstName());
        optFirstName.ifPresent(
                firstName -> System.out.println("Длина твоего имени: " + firstName.length())
        );
        //Мы преобразовали объект типа Optional<Person> в Optional<String> и затем,
        // используя метод ifPresent(), вывели на консоль длину имени.


        personRepository.findById(1L)
                        .map(Person::getFirstName)
                        .ifPresent(
                                firstName -> System.out.println("Длина твоего имени: " + firstName.length())
                        );
        //Этот код делает то же самое, но выглядит чище и понятнее.
        //Optional помогает избежать громоздких проверок на null, делая код более читаемым и безопасным.
    }
}
