package by.step.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Сравнение PhantomReference и finalize()
 */
public class PhantomVsFinalize {

    public static void main(String[] args) throws Exception {
        System.out.println("=== СРАВНЕНИЕ PHANTOM REFERENCE И FINALIZE() ===\n");

        // Тест с finalize()
        System.out.println("1. ТЕСТ С FINALIZE():");
        WithFinalize obj1 = new WithFinalize("Object-with-finalize");
        obj1 = null;
        System.gc();
        Thread.sleep(1000);

        // Тест с PhantomReference
        System.out.println("\n2. ТЕСТ С PHANTOM REFERENCE:");
        ReferenceQueue<SimpleObject> queue = new ReferenceQueue<>();
        SimpleObject obj2 = new SimpleObject("Object-with-phantom");
        PhantomReference<SimpleObject> phantomRef =
                new PhantomReference<>(obj2, queue);

        obj2 = null;

        System.gc();
        Thread.sleep(1000);

        // Проверяем очередь
        if (queue.poll() != null) {
            System.out.println("✅ PhantomReference добавлена в очередь после GC");
        } else {
            System.out.println("❌ PhantomReference не добавлена в очередь");
        }

        System.out.println("\n3. ПРЕИМУЩЕСТВА PHANTOM REFERENCE:");
        System.out.println("   - Гарантированный вызов (finalize() может не вызваться)");
        System.out.println("   - Контролируемое выполнение в отдельном потоке");
        System.out.println("   - Нет риска 'воскрешения' объекта");
        System.out.println("   - Предсказуемое время выполнения");
    }

    private static class WithFinalize {
        private String name;

        public WithFinalize(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("🔚 finalize() вызван для: " + name);
            super.finalize();
        }
    }

    private static class SimpleObject {
        private String name;

        public SimpleObject(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "SimpleObject{" + name + "}";
        }
    }
}


