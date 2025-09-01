package by.step.basic;

public class HelloRunnable implements Runnable {

    private final int threadNum;

    public HelloRunnable(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        System.out.println("Hello from " + threadNum + " " +
                Thread.currentThread().getName() + " !");
        try {
            Thread.sleep(3000L * threadNum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Goodbye from " + threadNum + " " +
                Thread.currentThread().getName() + " !");
    }

}