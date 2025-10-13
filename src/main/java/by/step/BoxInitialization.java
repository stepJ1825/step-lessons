package by.step;

public class BoxInitialization {
    public static void main(String[] args) {
        Box<? super String> b1 = new Box<> (123);
        Box<? extends String> b2 = new Box<>("123");
        Box<? extends Number> b3 = new Box<>(123);
        Box<? super Number> b4 = new Box<>(123L);
        System.out.println(b1.value.getClass());
        System.out.println(b1.getClass());
    }
}

class Box<T> {
    T value;
    public Box (T value) {
        this.value = value;
    }
}


