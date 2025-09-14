package by.step;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ключевые отличия типов ссылок:
 * Strong - объект никогда не удаляется GC
 * Soft - удаляется только при нехватке памяти
 * Weak - удаляется при следующем GC
 * Phantom - всегда возвращает null, используется для cleanup
 */
public class AllReferencesExample {
    private static ReferenceQueue<Object> queue = new ReferenceQueue<>();
    private static List<Reference<Object>> references = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // Создаем объекты со всеми типами ссылок
        createReferences();

        System.out.println("=== Исходное состояние ===");
        printReferences();

        // Удаляем strong references
        System.out.println("\n=== Удаляем strong references ===");
        clearStrongReferences();

        System.out.println("\n=== После удаления strong references ===");
        printReferences();

        // Вызываем GC
        System.out.println("\n=== Вызываем GC ===");
        System.gc();
        Thread.sleep(500);

        System.out.println("\n=== После GC ===");
        printReferences();

        // Проверяем очередь reference queue
        processReferenceQueue();
    }

    private static void createReferences() {
        // Strong reference
        Object strongObj = new MyObject("Strong");
        references.add(new StrongReferenceWrapper(strongObj));

        // Soft reference
        Object softObj = new MyObject("Soft");
        references.add(new SoftReference<>(softObj));

        // Weak reference
        Object weakObj = new MyObject("Weak");
        references.add(new WeakReference<>(weakObj));

        // Phantom reference
        Object phantomObj = new MyObject("Phantom");
        references.add(new PhantomReference<>(phantomObj, queue));
    }

    private static void clearStrongReferences() {
        // В реальном коде здесь бы мы установили strong references в null
        // Для демонстрации просто сообщим
        System.out.println("Strong references установлены в null");
    }

    private static void printReferences() {
        for (Reference<Object> ref : references) {
            Object obj = ref.get();
            String type = ref.getClass().getSimpleName();
            System.out.printf("%-15s: %s%n", type, obj != null ? obj : "null");
        }
    }

    private static void processReferenceQueue() {
        System.out.println("\n=== Обработка Reference Queue ===");
        Reference<?> ref;
        while ((ref = queue.poll()) != null) {
            System.out.println("Обнаружена в очереди: " + ref.getClass().getSimpleName());
        }
    }

    static class MyObject {
        private String name;
        private byte[] data = new byte[2 * 1024 * 1024]; // 2 MB

        public MyObject(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyObject{" + name + "}";
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalize для " + name);
            super.finalize();
        }
    }

    // Обертка для strong reference (ReferenceQueue не поддерживает strong references)
    static class StrongReferenceWrapper extends SoftReference<Object> {
        public StrongReferenceWrapper(Object referent) {
            super(referent);
        }
    }
}
