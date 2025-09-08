package by.step.creational.prototype;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.stream.IntStream;

public class FromDatabaseExample {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100L);
        Date start = new Date();
        Robot robot1 = new Robot(1, "first", 100);
        Date after1 = new Date();
        System.out.println(after1.getTime() - start.getTime());
        IntStream.rangeClosed(0, 1_000_000).forEach(value -> robot1.clone());
        Date after2 = new Date();
        System.out.println(after2.getTime() - after1.getTime());
    }
}

@Data
@NoArgsConstructor
class Robot {
    private int age;
    private String name;
    private double mass;

    public Robot(int age, String name, double mass) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.age = age;
        this.name = name;
        this.mass = mass;
    }

    public Robot clone() {
        Robot robot1 = new Robot();
        robot1.setAge(0);
        robot1.setMass(0);
        robot1.setName("default name");
        return robot1;
    }
}
