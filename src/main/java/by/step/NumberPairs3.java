package by.step;

import java.util.*;

public class NumberPairs3 {
    public static void numberPairs(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        List<int[]> res = new ArrayList<>();

        Arrays.stream(arr).forEach(x -> {
            int c = target - x;
            if (!used.contains(x) && !used.contains(c) && seen.contains(c)) {
                used.add(x);
                used.add(c);
                res.add(new int[]{x, c});
            }
            seen.add(x);
        });
        res.forEach(p -> System.out.println(p[0] + "  " + p[1]));
    }

    public static void main(String[] args) {
        int[] array = {5, 7, 2, 3,9,1,3,5,2,5,6,1,5,3,2,4,2,7};
        int target = 9;
        numberPairs(array, target);

    }
}
