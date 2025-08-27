package by.step.classwork2.tasks;

import by.step.classwork2.model.PersonRecord;

import java.util.Comparator;
import java.util.List;

public class Stream5 {
    public static void main(String[] args) {
        //выполнить сортировку по фамилии в обратном порядке, затем по имени в обратном порядке
        Comparator<PersonRecord> personSurnameRevesedComparator = (o1, o2) -> {
            String[] s1 = o1.fullName().split(" ");
            String[] s2 = o2.fullName().split(" ");
            return s2[1].compareTo(s1[1]);
        };
        Comparator<PersonRecord> personNameReversedComparator = (o1, o2) -> {
            String[] s1 = o1.fullName().split(" ");
            String[] s2 = o2.fullName().split(" ");
            return s2[0].compareTo(s1[0]);
        };
        getPersonList().stream()
                .sorted(personSurnameRevesedComparator.thenComparing(personNameReversedComparator))
                .forEach(System.out::println);
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

