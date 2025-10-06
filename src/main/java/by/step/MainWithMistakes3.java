package by.step;

import by.step.model.*;
import by.step.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainWithMistakes3 {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
//        task14();
//        task15();
//        task16();
    }

    private static void task1() throws IOException {
        System.out.println("\nЗадача №1");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        List<List<Animal>> zoos = animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        filteredList -> IntStream.range(0, (filteredList.size() + 6) / 7)
                                .mapToObj(i -> filteredList.subList(
                                        i * 7,
                                        Math.min(
                                                (i + 1) * 7,
                                                filteredList.size()
                                        )
                                ))
                                .map(ArrayList::new)
                                .collect(Collectors.toList())
                ));

        System.out.println("=== РАСПРЕДЕЛЕНИЕ В ЗООПАРКИ ===");
        System.out.println("Количество зоопарков: " + zoos.size());
        for (int i = 0; i < zoos.size(); i++) {
            System.out.print(zoos.get(i).size() + ", ");
        }

        System.out.println("\n=== 3-Й ЗООПАРК ===");
        if (zoos.size() >= 3) {
            for (Animal animal : zoos.get(2)) {
                System.out.println(animal);
            }
        }
    }

    private static void task2() throws IOException {
        System.out.println("\n\nЗадача №2");
        List<Animal> animals = Util.getAnimals();
        //TODO
        animals.stream()
                .filter(animal -> animal.getOrigin().equalsIgnoreCase("Japanese"))
                .map(animal -> animal.getGender().equalsIgnoreCase("Female")
                        ? animal.getBreed().toUpperCase()
                        : animal.getBreed())
                .toList()
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        System.out.println("\n\nЗадача №3");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        animals.stream()
                .filter(animal -> animal.getAge() >= 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .toList()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        System.out.println("\n\nЗадача №4");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        long femaleCount = animals.stream()
                .filter(animal -> animal.getGender().equalsIgnoreCase("Female"))
                .count();

        System.out.println("Количество всех животных пола 'Female': " + femaleCount);

    }

    private static void task5() throws IOException {
        System.out.println("\n\nЗадача №5");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        Map<Object, List<Animal>> hungarian = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .collect(Collectors.groupingBy(
                        animal -> animal.getOrigin()
                                .equalsIgnoreCase("Hungarian123")));

        if ((hungarian.get(true)!=null)&& !hungarian.get(true).isEmpty() ) {
            System.out.println("Среди животных есть хоть один из Венгрии");
        } else {
            System.out.println("Среди животных нет ни одного из Венгрии");
        }
    }

    private static void task6() throws IOException {
        System.out.println("\n\nЗадача №6");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        boolean genderAnimals = animals.stream()
                .anyMatch(animal -> animal.getGender().equalsIgnoreCase("Male") &&
                        animal.getGender().equalsIgnoreCase("Female"));
        if (!genderAnimals) {
            System.out.println("Все животные пола Male и Female");
        } else {
            System.out.println("Не все животные пола Male и Female");
        }
    }

    private static void task7() throws IOException {
        System.out.println("\n\nЗадача №7");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        boolean originAnimals = animals.stream()
                .anyMatch(animal -> animal.getOrigin().equalsIgnoreCase("Oceania"));
        if (!originAnimals) {
            System.out.println("Ни одно из животных не имеет страну происхождения Oceania");
        } else {
            System.out.println("Есть животное, которое имеет страну происхождения Oceania");
        }
    }

    private static void task8() throws IOException {
        System.out.println("\n\nЗадача №8");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        Optional<Animal> maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBreed))
                .limit(100)
                .max(Comparator.comparingInt(Animal::getAge));
        System.out.println("Самое старое животное: " + maxAge.get());
    }

    private static void task9() throws IOException {
        System.out.println("\n\nЗадача №9");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        OptionalInt minLength = animals.stream()
                .map(Animal::getBreed)
                .map(String::toCharArray)
                .mapToInt(value -> value.length)
                .min();
        System.out.println("Длина самого короткого массива: " + minLength.getAsInt());
    }

    private static void task10() throws IOException {
        System.out.println("\n\nЗадача №10");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        long sumAge = animals.stream()
                .collect(Collectors.summarizingInt(Animal::getAge))
                .getSum();
        System.out.println("Суммарный возраст всех животных: " + sumAge);
    }

    private static void task11() throws IOException {
        System.out.println("\n\nЗадача №11");
        List<Animal> animals = Util.getAnimals();
        //TODO:
        Double avgAge = animals.stream()
                .filter(animal -> animal.getOrigin().equalsIgnoreCase("Indonesian"))
                .collect(Collectors.averagingInt(Animal::getAge));
        System.out.println("Средний возраст животных из Индонезии: " + avgAge);
    }

    private static void task12() throws IOException {
        System.out.println("\n\nЗадача №12");
        List<Person> people = Util.getPersons();
        //TODO:
        people.stream()
                .filter(person -> person.getGender().equalsIgnoreCase("Male"))
                .filter(person -> {
                    LocalDate today = LocalDate.now();
                    int age = Period.between(person.getDateOfBirth(), today).getYears();
                    return age >= 18 && age <= 27;
                })
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        System.out.println("\n\nЗадача №13");
        List<House> houses = Util.getHouses();
        //TODO:
        final int retirementAge = 65;

        List<Person> peopleFromTheHospital = houses.stream()
                .filter(house ->
                        house.getBuildingType().equalsIgnoreCase("Hospital"))
                .flatMap(house ->
                        house.getPersonList().stream())
                .toList();

        List<Person> vulnerablePeople = houses.stream()
                .filter(house ->
                        house.getBuildingType().equalsIgnoreCase("Civil building"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> {
                    LocalDate today = LocalDate.now();
                    int age = Period.between(person.getDateOfBirth(), today).getYears();
                    return age < 18 || age >= retirementAge;
                })
                .sorted(Comparator.comparing((Person person) -> {
                            LocalDate today = LocalDate.now();
                            int age = Period.between(person.getDateOfBirth(), today).getYears();
                            return age < 18 ? 1 : 2;
                        })
                        .thenComparing(Person::getDateOfBirth))
                .toList();

        List<Person> otherPeople = houses.stream()
                .filter(house -> house.getBuildingType().equalsIgnoreCase("Civil building"))
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> {
                    LocalDate today = LocalDate.now();
                    int age = Period.between(person.getDateOfBirth(), today).getYears();
                    return age >= 18 && age < retirementAge;
                })
                .toList();

        List<Person> evacuationPriority = Stream.concat(
                        Stream.concat(
                                peopleFromTheHospital.stream(),
                                vulnerablePeople.stream()
                        ),
                        otherPeople.stream()
                )
                .limit(500)
                .toList();

        evacuationPriority
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        System.out.println("\n\nЗадача №14");
        List<Car> cars = Util.getCars();
        //TODO:
        List<Car> turkmenistan = new ArrayList<>();
        List<Car> uzbekistan = new ArrayList<>();
        List<Car> kazakhstan = new ArrayList<>();
        List<Car> kyrgyzstan = new ArrayList<>();
        List<Car> russia = new ArrayList<>();
        List<Car> mongolia = new ArrayList<>();

        List<Car> filteredCars = cars.stream()
                .filter(car -> {
                    if (car.getCarModel().equalsIgnoreCase("Jaguar")
                            || car.getColor().equalsIgnoreCase("White")) {
                        turkmenistan.add(car);
                        return false;
                    }
                    return true;
                })
                .filter(car -> {
                    if (car.getMass() <= 1500
                            || Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota")
                            .contains(car.getCarMake())) {
                        uzbekistan.add(car);
                        return false;
                    }
                    return true;
                })
                .filter(car -> {
                    if ((car.getColor().equalsIgnoreCase("Black") && car.getMass() > 4000)
                            || Arrays.asList("GMC", "Dodge").contains(car.getCarModel())) {
                        kazakhstan.add(car);
                        return false;
                    }
                    return true;
                })
                .filter(car -> {
                    if (car.getReleaseYear() <= 1982 || Arrays.asList("Civic", "Cherokee")
                            .contains(car.getCarModel())) {
                        kyrgyzstan.add(car);
                        return false;
                    }
                    return true;
                })
                .filter(car -> {
                    if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor())
                            ||
                            car.getPrice() >= 4000) {
                        russia.add(car);
                        return false;
                    }
                    return true;
                })
                .filter(car -> {
                    if (car.getVin().contains("59")) {
                        mongolia.add(car);
                        return false;
                    }
                    return true;
                })
                .toList();

        Map<String, List<Car>> countryCars = new LinkedHashMap<>();
        countryCars.put("Туркменистан", turkmenistan);
        countryCars.put("Узбекистан", uzbekistan);
        countryCars.put("Казахстан", kazakhstan);
        countryCars.put("Кыргызстан", kyrgyzstan);
        countryCars.put("Россия", russia);
        countryCars.put("Монголия", mongolia);

        double costPerTon = 7.14;
        double totalRevenue = 0;
        System.out.println("Стоимость транспортных расходов по странам:");
        for (Map.Entry<String, List<Car>> entry : countryCars.entrySet()) {
            String country = entry.getKey();
            List<Car> countryCarList = entry.getValue();

            double totalMassKg = countryCarList.stream()
                    .mapToDouble(Car::getMass)
                    .sum();

            double totalMassTons = totalMassKg / 1000;
            double transportCost = totalMassTons * costPerTon;
            totalRevenue += transportCost;

            System.out.printf("%s: %.2f $ (%.1f кг)%n", country, transportCost, totalMassKg);
        }

        System.out.println("\nОбщая выручка логистической компании: " + (float) totalRevenue + " $");

        System.out.println("Отброшено автомобилей: " + filteredCars.size());
    }

    private static void task15() throws IOException {
        System.out.println("\n\nЗадача №15");
        List<Flower> flowers = Util.getFlowers();
        //TODO:
        Set<String> allowedPots = Set.of("Glass", "Aluminum", "Steel");
        int years = 5;
        double waterCostPerCubicMeter = 1.39;

        double totalCost = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay)
                                .reversed()))
                .filter(flower -> {
                    if (flower.getCommonName() == null || flower.getCommonName().isEmpty()) {
                        return false;
                    }
                    char firstChar = Character.toUpperCase(flower.getCommonName().charAt(0));
                    return firstChar >= 'C' && firstChar <= 'S';
                })
                .filter(flower -> flower.isShadePreferred()
                        && flower.getFlowerVaseMaterial()
                        .stream()
                        .anyMatch(allowedPots::contains))
                .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay()
                        * years * waterCostPerCubicMeter)
                .sum();

        System.out.println("Обслуживание всех растений за 5 лет обойдется: " + totalCost + "$.");
    }

    private static void task16() throws IOException {
        System.out.println("\n\nЗадача №16");
        List<Customer> customers = Util.getCustomers();
        //TODO:
        Map<String, List<Customer>> customersGetCity = customers.stream()
                .collect(Collectors.groupingBy(customer ->
                        customer.getAddress().getCity()));

        List<Customer> filteredCustomers = customersGetCity.entrySet().stream()
                .filter(customerFilter ->
                        customerFilter.getValue().size() >= 3)
                .flatMap(customerFilter -> customerFilter.getValue()
                        .stream())
                .toList();

        List<Customer> sortedCustomers = filteredCustomers.stream()
                .sorted(Comparator
                        .comparing((Customer client) -> client.getAddress()
                                .getCity())
                        .thenComparing(client -> client.getAddress()
                                .getStreet())
                        .thenComparing(
                                (Customer client) -> client.getAddress()
                                        .getBuildingNumber(),
                                Comparator.reverseOrder()
                        )
                        .thenComparing(Customer::getLastName))
                .toList();

        for (Customer client : sortedCustomers) {
            CustomerAddress a = client.getAddress();
            System.out.println(client.getFirstName() + " " + client.getLastName() + ", city: " +
                    client.getAddress().getCity() + ", street: " +
                    client.getAddress().getStreet() + ", " + client.getAddress().getBuildingNumber() +
                    "-" + client.getAddress().getFlatNumber());
        }
    }
}