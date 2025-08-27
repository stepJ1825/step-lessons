package by.step.classwork2.tasks;

import java.util.Collections;
import java.util.List;

public class Stream11 {

    public static void main(String[] args) {
        List<String> strings = inWords(List.of("app", "gap", "map","zip"), List.of("apple", "maple", "zipline"));
        System.out.println(strings);
        System.out.println(List.of("app", "map", "zip"));
    }

    /**
     * Example: parts = {"app", "gap", "map", "zip"}
     *          words = {"apple", "maple", "zipline"}
     *          return = {"app", "map", "zip"}
     *
     * @param parts - части слов
     * @param words - слова
     * @return отсортированные уникальные части слов которые содержатся в словах
     */
    public static List<String> inWords(List<String> parts, List<String> words) {
        return Collections.emptyList();
    }
}
