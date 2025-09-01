package by.step;

import by.step.model.Person;
import by.step.repository.PersonRepositoryWithOptional;

import java.util.Map;
import java.util.Optional;

public class Main5 {

    private static final PersonRepositoryWithOptional personRepository =
            new PersonRepositoryWithOptional(Map.of(1L,new Person(1, null)));

    public static void main(String[] args) {

//        Optional<Person> optPerson = personRepository.findById(1L);

//        optPerson
//                .map(Person::getFirstName)
//                .ifPresent(firstName ->
//                        System.out.println("Длина твоего имени: " + firstName.length()));
        //Мы воспользовались методом map(), который позволяет преобразовать
        //Optional<Person> в Optional<String>. В данном случае — это имя пользователя.


//        Optional<String> optFirstName =
//                optPerson.map(Person::getFirstName);
//        optFirstName.ifPresent(
//                firstName -> System.out.println("Длина твоего имени: " + firstName.length())
//        );
        //Мы преобразовали объект типа Optional<Person> в Optional<String> и затем,
        // используя метод ifPresent(), вывели на консоль длину имени.


//        personRepository.findById(1L).isEmpty()
//                        .map(Person::getFirstName)
//                        .ifPresent(firstName ->
//                                System.out.println("Длина твоего имени: " + firstName.length())
//                        );

        //Этот код делает то же самое, но выглядит чище и понятнее.
        //Optional помогает избежать громоздких проверок на null, делая код более читаемым и безопасным.


//        Optional<Person> byId = personRepository.findById(1L);
//        boolean isPresent = byId.isPresent();
//        if (isPresent) {
//            Person person = byId.get();
//        }
//
//        personRepository.findById(2L).ifPresent(
//                person ->
//                        System.out.println(person.getFirstName())
//        );

        personRepository.findById(20L).ifPresentOrElse(
                person -> System.out.println(person.getFirstName()),
                () -> System.out.println("Иван Иванов")
        );

        Person personByIdOrDefault = personRepository.findById(20L)
                .orElse(new Person(-1, "anon"));

        personRepository.findById(20L)
                .orElseGet(() -> {
                    System.out.println("Пользователь не был найден, отправляем анонимного");
                    return new Person(-1, "anon");
                });

        personRepository.findById(200L)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        Person person1 = personRepository.findById(1L)
                .filter(person -> !person.getFirstName().isBlank())
                .get();
    }

}
