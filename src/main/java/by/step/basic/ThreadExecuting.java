package by.step.basic;

import java.util.stream.IntStream;

public class ThreadExecuting {

    public static void main(String[] args) {
        int threadsAmount = 10;

        IntStream.rangeClosed(1, threadsAmount)
                .mapToObj(i -> new Thread(() -> System.out.println(i)))
                .forEach(Thread::start);

    }
}
