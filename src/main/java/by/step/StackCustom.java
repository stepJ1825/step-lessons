package by.step;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * ЗАДАЧА: Создать кастомную реализацию стека
 * с получением минимального элемента за константное время
 */
public class StackCustom<T extends Comparable<T>> {

    private final LinkedList<T> data = new LinkedList<>();
    private final LinkedList<T> minElement = new LinkedList<>();

    public void push(T t) {
        if (data.isEmpty()) {
            minElement.push(t);
        } else {
            if (minElement.peek().compareTo(t) > 0) {
                minElement.push(t);
            }
        }
        data.push(t);
    }

    public T pop() {
        T lastElement = null;
        try {
            lastElement = data.pop();
            if (lastElement == minElement.peek()) {
                minElement.pop();
            }
        } catch (NoSuchElementException e) {
            System.err.println("Элемента нету. В хранилище пусто.");
        }

        return lastElement;
    }

    public T peek() {
        return data.peek();
    }

    public T getMin() {
        return minElement.peek();
    }
}
//PUSH
// 4 3 min 3
// 4 3 5 min 3
// 4 3 5 1 min 1
// 4 3 5 1 9 min 1
// 4 3 5 1 9 3 min 1
// 4 3 5 1 9 3 2 min 1
// 4 3 5 1 9 3 2 6 min 1
//POP
// 4 3 5 1 9 3 2 6 min 1
// 4 3 5 1 9 3 2 min 1
// 4 3 5 1 9 3 min 1
// 4 3 5 1 9 min 1
// 4 3 5 1 min 1
// 4 3 5 min 3
// 4 3 min 3
// 4 min 4


