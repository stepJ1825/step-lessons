package by.step;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static void main(String[] args) {
        WordCounter singletonWordCounter = new WordCounter();
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                List<String> words = singletonWordCounter.getWords(10);
                System.out.println(words);
            }).start();
        }
    }
}
