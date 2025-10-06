package by.step;

import by.step.model.*;
import by.step.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MainSolved {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
               .sorted(Comparator.comparingInt(Animal::getAge))
               .skip(7 * 2)
               .limit(7)
               .forEach(x -> System.out.println("task1: Animal for third zoo: " + x));

    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .filter(animal -> "Japanese".equalsIgnoreCase(animal.getOrigin()))
               .map(animal ->
                       "Female".equalsIgnoreCase(animal.getGender()) ?
                       animal.getBreed().toUpperCase() :
                       animal.getBreed())
               .forEach(x -> System.out.println("task2: Animal from Japan: " + x));
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getAge() > 30)
               .map(Animal::getOrigin)
               .distinct()
               .filter(s -> s.startsWith("A"))
               .forEach(x -> System.out.println("task3: country: " + x));
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long femalesAnimals = animals.stream()
                                     .filter(animal -> "Female".equalsIgnoreCase(animal.getGender()))
                                     .count();
        System.out.println("task4: amount of female animals: " + femalesAnimals);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean anyMatchHungarian = animals.stream()
                                           .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                                           .anyMatch(animal -> "Hungarian".equalsIgnoreCase(animal.getOrigin()));
        System.out.println("task5: is any animal from Hungary: "
                           + String.valueOf(anyMatchHungarian).toUpperCase(Locale.ROOT));
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isAllAnimalsMatchCondition = animals.stream()
                                                    .allMatch(animal ->
                                                            "Female".equalsIgnoreCase(animal.getGender()) ||
                                                            "male".equalsIgnoreCase(animal.getGender()));
        System.out.println("task6: are all animals male and female: "
                           + String.valueOf(isAllAnimalsMatchCondition).toUpperCase(Locale.ROOT));
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isOriginOceania = animals.stream()
                                         .noneMatch(animal -> "Oceania".equalsIgnoreCase(animal.getOrigin()));
        System.out.println("task7: there are no animals from Oceania: "
                           + String.valueOf(isOriginOceania).toUpperCase(Locale.ROOT));
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .sorted(Comparator.comparing(Animal::getBreed))
               .limit(100)
               .mapToInt(Animal::getAge)
               .max()
               .ifPresent(x -> System.out.println("task8: age of the oldest animal: " + x));
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .map(Animal::getBreed)
               .map(String::toCharArray)
               .min(Comparator.comparingInt(o -> o.length))
               .ifPresent(chars -> System.out.println("task9: the shortest array length: " + chars.length));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int sumOfAges = animals.stream()
                               .mapToInt(Animal::getAge)
                               .sum();
        System.out.println("task10: the sum of ages of all animals: " + sumOfAges);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .filter(animal -> "Indonesian".equalsIgnoreCase(animal.getOrigin()))
               .mapToInt(Animal::getAge)
               .average()
               .ifPresent(x -> System.out.println("task11: average age of animals from Indonesia: " + x));
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        // Today is 11.02.2023. We should choose persons, who were born
        // between 12.02.1995 (who is still 27) and 11.02.2005 (who already 18)
        people.stream()
              .filter(person -> "male".equalsIgnoreCase(person.getGender()))
              .filter(person ->
                      LocalDate.now().minusYears(18).plusDays(1)
                               .isAfter(person.getDateOfBirth()) &&
                      LocalDate.now().minusYears(28)
                               .isBefore(person.getDateOfBirth()))
              .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
              .limit(200)
              .forEach(x -> System.out.println("task12: French legion: " + x));
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        Predicate<House> isHospitalPredicate = house ->
                "Hospital".equalsIgnoreCase(house.getBuildingType());
        Predicate<Person> isOlderThen65 = person ->
                person.getDateOfBirth()
                      .isBefore(LocalDate.now().minusYears(65));
        Predicate<Person> isYoungerThen18 = person ->
                person.getDateOfBirth()
                      .isAfter(LocalDate.now().minusYears(18));
        Function<House, Stream<Person>> personFunction = house ->
                house.getPersonList().stream();

        Stream.concat(
                      Stream.concat(
                              houses.stream()
                                    .filter(isHospitalPredicate)
                                    .flatMap(personFunction),
                              houses.stream()
                                    .filter(isHospitalPredicate.negate())
                                    .flatMap(personFunction)
                                    .filter(isOlderThen65.or(isYoungerThen18))
                      ),
                      houses.stream()
                            .filter(isHospitalPredicate.negate())
                            .flatMap(personFunction)
                            .filter(isOlderThen65.negate().and(isYoungerThen18.negate()))
              )
              .limit(500)
              .forEach(x -> System.out.println("task13: evacuation list: " + x));
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List<Car> rest = new ArrayList<>(List.copyOf(cars));

        List<Car> cars1 = Stream.concat(
                                        cars.stream()
                                            .filter(car -> "Jaguar".equalsIgnoreCase(car.getCarMake())),
                                        cars.stream()
                                            .filter(car -> "White".equalsIgnoreCase(car.getColor()))
                                )
                                .toList();
        rest.removeAll(cars1);

        List<Car> cars2 = rest.stream()
                              .filter(car -> car.getMass() < 1500)
                              .filter(car ->
                                      "BMW".equalsIgnoreCase(car.getCarMake()) ||
                                      "Lexus".equalsIgnoreCase(car.getCarMake()) ||
                                      "Chrysler".equalsIgnoreCase(car.getCarMake()) ||
                                      "Toyota".equalsIgnoreCase(car.getCarMake()))
                              .toList();
        rest.removeAll(cars2);

        List<Car> cars3 = Stream.concat(
                                        rest.stream()
                                            .filter(car -> "Black".equalsIgnoreCase(car.getColor()))
                                            .filter(car -> car.getMass() > 4000),
                                        rest.stream()
                                            .filter(car ->
                                                    "GMC".equalsIgnoreCase(car.getCarMake()) ||
                                                    "Dodge".equalsIgnoreCase(car.getCarMake()))
                                )
                                .toList();
        rest.removeAll(cars3);

        List<Car> cars4 = rest.stream()
                              .filter(car ->
                                      car.getReleaseYear() < 1982 ||
                                      "Civic".equalsIgnoreCase(car.getCarModel()) ||
                                      "Cherokee".equalsIgnoreCase(car.getCarModel()))
                              .toList();
        rest.removeAll(cars4);

        List<Car> cars5 = rest.stream()
                              .filter(car ->
                                      !("Yellow".equalsIgnoreCase(car.getColor()) ||
                                        "Red".equalsIgnoreCase(car.getColor()) ||
                                        "Green".equalsIgnoreCase(car.getColor()) ||
                                        "Blue".equalsIgnoreCase(car.getColor())) ||
                                      car.getPrice() > 40_000
                              )
                              .toList();
        rest.removeAll(cars5);

        List<Car> cars6 = rest.stream()
                              .filter(car -> car.getVin().contains("59"))
                              .toList();
        rest.removeAll(cars6);

        List<Double> countryCostsList = Stream.of(cars1, cars2, cars3, cars4, cars5, cars6)
                                              .mapToDouble(value -> value.stream()
                                                                         .mapToInt(Car::getMass)
                                                                         .sum())
                                              .map(value -> value * 7.14 / 1000)
                                              .boxed()
                                              .toList();

        countryCostsList.forEach(x ->
                System.out.format("task14a:The cost of the echelon: %.2f %n", x));

        countryCostsList.stream()
                        .reduce(Double::sum)
                        .ifPresent(cost ->
                                System.out.format("task14b:The total cost: %.2f %n", cost));
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        List<Flower> resultFlowersList = flowers.stream()
                                                .sorted(Comparator
                                                        .comparing(Flower::getOrigin)
                                                        .reversed()                                    //sorted by origin reversed
                                                        .thenComparing(Flower::getPrice)                                            //sorted by price
                                                        .thenComparing(Flower::getWaterConsumptionPerDay)
                                                        .reversed()                //sorted by water consumption
                                                        .thenComparing(Flower::getCommonName)
                                                        .reversed())                           //sorted by common name
                                                .filter(flower -> Pattern.matches(
                                                        "([C-S])(.+)",
                                                        flower.getCommonName()
                                                ))     //filter by first letter of name
                                                .filter(Flower::isShadePreferred)                                                   //filter by shade preferring
                                                .filter(flower -> flower.getFlowerVaseMaterial().contains("Glass") ||
                                                                  //filter by vase material
                                                                  flower.getFlowerVaseMaterial().contains("Aluminum") ||
                                                                  flower.getFlowerVaseMaterial().contains("Steel"))
                                                .toList();

        resultFlowersList.forEach(x -> System.out.println("task15a: chosen plant:" + x));

        resultFlowersList.stream()
                         .mapToDouble(flower ->                                                              //calculate total cost for every plant
                                 flower.getPrice()
                                 + flower.getWaterConsumptionPerDay() * 365 * 5 * 1.39 / 1000)
                         .reduce(Double::sum)
                         .ifPresent(cost ->
                                 System.out.format("task15b: The cost of greenhouse maintenance: %.2f %n", cost));
    }

    private static void task16() throws IOException {
        List<Customer> customers = Util.getCustomers();
        HashMap<String, Integer> cityMap = new HashMap<>();

        customers.stream()
                 .map(customer -> customer.getAddress().getCity())
                 .forEach(s -> cityMap.put(
                         s, cityMap.containsKey(s) ?
                            cityMap.get(s) + 1 : 1
                 ));

        List<String> citiesWithThreeOrMoreCustomers = customers.stream()
                                                               .map(customer -> customer.getAddress().getCity())
                                                               .filter(s -> cityMap.get(s) >= 3)
                                                               .distinct()
                                                               .toList();

        customers.stream()
                 .filter(customer -> citiesWithThreeOrMoreCustomers
                         .contains(customer.getAddress().getCity()))
                 .sorted(Comparator.<Customer, String>comparing(o -> o.getAddress().getCity())
                                   .thenComparing(o -> o.getAddress().getStreet())
                                   .thenComparing(Comparator.<Customer, String>comparing(customer ->
                                           customer.getAddress().getBuildingNumber()).reversed())
                                   .thenComparing(Customer::getLastName))
                 .forEach(x -> System.out.println(
                         "task16: chosen customer: " +
                         x.getFirstName() + " " +
                         x.getLastName() + ", city: " +
                         x.getAddress().getCity() + ", street: " +
                         x.getAddress().getStreet() + ", " +
                         x.getAddress().getBuildingNumber() + "-" +
                         x.getAddress().getFlatNumber()
                 ));
    }
}