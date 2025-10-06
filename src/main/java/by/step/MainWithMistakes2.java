package by.step;


import by.step.model.*;
import by.step.util.Util;

import javax.lang.model.util.Elements;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Period.between;

public class MainWithMistakes2 {

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
               .filter(animal -> animal
                                         .getAge() > 10 && animal
                                                                   .getAge() < 20)
               .sorted(Comparator
                       .comparing(Animal::getAge))
               .skip(7 * 2)
               .limit(7)
               .forEach(System.out::println);
        System.out.println();
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .filter(animal -> animal
                       .getOrigin()
                       .equalsIgnoreCase("japanese"))
               .peek(animal -> {
                   if (animal.getGender()
                             .equalsIgnoreCase("female")) {
                       animal.setBreed(animal
                               .getBreed()
                               .toUpperCase());
                   }
               })
               .map(Animal::getBreed)
               .forEach(System.out::println);
        System.out.println();
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .filter(animal -> animal
                                         .getAge() > 30)
               .map(Animal::getOrigin)
               .filter(origin -> origin
                       .startsWith("A"))
               .distinct()
               .forEach(System.out::println);
        System.out.println();
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long femaleCount = animals.stream()
                                  .filter(animal -> animal
                                          .getGender()
                                          .equalsIgnoreCase("female"))
                                  .count();
        System.out.println(femaleCount);
        System.out.println();
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String answer = animals.stream()
                               .filter(animal -> animal
                                                         .getAge() > 20 && animal
                                                                                   .getAge() < 30)
                               .anyMatch(animal -> animal
                                       .getOrigin()
                                       .equalsIgnoreCase("Hungarian")) ? "да, есть" : "нет";
        System.out.println(answer);
        System.out.println();
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String answer = animals.stream()
                               .anyMatch(animal -> animal
                                                           .getGender().
                                                           equalsIgnoreCase("male") && animal
                                                           .getGender().
                                                           equalsIgnoreCase("female")) ? "да, есть" : "нет";
        System.out.println(answer);
        System.out.println();
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String answer = animals.stream()
                               .noneMatch(animal -> animal
                                       .getOrigin()
                                       .equalsIgnoreCase("oceania")) ? "да, есть" : "нет";
        System.out.println(answer);
        System.out.println();
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
               .sorted(Comparator
                       .comparing(Animal::getBreed))
               .map(Animal::getBreed)
               .limit(100)
               .forEach(System.out::println);
        System.out.println();
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int answer = animals.stream()
                            .map(Animal::getBreed)
                            .map(String::toCharArray)
                            .mapToInt(arr -> arr.length)
                            .min()
                            .orElse(0);
        System.out.println("длина самого коротокого массива:" + answer);
        System.out.println();
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int answer = animals.stream()
                            .mapToInt(Animal::getAge)
                            .sum();
        System.out.println(answer);
        System.out.println();
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int answer = (int) animals.stream()
                                  .filter(animal -> animal
                                          .getOrigin()
                                          .equalsIgnoreCase("Indonesian"))
                                  .mapToInt(Animal::getAge)
                                  .average()
                                  .orElse(0);
        System.out.println(answer);
        System.out.println();
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
              .filter(person -> person
                      .getGender().
                      equalsIgnoreCase("male"))
              .filter(person -> {
                  LocalDate date = person.getDateOfBirth();
                  int age = between(date, LocalDate.now()).getYears();
                  return age >= 18 && age <= 27;
              })
              .sorted(Comparator
                      .comparing(Person::getRecruitmentGroup))
              .limit(200)
              .forEach(System.out::println);
        System.out.println();
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        Person person = new Person();
        houses.stream()
              .filter(house -> house
                      .getBuildingType()
                      .equalsIgnoreCase("hospital"));
        //TODO:не понял откуда людей брать
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List<String> countries = List.of(
                "Туркменистан",
                "Узбекистан",
                "Казахстан",
                "Кыргизия",
                "Россия",
                "Монголия"
        );
        List<Car> listOfCars = cars.stream()
                                   .sorted(Comparator.comparing(car -> {
                                                       if (car.getCarMake().equalsIgnoreCase("Jaguar") ||
                                                           car.getColor().equalsIgnoreCase("White")) {
                                                           return 0;
                                                       }
                                                       if (car.getMass() <= 1500 ||
                                                           car.getCarMake().equalsIgnoreCase("bmw") ||
                                                           car.getCarMake().equalsIgnoreCase("lexus") ||
                                                           car.getCarMake().equalsIgnoreCase("chrysler") ||
                                                           car.getCarMake().equalsIgnoreCase("toyota")) {
                                                           return 1;
                                                       }
                                                       if (car.getColor().equalsIgnoreCase("black") ||
                                                           car.getMass() > 4000 ||
                                                           car.getCarMake().equalsIgnoreCase("gmc") ||
                                                           car.getCarMake().equalsIgnoreCase("dodge")) {
                                                           return 2;
                                                       }
                                                       if (car.getReleaseYear() < 1982 ||
                                                           car.getCarModel().equalsIgnoreCase("civic") ||
                                                           car.getCarModel().equalsIgnoreCase("cherokee")) {
                                                           return 3;
                                                       }
                                                       if (!car.getColor().equalsIgnoreCase("red") &&
                                                           !car.getColor().equalsIgnoreCase("yellow") &&
                                                           !car.getColor().equalsIgnoreCase("blue") &&
                                                           !car.getColor().equalsIgnoreCase("green") &&
                                                           car.getPrice() > 40000) {
                                                           return 4;
                                                       }
                                                       if (car.getVin().contains("59")) {
                                                           return 5;
                                                       }
                                                       return -1;
                                                   }
                                           )
                                   ).toList();
        //TODO: не разобрался как можно доделать
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        List<String> materials = List.of(
                "glass",
                "aluminium",
                "steel"
        );
        List<Flower> filteredFlowers = flowers.stream()
                                              .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                                              .sorted(Comparator.comparing(Flower::getPrice))
                                              .sorted(Comparator.comparing(Flower::getWaterConsumptionPerDay)
                                                                .reversed())
                                              .filter(flower -> {
                                                  char firstChar = flower.getCommonName().toUpperCase().charAt(0);
                                                  return firstChar >= 'C' && firstChar <= 'S';
                                              })
                                              .filter(flower -> flower.isShadePreferred() &&
                                                                flower.getFlowerVaseMaterial()
                                                                      .stream()
                                                                      .anyMatch(material -> materials.contains(material.toLowerCase())
                                                                      )
                                              ).toList();
        double waterPricePerCubicMeter = 1.39;
        int years = 5;
        double totalPrice = filteredFlowers.stream()
                                           .mapToDouble(flower -> {
                                               double flowerPrice = flower.getPrice();
                                               double waterPrice = flower.getWaterConsumptionPerDay() * 365 * years
                                                                   * waterPricePerCubicMeter;
                                               return flowerPrice + waterPrice;
                                           })
                                           .sum();
        System.out.println(totalPrice);

    }

    private static void task16() throws IOException {
        List<Customer> customers = Util.getCustomers();
        Map<String, Long> cityCounts = customers.stream().
                                                collect(Collectors.groupingBy(
                                                        customer -> customer
                                                                .getAddress()
                                                                .getCity(), Collectors.counting()
                                                ));
        Set<String> citiesWith3OrMorePeoples = cityCounts.entrySet().stream()
                                                         .filter(entry -> entry.getValue() >= 3)
                                                         .map(Map.Entry::getKey)
                                                         .collect(Collectors.toSet());
        List<Customer> result = customers.stream()
                                         .filter(customer -> citiesWith3OrMorePeoples
                                                 .contains(customer.getAddress().getCity())).toList();
        //TODO:дальше не разобрался
    }
}
