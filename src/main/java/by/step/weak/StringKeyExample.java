package by.step.weak;

import java.util.WeakHashMap;

public class StringKeyExample {

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<String, String> weakMap = new WeakHashMap<>();

        // Создаем строки разными способами
        String key1 = new String("key1"); // В heap
        String key2 = "key2"; // В string pool
        String key3 = new String("key3").intern(); // В string pool

        weakMap.put(key1, "value1");
        weakMap.put(key2, "value2");
        weakMap.put(key3, "value3");

        System.out.println("Исходное состояние:");
        System.out.println("Размер: " + weakMap.size());
        System.out.println();

        // Удаляем by.step.strong references
        key1 = null; // Будет удален GC
        key2 = null; // Не будет удален (в string pool)
        key3 = null; // Не будет удален (в string pool)

        System.gc();
        Thread.sleep(1000);

        System.out.println("После сборки мусора:");
        System.out.println("Размер: " + weakMap.size());
        System.out.println("Содержимое: " + weakMap);

        // Проверяем доступность через литералы
        System.out.println("Доступ через 'key2': " + weakMap.get("key2"));
        System.out.println("Доступ через 'key3': " + weakMap.get("key3"));
    }
}
