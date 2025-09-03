package by.step.wait_notify_join_yield_interrupt;

public class YieldExample {
    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Производитель: создание элемента " + i);
                Thread.yield(); // Уступает процессорное время другим потокам
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Потребитель: обработка элемента " + i);
                Thread.yield();
            }
        });

        producer.start();
        consumer.start();
    }
}
