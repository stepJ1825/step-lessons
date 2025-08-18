package by.step.lambdabasic;

public class LambdaExample2 {

    //        (int x, int y)->x+y;  - принимаем 2 аргумента
    //        ()-> 30 + 20;         - ничего не принимаем
    //        n-> n * n;            - принимаем 1 аргумент, скобки можно не указывать

    public static void main(String[] args) {
        Printable printer = s -> System.out.println(s); // лямбда принимает, но не возвращает никакого значения (void)
        printer.print("Hello Java!");
    }
}

interface Printable {
    void print(String s);
}