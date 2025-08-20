package by.step.lambdabasic;

public class LambdaExample3 {

    static int x = 10;
    static int y = 20;

    public static void main(String[] args) {
        extracted1();
        extracted2();
    }

    private static void extracted1() {
        Operation op = () -> {
            x = 30;
            return x + y;
        };
        System.out.println(op.calculate()); // 50
        System.out.println(x); // 30 - значение x изменилось
    }

    private static void extracted2() {
        int n = 70;
        int m = 30;
        Operation op = () -> {
            //            n=100; // - так нельзя сделать
            return m + n;
        };
        //         n=100;  // - так тоже нельзя
        System.out.println(op.calculate()); // 100
    }


}

interface Operation {
    int calculate();
}