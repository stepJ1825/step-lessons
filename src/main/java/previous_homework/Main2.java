package previous_homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        checkCountWord("", "");


        //НАРУШЕНИЕ ООП
        SimpleSearch simpleSearch = new SimpleSearch();
        simpleSearch.countWholeWord("", "");


        List<String> stringList = new ArrayList<>();
        stringList = new LinkedList<>();
    }

    private static void checkCountWord(String text, String word) {
        System.out.println("simpleSearch " + simpleSearch(text,word));;
        System.out.println("regexSearch " + regexSearch(text,word));;
    }

    private static int regexSearch(String text, String word) {
        Search searcher;
        searcher = new RegexSearch();
        return searcher.countWords(text, word);
    }

    private static int simpleSearch(String text, String word) {
        Search searcher = new SimpleSearch();
        return searcher.countWords(text, word);
    }
}
