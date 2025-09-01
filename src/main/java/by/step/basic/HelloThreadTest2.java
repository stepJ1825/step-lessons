package by.step.basic;

public class HelloThreadTest2 extends Thread{
    public static void main(String[] args) throws InterruptedException {
        HelloThread helloThread = new HelloThread();
        helloThread.setDaemon(true);
        helloThread.start();

//        Thread.sleep(100L);
        System.out.println(Thread.currentThread().getName());

    }
}
