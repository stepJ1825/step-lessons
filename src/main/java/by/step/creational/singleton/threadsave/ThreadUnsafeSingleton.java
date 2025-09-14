package by.step.creational.singleton.threadsave;



public class ThreadUnsafeSingleton {
    private static ThreadUnsafeSingleton instance;
    private static int instanceCount = 0;

    // Приватный конструктор
    private ThreadUnsafeSingleton() {
        instanceCount++;
        System.out.println("Создан экземпляр №" + instanceCount + " в потоке: " + Thread.currentThread().getName());
    }

    // Публичный метод для получения экземпляра
    public static ThreadUnsafeSingleton getInstance() {
        if (instance == null) {
            // Имитация задержки для увеличения вероятности race condition
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new ThreadUnsafeSingleton();
        }
        return instance;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }
}
