package by.step;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WordCounterSecond {
//    private static final int wordCount = 4;        // static ?, magic number
//    private List<String> stringList;    // static ?   ArrayList->List

    public /*static*/ List<String> getWords(int wordCount) {     //static ?   ArrayList->List
        List<String> stringList = new ArrayList<>();
        String response = requestRandomWords();
        //log.debug("get words: " +response);
        JSONArray jsonArray = new JSONArray(response);
        int length = jsonArray.length();
        int counter = Math.min(length, wordCount);

        try {
            for (int i = 0; i < counter; i++) {
                String word = jsonArray.getJSONObject(i).getString("word");
                if (!word.isBlank()) {
                    stringList.add(word);
                } else if (counter < length) {
                    counter++;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // replace on log.error(e.getMessage);
        }
        return stringList;
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