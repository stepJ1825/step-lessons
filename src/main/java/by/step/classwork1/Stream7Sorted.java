package by.step.classwork1;

import java.util.Comparator;
import java.util.stream.Stream;


public class Stream7Sorted {
    public static void main(String[] args) {
//        Stream.of(120, 410, 85, 32, 314, 12)
//                .sorted()
//                .forEach(System.out::println);
//        // 12, 32, 85, 120, 314, 410
//        Stream.of(120, 410, 85, 32, 314, 12)
//                .sorted(Comparator.reverseOrder())
//                .forEach(System.out::println);
//        // 410, 314, 120, 85, 32, 12

        User user1 = new User("AAA", 10);
        User user2 = new User("CCC", 40);
        User user3 = new User("BBB", 20);
        User user4 = new User("CCC", 30);
        Comparator<User> userNameReversedComparator = Comparator.comparing(User::getName).reversed();
        Comparator<User> userAgeComparator = Comparator.comparing(User::getAge);
        Stream.of(user1, user4, user3, user2)
                .sorted(userNameReversedComparator
                        .thenComparing(userAgeComparator.reversed())
                )
                .forEach(System.out::println);
    }
}

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
