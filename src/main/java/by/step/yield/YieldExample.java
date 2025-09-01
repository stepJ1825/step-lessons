package by.step.yield;

import java.util.concurrent.TimeUnit;
/*
По примеру из статьи:
https://www.baeldung.com/java-thread-yield
 */
public class YieldExample {
    public static void main(String[] args) throws InterruptedException {
        Runnable yieldedRunnable = () -> {
            int counter = 0;
            while (counter < 2) {
                System.out.println(Thread.currentThread().getName());
                counter++;
                Thread.yield();
            }
        };
        Runnable simpleRunnable = () -> {
            int counter = 0;
            while (counter < 2) {
                System.out.println(Thread.currentThread().getName());
                counter++;
            }
        };

        for (int i = 0; i < 10; i++) {
            System.out.println("_________________");
            Thread yielded = new Thread(yieldedRunnable, "yielded");
            Thread simple = new Thread(simpleRunnable, "simple");
            yielded.start();
            simple.start();
            System.out.println("_________________");
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
