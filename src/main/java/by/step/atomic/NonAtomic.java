package by.step.atomic;


import by.step.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class NonAtomic {

    private static final int NUM_INCREMENTS = 1000;
    private static final int NUM_THREADS = 8;

    private static Integer nonAtomicInt = 0;

    public static void main(String[] args) {
        testIncrement();
        testAccumulate();
        testUpdate();
    }

    private static void testUpdate() {
        nonAtomicInt = 0;

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            nonAtomicInt = nonAtomicInt + 2;
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Update: Expected=2000; Is= %d\n", nonAtomicInt);
    }

    private static void testAccumulate() {
        nonAtomicInt = 0;

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            nonAtomicInt = nonAtomicInt + i;
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Accumulate: Expected=499500; Is= %d\n", nonAtomicInt);
    }

    private static void testIncrement() {
        nonAtomicInt = 0;

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(() -> nonAtomicInt = nonAtomicInt + 1));
                // THREAD 1   read 0   update 0+1   write 0+1
                // THREAD 2            read 0       update 0+1     write 0+1
                // expected 2   actual 1
        ConcurrentUtils.stop(executor);

        System.out.format("Increment: Expected=%d; Is=%d\n", NUM_INCREMENTS, nonAtomicInt);
    }

}