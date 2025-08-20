package by.step.lambdabasic;

/**
 * Также метод в Java может возвращать лямбда-выражение
 */
public class LambdaExample8 {

    public static void main(String[] args) {
        Operation8 func = action("sum");
        int a = func.execute(6, 5);
        System.out.println(a);          // 11

        int b = action("multiply").execute(8, 2);
        System.out.println(b);          // 16

        Operation8 operation8asObject = new Operation8Impl();
        operation8asObject.someMethod();
    }

    private static Operation8 action(String action) {
        switch (action) {
            case "sum":
                return Integer::sum;
            case "difference":
                return (x, y) -> x - y;
            case "multiply":
                return (x, y) -> x * y;
            default:
                return (x, y) -> 0;
        }
    }
}

@FunctionalInterface
interface Operation8 {
    int execute(int x, int y);
    default void someMethod(){
        System.out.println("Default method");
    }
}

class Operation8Impl implements Operation8{

    @Override
    public int execute(int x, int y) {
        return 0;
    }
}

