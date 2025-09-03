package by.step.wait_notify_join_yield_interrupt;

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        // Поток, который можно прервать
        Thread interruptibleThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Поток работает...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Поток был прерван во время сна");
                    Thread.currentThread().interrupt(); // Повторно устанавливаем флаг прерывания
                }
            }
            System.out.println("Поток завершает работу по прерыванию");
        });

        interruptibleThread.start();

        // Ждем 2 секунды и прерываем поток
        Thread.sleep(2000);
        interruptibleThread.interrupt();

        interruptibleThread.join();
        System.out.println("Главный поток завершил работу");
    }
}
