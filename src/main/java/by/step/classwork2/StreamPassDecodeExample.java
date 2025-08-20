package by.step.classwork2;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPassDecodeExample<T> {

    public static void main(String[] args) {
        // Пример шифровки и расшифровки фразы одного слова.
        String str = new String(Base64.getEncoder().encode("Hello".getBytes()));
        System.out.println(str);
        byte[] decode = Base64.getDecoder().decode(str.getBytes());
        String s1 = new String(decode);
        System.out.println(s1);

        List<List<String>> lists = List.of(
                List.of("SGVsbG8=","d29ybGQ="),
                List.of("SG93","YXJl","eW91Pw==")
        );

        String collect = lists.stream()
                .flatMap(strings -> strings.stream())
                .map(s -> new String(Base64.getDecoder().decode(s)))
//                .peek(System.out::println)
                .collect(Collectors.joining(", "));

        System.out.println(collect);


    }
}
