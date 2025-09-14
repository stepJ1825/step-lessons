package by.step.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * Основная концепция:
 * PhantomReference - это механизм для отслеживания момента, когда объект уже окончательно удален из памяти (после вызова finalize()), но до того как память будет переиспользована.
 * Ключевые особенности
 * - Всегда возвращает null из метода get()
 * - Не препятствует сборке мусора
 * - Попадает в ReferenceQueue после финализации объекта
 * - Используется для cleanup ресурсов, которые не управляются GC
 */
public class PhantomReferenceExample {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();

        // Создаем объект с фантомной ссылкой
        Object object = new LargeResource("Большой ресурс");
        PhantomReference<Object> phantomRef = new PhantomReference<>(object, queue);

        System.out.println("Фантомная ссылка создана");
        System.out.println("До GC: " + phantomRef.get()); // Всегда null!

        // Удаляем strong reference
        object = null;

        // Ждем окончания finalization
        System.gc();
        Thread.sleep(100);

        // Проверяем очередь на наличие фантомных ссылок
        Reference<?> refFromQueue;
        while ((refFromQueue = queue.poll()) != null) {
            if (refFromQueue == phantomRef) {
                System.out.println("Фантомная ссылка в очереди - объект окончательно удален");
                // Здесь можно освободить ресурсы
                cleanupResources();
            }
        }
    }

    private static void cleanupResources() {
        System.out.println("Очистка ресурсов...");
    }

    static class LargeResource {
        private String name;
        private byte[] data = new byte[10 * 1024 * 1024]; // 10 MB

        public LargeResource(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalize для LargeResource: " + name);
            super.finalize();
        }
    }
}
