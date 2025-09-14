package by.step.creational.singleton.threadsave;

/**
 * Для получения экземпляра: EnumSingleton.INSTANCE.someMethod();
 */
public enum EnumSingleton {
    INSTANCE; // Единое властвующее над всеми ресурсами

    public int someMethod(int x, int y) {
        return x + y;
    }
}

