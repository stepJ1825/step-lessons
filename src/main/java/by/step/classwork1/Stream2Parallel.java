package by.step.classwork1;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

public class Stream2Parallel {
    public static void main(String[] args) {
        Date start = new Date();
        Random random = new Random();
        Stream
                .generate(() -> {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    return random.nextInt();
                })
                .parallel()
                .limit(100_000_000)
                .count();
//                .forEach(integer -> System.out.println(Thread.currentThread().getName()));
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime());
    }
}
