package by.step.wait_notify_join_yield_interrupt;

public class ComplexExample {
    private final Object lock = new Object();
    private int counter = 0;
    private final int MAX_COUNT = 5;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (counter < MAX_COUNT) {
                while (counter > 0) {
                    System.out.println("Производитель ждет...");
                    lock.wait();
                }

                counter++;
                System.out.println("Произведено: " + counter);
                Thread.yield(); // Уступаем время
                lock.notifyAll();
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (counter > 0) {
                while (counter == 0) {
                    System.out.println("Потребитель ждет...");
                    lock.wait();
                }

                System.out.println("Потреблено: " + counter);
                counter--;
                Thread.yield();
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ComplexExample example = new ComplexExample();

        Thread producerThread = new Thread(() -> {
            try {
                example.producer();
            } catch (InterruptedException e) {
                System.out.println("Производитель прерван");
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                example.consumer();
            } catch (InterruptedException e) {
                System.out.println("Потребитель прерван");
            }
        });

        Thread interrupter = new Thread(() -> {
            try {
                Thread.sleep(1000);
                producerThread.interrupt();
                consumerThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
        interrupter.start();

        producerThread.join();
        consumerThread.join();
        interrupter.join();

        System.out.println("Все потоки завершили работу");
    }
}
