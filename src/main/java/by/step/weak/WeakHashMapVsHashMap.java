package by.step.weak;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapVsHashMap {

    public static void main(String[] args) throws InterruptedException {
        // Создаем WeakHashMap и HashMap для сравнения
        Map<Key, String> weakMap = new WeakHashMap<>();
        Map<Key, String> strongMap = new HashMap<>();

        // Создаем ключи
        Key key1 = new Key("weak_key");
        Key key2 = new Key("strong_key");

        // Добавляем в обе мапы
        weakMap.put(key1, "Weak Value");
        strongMap.put(key2, "Strong Value");

        System.out.println("Исходное состояние:");
        System.out.println("WeakHashMap: " + weakMap);
        System.out.println("HashMap: " + strongMap);
        System.out.println();

        // Удаляем by.step.strong references
        key1 = null;
        key2 = null;

        System.out.println("Удалили by.step.strong references на оба ключа");

        // Принудительно вызываем сборщик мусора
        System.gc();
        Thread.sleep(1000);

        System.out.println("После сборки мусора:");
        System.out.println("WeakHashMap: " + weakMap);
        System.out.println("HashMap: " + strongMap);
        System.out.println("Размер WeakHashMap: " + weakMap.size());
        System.out.println("Размер HashMap: " + strongMap.size());
    }

    static class Key {
        private String name;

        public Key(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("GC collected: " + name);
            super.finalize();
        }
    }
}
