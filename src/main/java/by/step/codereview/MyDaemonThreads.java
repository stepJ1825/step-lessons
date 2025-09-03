package by.step.codereview;

public class MyDaemonThreads {
    private static class MyDaemonThread extends Thread {
        public MyDaemonThread() {
            setDaemon(true);
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyDaemonThread();
        thread.start();
        thread.join();
        System.out.println(thread.isAlive());
    }
}
