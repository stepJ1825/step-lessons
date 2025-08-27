package by.step.classwork2.tasks;

import by.step.classwork2.model.Worker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                .reduce(0, (sum1, worker) ->
                        sum1 + worker.getSalary(), Integer::sum);

        int sum1 = workers.stream().mapToInt(Worker::getSalary).sum();

        // сгруппировать людей по позициям
        Map<String, List<Worker>> workersByPosition = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition));

        // какую сумму мы платим каждому отделу
        Map<String, Integer> salarySumByPosition = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.summingInt(Worker::getSalary)));

        // Сколько людей занимают конкретную позицию
        Map<String, Integer> countByPosition = workers.stream()
                .collect(Collectors.groupingBy(Worker::getPosition,
                        Collectors.reducing(0, e -> 1, Integer::sum)));
        //TODO переписать последнюю строку на collectingAndThen c Collectors.counting()

        // разделить сотрудников на тех кто получает больше 100 и меньше 100
        Map<Boolean, List<Worker>> workersBySalaryCondition = workers.stream()
                .collect(Collectors.partitioningBy(worker -> worker.getSalary() > 100));

        System.out.println();

    }
}
