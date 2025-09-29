package by.step.phantom;

import lombok.Getter;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Менеджер для работы с direct memory (off-heap)
 */
public class DirectMemoryManager {
    private final ReferenceQueue<DirectBuffer> queue = new ReferenceQueue<>();
    private final AtomicInteger allocatedMemory = new AtomicInteger(0);
    private final AtomicInteger cleanedMemory = new AtomicInteger(0);

    private static class DirectBufferPhantomReference extends PhantomReference<DirectBuffer> {
        private final int size;
        private final long address; // Имитация native address

        public DirectBufferPhantomReference(
                DirectBuffer referent,
                ReferenceQueue<? super DirectBuffer> queue,
                int size, long address
        ) {
            super(referent, queue);
            this.size = size;
            this.address = address;
        }
    }

    public DirectBuffer allocateDirectBuffer(int size) {
        // Создаем direct buffer (off-heap memory)
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(size);
        DirectBuffer buffer = new DirectBuffer(byteBuffer, size);

        // Регистрируем phantom reference
        long simulatedAddress = System.identityHashCode(byteBuffer);
        new DirectBufferPhantomReference(buffer, queue, size, simulatedAddress);

        allocatedMemory.addAndGet(size);
        System.out.println("Выделено direct memory: " + size + " bytes" +
                           " (всего: " + allocatedMemory.get() + " bytes)");

        return buffer;
    }

    public void startCleanupMonitor() {
        Thread cleanupThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Object ref = queue.remove(1000);
                    if (ref instanceof DirectBufferPhantomReference) {
                        DirectBufferPhantomReference phantomRef =
                                (DirectBufferPhantomReference) ref;

                        // Освобождаем ресурсы
                        cleanupDirectMemory(phantomRef.address, phantomRef.size);
                        cleanedMemory.addAndGet(phantomRef.size);

                        System.out.println("Освобождено direct memory: " +
                                           phantomRef.size + " bytes" +
                                           " (всего освобождено: " +
                                           cleanedMemory.get() + " bytes)");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    private void cleanupDirectMemory(long address, int size) {
        // Здесь будет реальное освобождение native memory
        // В данном примере просто имитируем
        System.out.println("Освобождаем native memory по адресу: " +
                           String.format("0x%08X", address) +
                           ", размер: " + size + " bytes");
    }

    public static void main(String[] args) throws Exception {
        DirectMemoryManager manager = new DirectMemoryManager();
        manager.startCleanupMonitor();

        System.out.println("=== ДЕМОНСТРАЦИЯ УПРАВЛЕНИЯ DIRECT MEMORY ===\n");

        // Создаем несколько буферов
        DirectBuffer buffer1 = manager.allocateDirectBuffer(1024 * 1024); // 1MB
        DirectBuffer buffer2 = manager.allocateDirectBuffer(2 * 1024 * 1024); // 2MB

        // Используем буферы
        buffer1.use();
        buffer2.use();

        System.out.println("\n--- Освобождаем буферы ---");

        // Освобождаем буферы (убираем strong references)
        buffer1 = null;
        buffer2 = null;

        // Запускаем GC
        System.gc();
        Thread.sleep(1000);
        System.gc();

        // Даем время на обработку
        Thread.sleep(2000);

        System.out.println("\nИтоги:");
        System.out.println("Выделено всего: " + manager.allocatedMemory.get() + " bytes");
        System.out.println("Освобождено: " + manager.cleanedMemory.get() + " bytes");
        System.out.println("Не освобождено: " +
                           (manager.allocatedMemory.get() - manager.cleanedMemory.get()) + " bytes");
    }

    /**
     * Обертка для direct buffer
     */
    private record DirectBuffer(@Getter ByteBuffer buffer, int size) {

        public void use() {
            System.out.println("Используем direct buffer размером " + size + " bytes");
            // Работа с буфером...
        }

        @Override
        public String toString() {
            return "DirectBuffer{size=" + size + " bytes}";
        }
    }
}


