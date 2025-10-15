package by.step;

public class BoxInitialization {
    public static void main(String[] args) {
        Box<? super String> b1 = new Box<> (new Snail()); //123 компилятор видит как Object
//        String value = (String) b1.value;
//        Object value = b1.value;
//        System.out.println(value.getClass());
//        System.out.println(b1.getClass());

        Box<? extends String> b2 = new Box<>("123");
//        String value1 = b2.value;
//        Class<? extends String> aClass = value1.getClass();
//        if (aClass.equals(String.class)) {
//            System.out.println("String class");
//        }

        Box<? extends Number> b3 = new Box<>(123D);
//        Integer b3IntegerValue = (Integer) b3.value;
//        Double b3DoubleValue = (Double) b3.value;

        Box<? super Number> b4 = new Box<>(123L);
//        Object value = b4.value;
    }
}

class Box<T> {
    T value;
    public Box (T value) {
        this.value = value;
    }
}


