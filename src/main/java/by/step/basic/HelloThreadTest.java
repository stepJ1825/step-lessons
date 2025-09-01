package by.step.basic;

public class HelloThreadTest extends Thread{
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            HelloThread helloThread = new HelloThread();
//            helloThread.setPriority(i);
//            System.out.println("helloThread.isAlive(): " + helloThread.isAlive());
            helloThread.start();
            System.out.println("helloThread.isAlive() after start: " + helloThread.isAlive());
//            helloThread.sleep(100L);
            System.out.println("helloThread.isAlive() after sleep: " + helloThread.isAlive());

            helloThread.interrupt();
            helloThread.stop();

            helloThread.sleep(1L);


//            System.out.println(Thread.currentThread().getName());
        }

    }
}
