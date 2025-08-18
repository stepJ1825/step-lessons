package by.step.lambdabasic;

/**
 * Примеры взяты из статьи https://metanit.com/java/tutorial/9.1.php
 */
public class LambdaExample1 {
    public static void main(String[] args) {
        //Использование анонимных классов
        Operationable op = new Operationable() {
            public int calculate(int x, int y) {
                return x + y;
            }
        };
        int z = op.calculate(20, 10);
        System.out.println(z); // 30

        //Использование лямбда выражения
        Operationable operation;                 //Определение ссылки на функциональный интерфейс
        operation = (x, y) -> x + y;      //Создание лямбда-выражения

        int result = operation.calculate(10, 20); //Использование лямбда-выражения в виде вызова метода интерфейса
        System.out.println(result); //30
    }
}

interface Operationable {
    int calculate(int x, int y);
}