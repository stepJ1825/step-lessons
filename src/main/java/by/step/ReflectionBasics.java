package by.step;

public class ReflectionBasics {
    public static void main(String[] args) {
        // Три способа получить объект Class

        // 1. Через класс
        Class<String> stringClass = String.class;

        // 2. Через объект
        String str = "Hello";
        Class<?> strClass = str.getClass();

        // 3. Через полное имя класса
        try {
            Class<?> arrayListClass = Class.forName("java.util.ArrayList");
            System.out.println("Class name: " + arrayListClass.getName());
            System.out.println("Simple name: " + arrayListClass.getSimpleName());
            System.out.println("Package: " + arrayListClass.getPackage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
