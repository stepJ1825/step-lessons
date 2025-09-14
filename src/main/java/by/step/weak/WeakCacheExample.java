package by.step.weak;

import java.util.WeakHashMap;

public class WeakCacheExample {

    private static WeakHashMap<Object, String> cache = new WeakHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // Создаем объекты для кэширования
        Object data1 = new Data("Данные 1");
        Object data2 = new Data("Данные 2");
        Object data3 = new Data("Данные 3");

        // Добавляем в кэш
        addToCache(data1, "Результат обработки 1");
        addToCache(data2, "Результат обработки 2");
        addToCache(data3, "Результат обработки 3");

        System.out.println("Кэш после добавления:");
        printCache();
        System.out.println();

        // Удаляем by.step.strong reference на data2
        data2 = null;
        System.out.println("Удалили ссылку на data2");

        // Симулируем работу с другими объектами
        System.out.println("Работаем с data1: " + getFromCache(data1));
        System.out.println("Пытаемся получить data2: " + getFromCache(data2));
        System.out.println();

        // Принудительно вызываем сборщик мусора
        System.gc();
        Thread.sleep(1000);

        System.out.println("После сборки мусора:");
        printCache();
    }

    public static void addToCache(Object key, String value) {
        cache.put(key, value);
    }

    public static String getFromCache(Object key) {
        return cache.get(key);
    }

    public static void printCache() {
        System.out.println("Размер кэша: " + cache.size());
        System.out.println("Содержимое: " + cache);
    }

    static class Data {
        private String content;

        public Data(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Данные удалены из памяти: " + content);
            super.finalize();
        }
    }
}
