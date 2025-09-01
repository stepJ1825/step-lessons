package by.step.basic;

public class HelloThread extends Thread {

    public HelloThread(String name) {
        super(name);
    }

    public HelloThread() {
    }

    @Override
    public void run() {
//        Thread.currentThread().setName("My Thread");
        int x = 2 + 2;
        System.out.println("Thread class: " + Thread.currentThread().getName());
    }
}
