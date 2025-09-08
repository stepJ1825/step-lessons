package by.step.creational.singleton;

public class SingletonExample {
    public static void main(String[] args) {
        // Получаем экземпляр через метод getInstance()
        SingletonClass singleton1 = SingletonClass.getInstance();
        SingletonClass singleton2 = SingletonClass.getInstance();

        // Убеждаемся, что оба объекта - это один и тот же экземпляр
        System.out.println("Singleton1 == Singleton2: " + (singleton1 == singleton2)); // true

        singleton1.showMessage();
        singleton2.showMessage();

    }
}
