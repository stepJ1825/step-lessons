package by.step.classwork1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream8Distinct {
    public static void main(String[] args) {
//        Stream.of(2, 1, 8, 1, 3, 2)
//                .distinct()
//                .forEach(System.out::println);
//// 2, 1, 8, 3

        Fruit fruit1 = new Fruit(1, "Orange");
        Fruit fruit2 = new Fruit(2, "Apple");
        Fruit fruit3 = new Fruit(3, "Pineapple");
        Fruit fruit4 = new Fruit(4, "Apple");

//        Stream.of(fruit1, fruit2, fruit3, fruit4)
//                .peek(fruit -> System.out.println("before distinct " + fruit))
//                .map(fruit -> new Fruit(fruit.getId()*10,fruit.getName()))
//                .distinct()
//                .sorted((o1, o2) -> o2.id - o1.id)
//                .peek(fruit -> System.out.println("after distinct " + fruit))
//                .forEach(System.out::println);

        System.out.println();

        Object[] array = Stream.of(fruit1, fruit2, fruit3, fruit4)
                .toArray();

        Fruit[] fruitArray = Stream.of(fruit1, fruit2, fruit3, fruit4)
                .toArray(Fruit[]::new);

        Comparator<Fruit> fruitComparator = (o1, o2) -> o1.id - o2.getId();
        Fruit maxFruit = Arrays.stream(fruitArray)
                .max(fruitComparator).get();
//        System.out.println(maxFruit);

        Map<Integer, Fruit> collect = Stream.of(fruit1, fruit2, fruit3, fruit4)
                .collect(Collectors.toMap(Fruit::getId, fruit -> fruit));

        System.out.println();


    }
}

class Fruit {
    int id;
    String name;

    public Fruit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Fruit fruit)) return false;

        return Objects.equals(name, fruit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}