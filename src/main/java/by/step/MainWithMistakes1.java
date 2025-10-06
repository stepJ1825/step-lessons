package by.step;

import by.step.model.*;
import by.step.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainWithMistakes1 {
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
        System.out.println("Задание 1 + 1*\nНовые животные 3-го зоопарка : ");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        List<Animal> sortedAnimals = animals.stream().filter(
                animal -> animal.getAge()
                          >= 10 && animal.getAge() <= 20).sorted(
                Comparator.comparing(Animal::getAge)).toList();
        List<List<Animal>> sortedAnimalsToZoo = new ArrayList<>();

        for (int i = 0; i < sortedAnimals.size(); i += 7) {
            sortedAnimalsToZoo.add(sortedAnimals
                    .subList(i, Math.min(i + 7, sortedAnimals.size())));
        }
        sortedAnimalsToZoo.get(2).forEach(System.out::println);

        List<Integer> animalsInZoo = new ArrayList<>();
        int animalsC = sortedAnimals.size();
        int counter = (animalsC / 7);
        for (int i = 0; i < counter; i++) {
            animalsC -= 7;
            animalsInZoo.add(Math.min(animalsC, 7));
        }
        System.out.println("Животных прибыло в зоопарки : \n" + animalsInZoo);


    }

    private static void task2() throws IOException {
        System.out.println("\nЗадание 2");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        animals.stream().filter(animal -> "Japanese"
                .equals(animal.getOrigin())).map(animal -> {
            if ("Female".equalsIgnoreCase(animal.getGender())) {
                return animal.getBreed().toUpperCase();
            } else {
                return animal.getBreed();
            }
        }).forEach(System.out::println);
    }

    private static void task3() throws IOException {
        System.out.println("\nЗадание 3");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        animals.stream().filter(animal -> animal.getAge() > 30)
               .map(Animal::getOrigin)
               .filter(firstChar -> firstChar.startsWith("A"))
               .distinct()
               .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        System.out.println("\nЗадание 4");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        long femaleCounter = animals.stream()
                                    .filter(animal -> "Female".equalsIgnoreCase
                                                                      (animal.getGender())).count();
        System.out.println("Животных женского пола : " + femaleCounter);
    }

    private static void task5() throws IOException {
        System.out.println("\nЗадание 5");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        boolean hasHungarian = animals.stream()
                                      .filter(animal -> animal.getAge() >= 20 &&
                                                        animal.getAge() <= 30)
                                      .anyMatch(animal -> "Hungarian".equalsIgnoreCase(
                                              animal.getOrigin()));
        System.out.println("Присутствуют Hungarian животные от 20 до 30 лет : "
                           + hasHungarian);
    }

    private static void task6() throws IOException {
        System.out.println("\nЗадание 6");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        boolean traditionalValues = animals.stream()
                                           .allMatch(animal -> "Male".equalsIgnoreCase(animal.getGender())
                                                               || "Female".equalsIgnoreCase(animal.getGender()));
        System.out.println("Все животные Male или Female : " + traditionalValues);
    }

    private static void task7() throws IOException {
        System.out.println("\nЗадание 7");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        boolean noneOceania = animals.stream()
                                     .noneMatch(animal -> "Oceania".equalsIgnoreCase(
                                             animal.getOrigin()));
        System.out.println("Нет животных из Oceania : " + noneOceania);
    }

    private static void task8() throws IOException {
        System.out.println("\nЗадание 8");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        List<Animal> firstHundred = animals.stream()
                                           .sorted(Comparator.comparing(Animal::getBreed))
                                           .limit(100)
                                           .toList();
        OptionalInt maxAge = firstHundred.stream()
                                         .mapToInt(Animal::getAge)
                                         .max();
        System.out.println("Самое старое из сотни : " + maxAge.getAsInt() + " лет");
    }

    private static void task9() throws IOException {
        System.out.println("\nЗадание 9");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        OptionalInt minLength = animals.stream()
                                       .map(Animal::getBreed)
                                       .map(String::toCharArray)
                                       .mapToInt(arr -> arr.length)
                                       .min();
        System.out.println("Длина самого короткого массива породы : "
                           + minLength.getAsInt() + " символов");
    }

    private static void task10() throws IOException {
        System.out.println("\nЗадание 10");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        int totalAge = animals.stream()
                              .mapToInt(Animal::getAge)
                              .sum();
        System.out.println("Общий возраст всех животных : " + totalAge + " лет");
    }

    private static void task11() throws IOException {
        System.out.println("\nЗадание 11");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        OptionalDouble averageAgeFromIndonesia = animals.stream()
                                                        .filter(animal -> "Indonesian"
                                                                .equalsIgnoreCase(animal.getOrigin()))
                                                        .mapToInt(Animal::getAge)
                                                        .average();
        if (averageAgeFromIndonesia.isPresent()) {
            System.out.println("Средний возраст животных из Indonesia : " +
                               averageAgeFromIndonesia.getAsDouble() + " лет");
        } else {
            System.out.println("Животных из Indonesia нет");
        }
    }

    private static void task12() throws IOException {
        System.out.println("\nЗадание 12");
        List<Person> people = Util.getPersons();
        //TODO:
        LocalDate today = LocalDate.now();
        List<Person> selectedToLegion = people.stream()
                                              .filter(person -> "Male".equalsIgnoreCase(
                                                      person.getGender()))
                                              .filter(person -> {
                                                  int age = Period.between(person.getDateOfBirth(), today).getYears();
                                                  return age >= 18 && age <= 27;
                                              })
                                              .filter(person -> {
                                                  int category = person.getRecruitmentGroup();
                                                  return category == 1 || category == 2 || category == 3;
                                              })
                                              .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                                              .limit(200)
                                              .toList();
        selectedToLegion.forEach(System.out::println);

    }

    private static void task13() throws IOException {
        System.out.println("\nЗадание 13");
        List<House> houses = Util.getHouses();
        //TODO:
        LocalDate today = LocalDate.now();
        List<Person> allPeoples = houses.stream()
                                        .flatMap(house -> house
                                                .getPersonList().stream())
                                        .toList();
        List<Person> firstPriority = houses.stream()
                                           .filter(house -> "Hospital"
                                                   .equalsIgnoreCase(house.getBuildingType()))
                                           .flatMap(house -> house.getPersonList()
                                                                  .stream()).toList();
        List<Person> secondPriority = allPeoples.stream()
                                                .filter(person -> {
                                                    int age = Period.between(person.getDateOfBirth(), today).getYears();
                                                    String gender = person.getGender();
                                                    boolean child = age < 18;
                                                    boolean pensioner = (age >= 62 ||
                                                                         (gender.equalsIgnoreCase("Female")
                                                                          && age >= 58));
                                                    return child || pensioner;
                                                })
                                                .filter(person -> !firstPriority.contains(person))
                                                .toList();
        List<Person> thirdPriority = allPeoples.stream()
                                               .filter(person -> !firstPriority.contains(person)
                                                                 && !secondPriority.contains(person))
                                               .toList();
        List<Person> evacuationList = Stream.of(firstPriority, secondPriority, thirdPriority)
                                            .flatMap(Collection::stream)
                                            .limit(500)
                                            .toList();
        evacuationList.forEach(System.out::println);
    }

    private static void task14() throws IOException {
        System.out.println("\nЗадание 14");
        List<Car> cars = Util.getCars();
        //TODO:
        Map<String, List<Car>> countryMap = new LinkedHashMap<>();
        countryMap.put("Туркменистан", new ArrayList<>());
        countryMap.put("Узбекистан", new ArrayList<>());
        countryMap.put("Казахстан", new ArrayList<>());
        countryMap.put("Кыргызстан", new ArrayList<>());
        countryMap.put("Россия", new ArrayList<>());
        countryMap.put("Монголия", new ArrayList<>());

        List<Car> forSent = new ArrayList<>(cars);

        List<Car> toTurkmenistan = forSent.stream()
                                          .filter(car -> (car.getCarModel().equalsIgnoreCase(
                                                  "Jaguar") ||
                                                          car.getColor().equalsIgnoreCase(
                                                                  "White"))).toList();
        countryMap.put("Туркменистан", toTurkmenistan);
        forSent.removeAll(toTurkmenistan);

        List<String> carModelToUzb = List.of("BMW", "Lexus", "Chrysler", "Toyota");
        List<Car> toUzbekistan = forSent.stream()
                                        .filter(car -> car.getMass() <= 1500)
                                        .filter(car -> carModelToUzb.stream()
                                                                    .anyMatch(model -> model
                                                                            .equalsIgnoreCase(car.getCarModel())))
                                        .toList();
        countryMap.put("Узбекистан", toUzbekistan);
        forSent.removeAll(toUzbekistan);

        List<String> carModelToKZ = List.of("GMC", "Dodge");
        List<Car> toKazakhstan = forSent.stream()
                                        .filter(car -> car.getColor()
                                                          .equalsIgnoreCase("Black")
                                                       && car.getMass() > 4000
                                                       || carModelToKZ.stream()
                                                                      .anyMatch(model -> model
                                                                              .equalsIgnoreCase(car.getCarModel())))
                                        .toList();
        countryMap.put("Казахстан", toKazakhstan);
        forSent.removeAll(toKazakhstan);

        List<String> carModelToKyrgiz = List.of("Civic", "Cherokee");
        List<Car> toKyrgyzstan = forSent.stream()
                                        .filter(car -> car.getReleaseYear() <= 1982
                                                       || carModelToKyrgiz.stream()
                                                                          .anyMatch(model -> model
                                                                                  .equalsIgnoreCase(car.getCarModel())))
                                        .toList();
        countryMap.put("Кыргызстан", toKyrgyzstan);
        forSent.removeAll(toKyrgyzstan);

        List<String> bannedCarColorsToRus =
                List.of("Yellow", "Red", "Green", "Blue");
        List<Car> toRussia = forSent.stream()
                                    .filter(car -> !bannedCarColorsToRus.stream()
                                                                        .anyMatch(color -> color
                                                                                .equalsIgnoreCase(car.getColor()))
                                                   || car.getPrice() > 40000)
                                    .toList();
        countryMap.put("Россия", toRussia);
        forSent.removeAll(toRussia);

        List<Car> toMongolia = forSent.stream()
                                      .filter(car -> car.getVin().contains("59"))
                                      .toList();
        countryMap.put("Монголия", toMongolia);
        forSent.removeAll(toMongolia);

        double totalExpenditure = 0;
        for (Map.Entry<String, List<Car>> entry : countryMap.entrySet()) {
            String country = entry.getKey();
            List<Car> carList = entry.getValue();

            int totalMass = carList.stream().mapToInt(Car::getMass).sum();
            int totalMassTons = totalMass / 1000;
            double cost = totalMassTons * 7.14;
            System.out.printf(country + ": стоимость транспортировки = " + cost + "\n");
            totalExpenditure += cost;
        }
        System.out.printf("Общие затраты на тронспортировку = " + totalExpenditure);

    }

    private static void task15() throws IOException {
        System.out.println("\n\nЗадание 15");
        List<Flower> flowers = Util.getFlowers();
        //TODO:
        final double waterCost = 1.39;
        List<String> allowedPods = List.of("Glass", "Aluminum", "Steel");
        List<Flower> sortedFlowers = flowers.stream()
                                            .sorted(Comparator
                                                    .comparing(Flower::getOrigin, Comparator.reverseOrder())
                                                    .thenComparing(Flower::getPrice)
                                                    .thenComparing(
                                                            Flower::getWaterConsumptionPerDay,
                                                            Comparator.reverseOrder()
                                                    ))
                                            .toList();
        List<Flower> sortedFromCtoS = sortedFlowers.stream()
                                                   .filter(flower -> {
                                                       String firstChar =
                                                               flower.getCommonName().substring(0, 1).toUpperCase();
                                                       return firstChar.compareTo("C") >= 0
                                                              && firstChar.compareTo("S") <= 0;
                                                   })
                                                   .toList();
        List<Flower> sortedFromShadowAndPot = sortedFromCtoS.stream()
                                                            .filter(flower -> flower.isShadePreferred())
                                                            .filter(flower -> flower.getFlowerVaseMaterial()
                                                                                    .stream()
                                                                                    .anyMatch(allowedPods::contains))
                                                            .toList();
        double totalPrice = 0;
        for (Flower flower : sortedFromShadowAndPot) {
            double waterPriceFor5Years = flower.getWaterConsumptionPerDay()
                                         * 5 * waterCost;
            totalPrice += flower.getPrice() + waterPriceFor5Years;
        }
        System.out.println("Общая цена обслуживания = " + totalPrice);
    }

    private static void task16() throws IOException {
        System.out.println("\nЗадание 16");
        List<Customer> customers = Util.getCustomers();
        //TODO:
        Map<String, Long> customersInCity = customers.stream()
                                                     .collect(Collectors.groupingBy(
                                                             customer ->
                                                                     customer.getAddress().getCity(),
                                                             Collectors.counting()
                                                     ));
        List<Customer> moreThan3Customers = customers.stream()
                                                     .filter(customer -> customersInCity.get(
                                                             customer.getAddress().getCity()) >= 3)
                                                     .toList();
        List<Customer> sortedCustomers = moreThan3Customers.stream()
                                                           .sorted(Comparator
                                                                   .comparing((Customer customer) -> customer.getAddress()
                                                                                                             .getCity())
                                                                   .thenComparing(customer -> customer.getAddress()
                                                                                                      .getStreet())
                                                                   .thenComparing(
                                                                           customer -> {
                                                                               return Integer.parseInt(customer.getAddress()
                                                                                                               .getBuildingNumber());
                                                                           }
                                                                           , Comparator.reverseOrder()
                                                                   ))
                                                           .toList();
        for (Customer customer : sortedCustomers) {
            CustomerAddress address = customer.getAddress();
            System.out.println(customer.getLastName() + " "
                               + customer.getFirstName() + " | Адресс : "
                               + address.getCity() + " ," + address.getStreet() + " "
                               + address.getBuildingNumber() + " ,этаж :" + address.getFlatNumber());

        }


    }
}