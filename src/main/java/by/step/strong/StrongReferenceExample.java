package by.step.strong;

public class StrongReferenceExample {
    public static void main(String[] args) {
        // Strong reference - обычная ссылка
        Object strongRef = new Object();

        // Объект не будет удален GC, пока существует strong reference
        System.out.println("Strong reference: " + strongRef);

        // Удаляем strong reference
        strongRef = null;

        // Теперь объект может быть удален GC
        System.gc();
        System.out.println("Strong reference установлена в null");
    }
}