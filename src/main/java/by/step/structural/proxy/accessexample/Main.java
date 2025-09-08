package by.step.structural.proxy.accessexample;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseProxy database = new DatabaseProxy(new Database());

        List<String> database1 = database.getDatabase();
    }
}
