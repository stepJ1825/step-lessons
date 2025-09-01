package by.step.syncro;

public class CounterThread implements Runnable {
    private final CommonObject res;

    public CounterThread(CommonObject res) {
        this.res = res;
    }

    @Override
    public void run() {
        synchronized (res) {
            res.setCounter(1);
            for (int i = 1; i < 5; i++) {
                System.out.printf("'%s' - %d\n", Thread.currentThread().getName(), res.getCounter());
                res.setCounter(res.getCounter() + 1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("Exception!!!");
                }
            }
        }
    }

    public synchronized void demoSynchronizedMethod() {
    }

    public void demoSynchronizedMethodWithSyncBlock() {
        synchronized (this) {

        }
    }

    public synchronized static void demoSyncStaticMethod() {
    }

    public void demoSyncMethodWithSyncBlock() {
        synchronized (CounterThread.class) {

        }
    }


}
