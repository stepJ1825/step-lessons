package by.step.basic;

import java.util.concurrent.locks.Lock;

public class HelloThreadTest3 extends Thread{
    public static void main(String[] args) throws InterruptedException {
        HelloThread helloThread1 = new HelloThread();
        HelloThread helloThread2 = new HelloThread();
        HelloThread helloThread3 = new HelloThread();
        helloThread1.start();
        helloThread2.start();
        helloThread3.start();
        Thread.yield();

//        Thread.sleep(100L);
        System.out.println(Thread.currentThread().getName());

    }
}
