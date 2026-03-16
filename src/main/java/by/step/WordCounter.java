package by.step;

import java.util.ArrayList;

@Service
@Slf4j
public class WordCounter {
    private static final int wordCount = 10;
    private static ArrayList<String> stringList;

    public static ArrayList<String> getWords() {
        stringList = new ArrayList<>();
        String response = requestRandomWords();
        //log.debug("get words: " +response);
        for (int i = 0; i < wordCount; i++) {
            try {
                stringList.add(new JSONArray(response).getJSONObject(i).getString("word"));
            } catch (Exception e) {
                e.printStackTrace();
            }
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