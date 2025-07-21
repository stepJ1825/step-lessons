package homework;

import java.util.Set;

public class DelimitersExample {

    private static String delimiter1 = ",";
    private static String delimiter2 = ".";
    private static String delimiter3 = "!";
    private static String delimiter4 = "?";
    private static Set<String> delimiters =
         Set.of(delimiter1, delimiter2, delimiter3, delimiter4);

    public static void main(String[] args) {
        String s = ",";
        System.out.println(delimiters.contains(s));

//        String delimiter1 = ",";
//        String delimiter2 = ".";
//        String delimiter3 = "!";
//        String delimiter4 = "?";
//        Set<String> localDelimiters =
//                Set.of(delimiter1, delimiter2, delimiter3, delimiter4);
    }
}
