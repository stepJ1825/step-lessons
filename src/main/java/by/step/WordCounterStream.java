package by.step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
public class WordCounterStream {
//    private static final int wordCount = 4;        // static ?, magic number
//    private List<String> stringList;    // static ?   ArrayList->List

    public /*static*/ List<String> getWords(int wordCount) {     //static ?   ArrayList->List
        String response = requestRandomWords();
        //log.debug("get words: " +response);
        JSONArray jsonArray = new JSONArray(response);
//        Stream.iterate()
//        Stream.generate()
        return IntStream.range(0, jsonArray.length())
                .boxed()
                .map(i -> {
                    String word = "";
                    try {
                        word = jsonArray.getJSONObject(i).getString("word");
                    } catch (Exception e) {
                        System.err.println(e.getMessage()); // replace on log.error(e.getMessage);
                    }
                    return word;
                })
                .filter(s -> !s.isBlank())
                .limit(wordCount)
                .toList();
    }

    private static String requestRandomWords() {
        //go to some resource and return JSON
        return WordCounterUtils.REQUEST_JSON;
    }
}

/*
Задача: сделать ревью кода и исправить ошибки.
Что происходит:
1. Получаем методом requestRandomWords() некий JSON. JSON представляет собой массив объектов с полями "name", "word", "type".
2. Метод getWords() использую полученный JSON достаёт необходимое количество (wordCount) слов (значений ключа "word"),
и собирает их в результирующий массив.
ВНИМАНИЕ:
- строки нулевой длинны и строки, состоящие из пробелов, не должны попадать в итоговый массив.
- длинна внутреннего массива JSON может быть меньше переменной wordCount. В этом случае необходимо взять все возможные слова из JSON.
 */