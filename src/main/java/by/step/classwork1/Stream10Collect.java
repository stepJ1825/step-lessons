package by.step.classwork1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream10Collect {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(10, 20, 30)
                .collect(Collectors.toList()); // - возвращает модифицируемую коллекцию
                //.toList(); - возвращает НЕмодифицируемую коллекцию
        list.remove(Integer.valueOf(10));
        System.out.println(list);





        String s = Stream.of(1, 2, 3)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "<", ">"));
// s: "<1-2-3>"

        System.out.println();
    }
}
