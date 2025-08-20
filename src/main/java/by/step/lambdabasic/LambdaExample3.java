package by.step.lambdabasic;

public class LambdaExample3 {

    int x = 10;
    int y = 20;

    public static void main(String[] args) {
        extracted1();
        extracted2();
    }

    private static void extracted1() {
        LambdaExample3 example3 = new LambdaExample3();
        Operation op = () -> {
            example3.x = 30;
            return example3.x + example3.y;
        };
        System.out.println(op.calculate()); // 50
        System.out.println(example3.x); // 30 - значение x изменилось
    }

    private static void extracted2() {
        int n = 70;
        int m = 30;
        Operation op = () -> {
//            n = 100; // - так нельзя сделать
            return m + n;
        };
//        n = 100;  // - так тоже нельзя
        System.out.println(op.calculate()); // 100
    }


}

interface Operation {
    int calculate();
}