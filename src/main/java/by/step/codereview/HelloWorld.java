package by.step.codereview;

public class HelloWorld {
    public static void main(String[] args) {
        foo();
    }
    static void foo() {
        StringBuilder m = new StringBuilder("Hello");
        System.out.println(m);
        bar(m);
        System.out.println(m);
    }

    static void bar(StringBuilder m) {
        m.append(" World!");
    }
}
