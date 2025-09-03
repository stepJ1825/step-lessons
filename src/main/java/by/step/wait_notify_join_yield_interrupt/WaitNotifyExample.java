package by.step.wait_notify_join_yield_interrupt;

public class WaitNotifyExample {
    private final Object someObject = new Object();
    private boolean condition = false;

    public void waitMethod() throws InterruptedException {
        synchronized (someObject) {
            System.out.println(Thread.currentThread().getName() + " входит в wait");
            while (!condition) {
                someObject.wait(); // Ожидание уведомления
            }
            System.out.println(Thread.currentThread().getName() + " продолжает работу");
        }
    }

    public void notifyMethod() {
        synchronized (someObject) {
            System.out.println("Отправка уведомления...");
            condition = true;
//            someObject.notify(); // Пробуждает один поток
            someObject.notifyAll(); // Пробуждает все потоки
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
        }, "Waiter1-Thread");
        Thread waiter2 = new Thread(() -> {
            try {
                example.waitMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Waiter2-Thread");

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
        waiter2.start();
        notifier.start();

        waiter.join();
        waiter2.join();
        notifier.join();
    }
}