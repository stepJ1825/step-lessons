package by.step.callable;

import by.step.ConcurrentUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class CallableExample {
    public CallableExample() {
        // Определяем пул из трех потоков
        //        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor2 = Executors.newSingleThreadExecutor();

        // Список ассоциированных с Callable задач Future
        List<Future<String>> futures =
                IntStream.rangeClosed(1, 10)
                         .mapToObj(i -> executor.submit(new CallableClass(i)))
                         .toList();

        Future<String> future2 = executor2.submit(new CallableClass(1));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.mmm  ");
        try {
            String result = future2.get();
            String text = simpleDateFormat.format(new Date()) + result;
            System.out.println(text);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        for (Future<String> future : futures) {
            try {
                String result = future.get();
                String text = simpleDateFormat.format(new Date()) + result;
                System.out.println(text);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Exception!!!!: " + e);
            }
        }

        // Останавливаем пул потоков
        executor.shutdown();
        //        executor2.shutdown();
        ConcurrentUtils.stop(executor2);
    }

    public static void main(String[] args) {
        new CallableExample();
    }

}