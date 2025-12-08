package by.step;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberPairs2 {
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
        System.out.println(getPair2(array, target));
    }

    public static String getPair(int[] array, int target) {
        return IntStream.range(0, array.length)
                .boxed()
                .map(i -> {
                    int[] ints = new int[array.length - i - 1];
                    System.arraycopy(array, i + 1, ints, 0, array.length - i - 1);
                    LinkedHashMap<Integer, Integer> collect = Arrays.stream(ints)
                            .boxed()
                            .collect(Collectors.toMap(Integer::valueOf, Integer::valueOf, (integer1, integer2) -> integer1,
                                    LinkedHashMap::new));
//                    System.out.println(collect + " " + array[i] + " " + array.length);
                    if (collect.containsKey(target - array[i])) {
                        return array[i] + " " + (target - array[i]);
                    }
                    return "";
                })
                .filter(s -> !s.isBlank())
                .toList().toString();
//                .findFirst()
//                .orElseGet(() -> "числа не найдены");
    }

    public static String getPair2(int[] array, int target) {
        Map<Integer, Long> integerCountingMap = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(integer -> integer, Collectors.counting()));
        integerCountingMap.entrySet()
                .forEach(entry -> {
                    boolean containsKey = integerCountingMap.containsKey(target - entry.getKey());
                    if (containsKey) {
                        System.out.println(entry.getKey() + " " + (target - entry.getKey()));
                        Long count = integerCountingMap.get(target - entry.getKey());
                        if (count > 1) {
                            integerCountingMap.put(target - entry.getKey(), --count);
                        } else {
                            integerCountingMap.remove(target - entry.getKey());
                        }
                    }
                });
        return "";

    }


}
