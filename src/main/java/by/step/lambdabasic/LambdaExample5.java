package by.step.lambdabasic;

/**
 * Одним из преимуществ лямбд в java является то, что их можно передавать в качестве параметров в методы
 */
public class LambdaExample5 {
    public static void main(String[] args) {

        Expression func = (n) -> n % 2 == 0;
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(sum(nums, func)); // 20
        System.out.println(sum(nums, (n) -> n > 5));  // 30 (сумма чисел, которые больше 5)
    }

    private static int sum(int[] numbers, Expression func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i)) {
                result += i;
            }
        }
        return result;
    }
}

interface Expression {
    boolean isEqual(int n);
}

