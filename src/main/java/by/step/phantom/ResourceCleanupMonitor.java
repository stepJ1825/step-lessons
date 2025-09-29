package by.step.phantom;

import lombok.Getter;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Система для отслеживания освобождения ресурсов
 */
public class ResourceCleanupMonitor {

    private static final ReferenceQueue<ManagedResource> QUEUE =
            new ReferenceQueue<>();
    private static final ConcurrentHashMap<PhantomReference<ManagedResource>,
            CleanupTask> REFERENCE_MAP =
            new ConcurrentHashMap<>();

    private static final ExecutorService CLEANUP_EXECUTOR =
            Executors.newSingleThreadExecutor();

    static {
        // Запускаем поток для обработки очистки
        startCleanupThread();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== ДЕМОНСТРАЦИЯ PHANTOM REFERENCE ДЛЯ ОЧИСТКИ РЕСУРСОВ ===\n");

        // Создаем несколько ресурсов
        List<ManagedResource> resources = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ManagedResource resource = new ManagedResource("Resource-" + i);
            resources.add(resource);
            registerResource(resource);
        }

        System.out.println("Создано ресурсов: " + resources.size());
        System.out.println("Зарегистрировано phantom references: " + REFERENCE_MAP.size());

        // Освобождаем некоторые ресурсы
        System.out.println("\n--- Освобождаем Resource-0 и Resource-2 ---");
        resources.set(0, null);
        resources.set(2, null);

        // Принудительная сборка мусора
        triggerCleanup();

        // Освобождаем остальные
        System.out.println("\n--- Освобождаем все оставшиеся ресурсы ---");
        resources.clear();

        // Еще раз запускаем очистку
        triggerCleanup();

        // Даем время на обработку
        Thread.sleep(2000);

        // Завершаем работу
        CLEANUP_EXECUTOR.shutdown();
        CLEANUP_EXECUTOR.awaitTermination(5, TimeUnit.SECONDS);
        CLEANUP_EXECUTOR.shutdownNow();

        System.out.println("\nОставшиеся phantom references: " + REFERENCE_MAP.size());
    }

    public static void registerResource(ManagedResource resource) {
        CleanupTask cleanupTask = new CleanupTask(resource.getResourceId());
        PhantomReference<ManagedResource> phantomRef =
                new PhantomReference<>(resource, QUEUE);

        REFERENCE_MAP.put(phantomRef, cleanupTask);

        System.out.println("Зарегистрирован ресурс: " + resource.getResourceId());
    }

    private static void startCleanupThread() {
        CLEANUP_EXECUTOR.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // Ждем появления reference в очереди
                    Reference<? extends ManagedResource> ref = QUEUE.remove(1000);

                    if (ref != null) {
                        CleanupTask cleanupTask = REFERENCE_MAP.remove(ref);
                        if (cleanupTask != null) {
                            cleanupTask.cleanup();
                        }

                        // Очищаем reference
                        ref.clear();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }

    public static void triggerCleanup() {
        System.out.println("Запуск GC...");
        System.gc();
        System.runFinalization();

        try {
            Thread.sleep(500); // Даем время на обработку
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Управляемый ресурс (например, файл, сетевое соединение и т.д.)
     */
    private static class ManagedResource {
        @Getter
        private final String resourceId;
        private final byte[] data;

        public ManagedResource(String resourceId) {
            this.resourceId = resourceId;
            this.data = new byte[2 * 1024 * 1024]; // 2MB данных
            System.out.println("Создан ресурс: " + resourceId);
        }

        public void doWork() {
            System.out.println("Ресурс " + resourceId + " выполняет работу");
        }

        // НЕ переопределяем finalize()!
        // Вся очистка через PhantomReference

        @Override
        public String toString() {
            return "ManagedResource{" + resourceId + "}";
        }
    }

    /**
     * Задача очистки ресурса
     */
    private static class CleanupTask {
        private final String resourceId;
        private final long creationTime;

        public CleanupTask(String resourceId) {
            this.resourceId = resourceId;
            this.creationTime = System.currentTimeMillis();
        }

        public void cleanup() {
            long lifetime = System.currentTimeMillis() - creationTime;
            System.out.println("🚀 ВЫПОЛНЕНА ОЧИСТКА: " + resourceId +
                               " (время жизни: " + lifetime + "ms)");

            // Здесь может быть:
            // - Закрытие файловых дескрипторов
            // - Освобождение сетевых соединений
            // - Очистка кеша
            // - Освобождение native memory
            // - Логирование

            try {
                // Имитация работы по очистке
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}



