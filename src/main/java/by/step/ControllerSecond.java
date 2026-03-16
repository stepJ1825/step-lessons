package by.step;

import java.util.List;

public class ControllerSecond {
    public static void main(String[] args) {
        WordCounter singletonWordCounter = new WordCounter();
        List<String> words1 = singletonWordCounter.getWords(10);
        System.out.println(words1);
        System.out.println("-------------------");
        WordCounterSecond singletonWordCounterSecond = new WordCounterSecond();
        List<String> words2 = singletonWordCounterSecond.getWords(10);
        System.out.println(words2);
    }
}
