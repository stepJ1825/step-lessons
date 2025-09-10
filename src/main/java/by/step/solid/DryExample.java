package by.step.solid;

public class DryExample {
    public static void main(String[] args) {
        sum1(1, 2);
        sum2(3, 4);
    }

    public static void sum1(int x, int y) {
        System.out.println("start in method sum1");
        extracted(x, y);
    }

    public static void sum2(int x, int y) {
        System.out.println("start in method sum2");
        extracted(x, y);
    }

    private static void extracted(int x, int y) {
        System.out.println("start");
        int i = x + y;
        System.out.println("result" + i);
    }
}
