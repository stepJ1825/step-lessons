package by.step.creational.singleton.threadsave;

public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    private static int instanceCount = 0;

    private ThreadSafeSingleton() {
        instanceCount++;
        System.out.println("Создан экземпляр №" + instanceCount + " в потоке: " + Thread.currentThread().getName());
    }

    //Только один поток может одновременно выполнять код, защищенный синхронизацией
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    // Потокобезопасная реализация с double-checked locking
    public static ThreadSafeSingleton getInstanceDCL() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }
}