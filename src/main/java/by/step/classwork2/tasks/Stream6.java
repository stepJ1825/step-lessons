package by.step.classwork2.tasks;

import java.util.List;

public class Stream6 {
    /*
    Какой фрагмент кода следует вставить вместо комментария line 1, чтобы
в консоль было выведено значение true? (выбрать один)
a) noneMatch(i -> i == 2);
b) allMatch(i -> i == 2);
c) anyMatch(i ->i == 2);
d) findFirst().
     */
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 1, 7);
        boolean res = integers.stream()
//                .noneMatch(i -> i == 2);// line 1
//                .allMatch(i -> i == 2);// line 1
                .anyMatch(i -> i == 2);// line 1
//                .findFirst();// line 1
        System.out.print(res);
    }
}
