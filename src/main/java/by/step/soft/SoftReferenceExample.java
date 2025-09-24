package by.step.soft;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftReferenceExample {
    public static void main(String[] args) throws InterruptedException {
        // Создаем объект с мягкой ссылкой
        Thread.sleep(20000);
        Object largeObject = new LargeObject("Большие данные");
        SoftReference<Object> softRef = new SoftReference<>(largeObject);

        System.out.println("До очистки: " + softRef.get());

        // Удаляем strong reference
        largeObject = null;

        // Создаем нагрузку на память чтобы вызвать очистку soft references
        Thread.sleep(5000);
        try {
            List<byte[]> memoryHog = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                memoryHog.add(new byte[10 * 1024 * 1024]); // 10 MB
                Thread.sleep(100);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError пойман!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("После нагрузки: " + softRef.get());
    }

    static class LargeObject {
        private String data;
        private byte[] buffer = new byte[5 * 1024 * 1024]; // 5 MB

        public LargeObject(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "LargeObject{data='" + data + "'}";
        }
    }
}
