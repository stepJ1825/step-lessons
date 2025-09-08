package by.step.creational;


public class SingletonExample {
    // 1. Статическое поле для хранения единственного экземпляра класса
    private static SingletonExample instance;
    private String message;

    // 2. Приватный конструктор, чтобы предотвратить создание экземпляров извне
    private SingletonExample(String message) {
        this.message = message;
        System.out.println("Инициализация Singleton.");
    }

    // 3. Статический публичный метод для получения экземпляра класса
    public static SingletonExample getInstance() {
        // Ленивая инициализация: экземпляр создается только при первом вызове
        if (instance == null) {
            instance = new SingletonExample("Привет, это единственный экземпляр!");
        }
        return instance;
    }

    // Пример метода, который будет вызываться у единственного экземпляра
    public void showMessage() {
        System.out.println(message);
    }

    public static void main(String[] args) {
        // Получаем экземпляр через метод getInstance()
        SingletonExample singleton1 = SingletonExample.getInstance();
        SingletonExample singleton2 = SingletonExample.getInstance();

        // Убеждаемся, что оба объекта - это один и тот же экземпляр
        System.out.println("Singleton1 == Singleton2: " + (singleton1 == singleton2)); // true

        singleton1.showMessage();
        singleton2.showMessage();
    }
}
