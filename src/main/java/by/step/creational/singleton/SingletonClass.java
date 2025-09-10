package by.step.creational.singleton;


public class SingletonClass {
    // 1. Статическое поле для хранения единственного экземпляра класса
    private static SingletonClass instance;
    private String message;

    // 2. Приватный конструктор, чтобы предотвратить создание экземпляров извне
    private SingletonClass(String message) {
        this.message = message;
        System.out.println("Инициализация Singleton.");
    }

    // 3. Статический публичный метод для получения экземпляра класса
    public static SingletonClass getInstance() {
        // Ленивая инициализация: экземпляр создается только при первом вызове
        if (instance == null) {
            instance = new SingletonClass("Привет, это единственный экземпляр!");
        }
        return instance;
    }

    // Пример метода, который будет вызываться у единственного экземпляра
    public void showMessage() {
        System.out.println(message);
    }
}
