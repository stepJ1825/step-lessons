package by.step.atomic;


import by.step.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Atomic {

    private static final int NUM_INCREMENTS = 1000;
    private static final int NUM_THREADS = 8;

    private static final AtomicInteger atomicInt = new AtomicInteger(0);

    public static void main(String[] args) {
        testIncrement();
        testAccumulate();
        testUpdate();
    }

    private static void testUpdate() {
        atomicInt.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.updateAndGet(n -> n + 2);
                            // read 0   update 0+2    read 2   read again ->
                            // read 2   update 2+2    read 2   write  2+2  ALL OK
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);
//        executor.shutdown();

        System.out.format("Update: Expected=2000; Is= %d\n", atomicInt.get());
    }

    private static void testAccumulate() {
        atomicInt.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, (n, m) -> n + m);
                    executor.submit(task);
                });

//        ConcurrentUtils.stop(executor);

        System.out.format("Accumulate: Expected=499500; Is= %d\n", atomicInt.get());
    }

    private static void testIncrement() {
        atomicInt.set(0);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.format("Increment: Expected=%d; Is=%d\n", NUM_INCREMENTS, atomicInt.get());
    }

}