package by.step.creational.prototype;

import java.util.HashMap;

public class ParametersPrototypeExample {
    public static void main(String[] args) {
        HashMap<String, String> initialHashMap = getInitialMap();

        HashMap<String, String> withSevenParam = getWithSevenParam(initialHashMap);

        HashMap<String, String> withEightParam = getWithEightParam(initialHashMap);

    }

    private static HashMap<String, String> getInitialMap() {
        HashMap<String, String> initialHashMap = new HashMap<>();
        initialHashMap.put("1","one");
        initialHashMap.put("2","two");
        initialHashMap.put("3","three");
        initialHashMap.put("4","four");
        initialHashMap.put("5","five");
        initialHashMap.put("6","six");
        return initialHashMap;
    }

    private static HashMap<String, String> getWithEightParam(HashMap<String, String> initialHashMap) {
        HashMap<String, String> secondClonedMap = new HashMap<>(initialHashMap);
        initialHashMap.put("8","eight");
        return secondClonedMap;
    }

    private static HashMap<String, String> getWithSevenParam(HashMap<String, String> initialHashMap) {
        HashMap<String, String> firstClonedMap = new HashMap<>(initialHashMap);
        firstClonedMap.put("7","seven");
        return firstClonedMap;
    }

    private static HashMap<String, String> clone(HashMap<String, String> initMap){
        return new HashMap<>(initMap);
    }
}
