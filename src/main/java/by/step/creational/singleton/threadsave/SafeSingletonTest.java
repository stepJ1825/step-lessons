package by.step.creational.singleton.threadsave;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SafeSingletonTest {
    public static void main(String[] args) throws InterruptedException {
        final int THREAD_COUNT = 10;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        System.out.println("Запуск теста в многопоточной среде...");

        // Запускаем несколько потоков, которые пытаются получить экземпляр синглтона
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstanceDCL();
                int i1 = EnumSingleton.INSTANCE.someMethod(1, 2);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Всего создано экземпляров: " + ThreadSafeSingleton.getInstanceCount());

        if (ThreadSafeSingleton.getInstanceCount() > 1) {
            System.out.println("⚠️  Обнаружена проблема: создано больше одного экземпляра!");
        } else {
            System.out.println("✓  Синглтон работает корректно");
        }
    }
}