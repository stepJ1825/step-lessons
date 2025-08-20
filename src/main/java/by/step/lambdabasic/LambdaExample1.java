package by.step.lambdabasic;

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
        Operationable operation2;                 //Определение ссылки на функциональный интерфейс
        operation = (x, y) -> x - y;      //Создание лямбда-выражения
        operation2 = Integer::max;      //Создание лямбда-выражения

        int result = operation.calculate(10, 20); //Использование лямбда-выражения в виде вызова метода интерфейса
        System.out.println(result); //30

        System.out.println(operation2.calculate(10,110));
    }
}

interface Operationable {
    int calculate(int x, int y);
    default void someMethod(){
        System.out.println("default method");
    }
}