package by.step;

import lombok.Data;

import java.util.*;

/**
 * Пример 4: Более сложный Consumer
 */
public class AnimalExample {

    // Consumer Super - добавляем животных в список
    public static void addAnimals(List<? super Animal> animals) {
        animals.add(new Animal("Generic Animal"));
        animals.add(new Dog("Buddy"));
        animals.add(new Cat("Whiskers"));
    }

    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();

        addAnimals(objects); // Object super Animal
        addAnimals(animals); // Animal super Animal

        System.out.println("Objects: " + objects);
        System.out.println("Animals: " + animals);

        Dog someDog = (Dog) animals.get(1);
        someDog.bark();

        // Не скомпилируется!
//        List<Dog> dogs = new ArrayList<>();
//        addAnimals(dogs); // Ошибка! Dog не super Animal
    }

    @Data
    private static class Animal {
        private final String name;

        @Override
        public String toString() {
            return name;
        }
    }

    private static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }

        public void bark() {
            System.out.println("The dog is barking");
        }
    }

    private static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }
}