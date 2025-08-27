package by.step.classwork2.tasks;

import java.util.Map;
import java.util.stream.Collectors;

public class Stream9 {

    public static void main(String[] args) {
        // Получить Map, где ключ это символ в строке, а значение то,
        // сколько раз этот символ встречается
        //input1: aaabbbbccccc
        //input2: aaabbbaannnntt
        //input3: ""
        //input4: null

        Map<String, Long> collect = "aaabbbbccccc".chars()
                .mapToObj(operand -> String.valueOf(
                        (char) operand))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }
}
