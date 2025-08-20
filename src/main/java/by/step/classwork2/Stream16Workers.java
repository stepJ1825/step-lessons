package by.step.classwork2;

import by.step.classwork2.model.Worker;

import java.util.List;

public class Stream16Workers {

    public static void main(String[] args) {
        List<Worker> workers = List.of(
                new Worker("Dan", "Developer", 100),
                new Worker("Maxim", "QA Tester", 50),
                new Worker("Sergey", "Manager", 100),
                new Worker("Vadim", "Developer", 130),
                new Worker("Denis", "QA Tester", 70),
                new Worker("Pavel", "Product Owner", 120)
        );

        // сумма которую платим всем
        Integer sum = workers.stream()
                .reduce(0, (sum1, worker) -> sum1 + worker.getSalary(), Integer::sum);

        // сгруппировать людей по позициям
//        Map<String, List<Worker>> workersByPosition =

        // какую сумму мы платим каждому отделу
//        Map<String, Integer> salarySumByPosition =

        // Сколько людей занимают конкретную позицию
//        Map<String, Integer> countByPosition = workers.stream()

        // разделить сотрудников на тех кто получает больше 100 и меньше 100
//        Map<Boolean, List<Worker>> workersBySalaryCondition =

    }
}
