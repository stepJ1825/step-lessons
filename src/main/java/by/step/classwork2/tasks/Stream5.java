package by.step.classwork2.tasks;

import by.step.classwork2.model.PersonRecord;

import java.util.List;

public class Stream5 {
    public static void main(String[] args) {
        //выполнить сортировку по фамилии в обратном порядке, затем по имени в обратном порядке
        //getPersonList().stream()
    }

    private static List<PersonRecord> getPersonList() {
        PersonRecord p1 = new PersonRecord(1, "John Smith", 40);
        PersonRecord p2 = new PersonRecord(2, "Jack Daniels", 35);
        PersonRecord p3 = new PersonRecord(3, "Sam Smith", 15);
        PersonRecord p4 = new PersonRecord(4, "Tom Holland", 25);
        PersonRecord p5 = new PersonRecord(5, "Tom Cruise", 60);

        return List.of(p1, p2, p3, p4, p5);
    }
}

