package by.step.wait_notify;
// класс Производитель

public class Producer implements Runnable {

    private Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
