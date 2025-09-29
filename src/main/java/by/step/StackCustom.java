package by.step;

import java.util.LinkedList;

/**
 * ЗАДАЧА: Создать кастомную реализацию стека
 * с получением минимального элемента за константное время
 */
public class StackCustom {

    private LinkedList data = new LinkedList();
    private LinkedList minElement = new LinkedList();

    public void push(Object o) {
        if (data.isEmpty()) {
            minElement.push(o);
        } else {
            if (((Comparable) minElement.peek()).compareTo(o) > 0) {
                minElement.push(o);
            }
        }
        data.push(o);
    }

    public Object pop() {
        Object lastElement = data.pop();
        if (lastElement == minElement.peek()) {
            minElement.pop();
        }
        return lastElement;
    }

    public Object peek() {
//        data.get(data.size()-1) //если data это List
        return data.getLast();
    }

    public Object getMin() {
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


