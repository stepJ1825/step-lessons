package by.step.lambdabasic;

/**
 * Ссылка на метод передается в виде
 * имя_класса::имя_статического_метода (если метод статический) или
 * объект_класса::имя_метода (если метод нестатический).
 */
public class LambdaExample6 {
    public static void main(String[] args) {

        int[] nums = { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        System.out.println(sum(nums, ExpressionHelper::isEven));

        Expression6 expr = ExpressionHelper::isPositive;
        System.out.println(sum(nums, expr));
    }

    private static int sum (int[] numbers, Expression6 func)
    {
        int result = 0;
        for(int i : numbers)
        {
            if (func.isEqual(i))
                result += i;
        }
        return result;
    }
}

// функциональный интерфейс
interface Expression6{
    boolean isEqual(int n);
}
// класс, в котором определены методы
class ExpressionHelper{

    static boolean isEven(int n){

        return n%2 == 0;
    }

    static boolean isPositive(int n){

        return n > 0;
    }
}