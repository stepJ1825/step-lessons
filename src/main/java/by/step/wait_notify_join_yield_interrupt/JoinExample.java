package by.step.wait_notify_join_yield_interrupt;

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Главный поток начал работу");

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Поток 1 начал работу");
                Thread.sleep(2000);
                System.out.println("Поток 1 завершил работу");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Поток 2 начал работу");
                Thread.sleep(1000);
                System.out.println("Поток 2 завершил работу");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        // Главный поток ждет завершения thread1
        thread1.join();
        System.out.println("Поток 1 завершился, главный поток продолжает работу");

        // Ждем завершения thread2
        thread2.join();
        System.out.println("Поток 2 завершился, главный поток завершает работу");
    }
}
