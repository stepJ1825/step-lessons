package homework;

public class SimpleSearch implements Search {
    @Override
    public int countWords(String text, String word) {
        int counter = getCounter();
//        System.out.println(counter); //нарушение SPR
        return counter;
    }

    private static int getCounter() {
        return 10;
    }

    /**
     * ПЛОХАЯ ПРАКТИКА, НАРУШЕНИЕ ООП!!!
     */
    public int countWholeWord(String text, String word){
        return 10;
    }
}
