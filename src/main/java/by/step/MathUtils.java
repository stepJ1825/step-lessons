package by.step;

public class MathUtils {
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return (double) a / b;
    }
}
