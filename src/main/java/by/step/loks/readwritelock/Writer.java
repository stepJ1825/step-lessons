package by.step.loks.readwritelock;

import static java.lang.Thread.sleep;

public class Writer implements Runnable {

    SynchronizedHashMapWithRWLock object;

    public Writer(SynchronizedHashMapWithRWLock object) {
        this.object = object;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                object.put("key" + i, "value" + i);
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
