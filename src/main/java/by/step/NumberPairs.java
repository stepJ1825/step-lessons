package by.step;

public class NumberPairs {
    /*
    Дано:
        int[]array = new int[]{5,7,2,3};
        int target = 9;
    Найти пары чисел из array, которые в сумме дают target.

    Требования: одни и те же числа не должны встречаться больше одного раза;
                7 и 2, 2 и 7 из примера выше - ответ неверный,
                должно быть только 7 и 2.

    Можно использовать Java Collection Framework, Stream API.

    Необходимо минимизировать алгоритмическую сложность.
    В идеале - O(n). То есть найти такие пары за один проход по массиву.
     */


    public static void main(String[] args) {
        int[] array = new int[]{5, 5, 5, 5, 5, -5, 4, 5, 5, 5, 1, 1, 2, 3, 10};
//        int[] array = new int[]{5, 7, 2, 3};
        int target = 9;
        System.out.println(getPair(array, target));
    }

    public static String getPair(int[] array, int target) {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length; j++) {
                counter++;
                if (array[i] + array[j] == target) {
                    stringBuilder.append(array[i])
                            .append("  ")
                            .append(array[j])
                            .append("\n");
                }
            }
        }
        System.err.println("Array length: " + array.length + ". Iterations: " + counter);
        return stringBuilder.toString();
    }


}
