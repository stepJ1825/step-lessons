package by.step.creational.prototype.robotexample;

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

