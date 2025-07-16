package by.step;

import java.util.List;

public class StringUtils {
    public List<String> getWordsLongerThan(String text, int length) {
        return List.of(text.split(" "))
                   .stream()
                   .filter(word -> word.length() > length)
                   .toList();
    }
}
