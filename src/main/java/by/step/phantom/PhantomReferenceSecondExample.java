package by.step.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * Не позволяет получить объект (get() всегда возвращает null)
 * Используется для отслеживания факта сборки мусора
 * Добавляется в ReferenceQueue после финализации объекта
 */
public class PhantomReferenceSecondExample {

    public static void main(String[] args) throws InterruptedException {
        // Очередь для отслеживания phantom references
        ReferenceQueue<LargeObject> queue = new ReferenceQueue<>();

        // Создаем большой объект
        LargeObject largeObject = new LargeObject("Test Object");

        // Создаем PhantomReference
        PhantomReference<LargeObject> phantomRef =
                new PhantomReference<>(largeObject, queue);

        System.out.println("1. Исходное состояние:");
        System.out.println("   largeObject: " + largeObject);
        System.out.println("   phantomRef.get(): " + phantomRef.get()); // Всегда null!
        System.out.println("   queue.poll(): " + queue.poll());

        // Убираем сильную ссылку
        largeObject = null;

        System.out.println("\n2. После удаления сильной ссылки:");
        System.out.println("   phantomRef.get(): " + phantomRef.get());

        // Запускаем сборку мусора
        System.out.println("\n3. Запускаем GC...");
        System.gc();
        Thread.sleep(1000);

        // Проверяем очередь
        System.out.println("   queue.poll(): " + queue.poll());

        // Еще раз запускаем GC (иногда нужно несколько раз)
        System.out.println("\n4. Еще один GC...");
        System.gc();
        Thread.sleep(1000);

        Reference<? extends LargeObject> refFromQueue = queue.poll();
        System.out.println("   refFromQueue: " + refFromQueue);
        System.out.println("   refFromQueue == phantomRef: " +
                           (refFromQueue == phantomRef));
    }

    private static class LargeObject {
        private String name;
        private byte[] data = new byte[10 * 1024 * 1024]; // 10MB

        public LargeObject(String name) {
            this.name = name;
            System.out.println("Создан LargeObject: " + name);
        }

        @Override
        public String toString() {
            return "LargeObject{" + name + "}";
        }

        // Обычно НЕ переопределяем finalize() при использовании PhantomReference
    }
}


