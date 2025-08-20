package by.step.classwork1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream12CollectExample {
    public static void main(String[] args) {
        Set<Integer> collect = Stream.of(1, 2, 5, 74, 3, 1)
                                     .collect(Collectors.toCollection(LinkedHashSet::new));


        Map<String, List<String>> collect1 =
                "DADWAHJDKLAWYUIOEGHJALDHAWJUIDOLAWUIO".chars()
                                                       .mapToObj(operand -> String.valueOf(
                                                               (char) operand))
                                                       .collect(Collectors.groupingBy(s -> s));

        Map<Boolean, List<Integer>> collect2 =
                Stream.of(1, 2, 3, 7, 8, 9)
                      .collect(Collectors.partitioningBy(integer -> integer >= 5));

        System.out.println();
    }
}
