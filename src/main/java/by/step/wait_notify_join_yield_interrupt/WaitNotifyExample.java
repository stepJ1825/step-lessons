package by.step.wait_notify_join_yield_interrupt;

public class WaitNotifyExample {
    private final Object lock = new Object();
    private boolean condition = false;

    public void waitMethod() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " входит в wait");
            while (!condition) {
                lock.wait(); // Ожидание уведомления
            }
            System.out.println(Thread.currentThread().getName() + " продолжает работу");
        }
    }

    public void notifyMethod() {
        synchronized (lock) {
            System.out.println("Отправка уведомления...");
            condition = true;
            lock.notify(); // Пробуждает один поток
            // lock.notifyAll(); // Пробуждает все потоки
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyExample example = new WaitNotifyExample();

        // Поток, который будет ждать
        Thread waiter = new Thread(() -> {
            try {
                example.waitMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Waiter-Thread");

        // Поток, который отправит уведомление
        Thread notifier = new Thread(() -> {
            try {
                Thread.sleep(2000); // Имитация работы
                example.notifyMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Notifier-Thread");

        waiter.start();
        notifier.start();

        waiter.join();
        notifier.join();
    }
}