package by.step.weak;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        // Создаем WeakHashMap
        Map<Key, String> weakMap = new WeakHashMap<>();

        // Создаем ключи
        Key key1 = new Key("key1");
        Key key2 = new Key("key2");
        Key key3 = new Key("key3");

        // Добавляем значения
        weakMap.put(key1, "Value 1");
        weakMap.put(key2, "Value 2");
        weakMap.put(key3, "Value 3");

        System.out.println("Map после добавления элементов:");
        System.out.println("Размер: " + weakMap.size());
        System.out.println("Содержимое: " + weakMap);
        System.out.println();

        // Удаляем by.step.strong reference на key2
        key2 = null;
        System.out.println("Удалили by.step.strong reference на key2");

        // Принудительно вызываем сборщик мусора
        System.gc();
        Thread.sleep(1000); // Даем время GC

        System.out.println("После сборки мусора:");
        System.out.println("Размер: " + weakMap.size());
        System.out.println("Содержимое: " + weakMap);
        System.out.println();

        // Удаляем by.step.strong reference на остальные ключи
        key1 = null;
        key3 = null;
        System.out.println("Удалили by.step.strong references на key1 и key3");

        // Принудительно вызываем сборщик мусора
        System.gc();
        Thread.sleep(1000);

        System.out.println("После второй сборки мусора:");
        System.out.println("Размер: " + weakMap.size());
        System.out.println("Содержимое: " + weakMap);
    }

    // Вспомогательный класс для ключа
    static class Key {
        private String name;

        public Key(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        // Переопределяем finalize для отслеживания сборки мусора
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Сборщик мусора удалил ключ: " + name);
            super.finalize();
        }
    }
}
