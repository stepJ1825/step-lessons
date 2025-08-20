package by.step.lambdabasic;

public class LambdaExample4 {

    public static void main(String[] args) {

        Operationable4<Integer> operation1 = (x, y) -> x + y;
        Operationable4<String> operation2 = (x, y) -> x + y;

        System.out.println(operation1.calculate(20, 10)); //30
        System.out.println(operation2.calculate("20", "10")); //2010
    }
}

interface Operationable4<T> {
    T calculate(T x, T y);
}