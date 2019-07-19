package MyQueue;


import java.util.Arrays;

public class SimpleQueue<E> implements MyQueue<E> {

    E[] massQueue;
    int size;

    public SimpleQueue(E[] e) {
        massQueue = e;
        size = e.length;
    }


    @Override
    public void add(E value) {
        if (size < massQueue.length) {
            massQueue[size] = value;
            size++;
        } else {
            System.out.println("Queue is full");
        }
    }

    @Override
    public void remove(int index) {
            E[] temp = massQueue.clone();
            massQueue = Arrays.copyOf(massQueue,massQueue.length- 1);
            System.arraycopy(temp, 0, massQueue, 0, index);
            int amountElementAfterIndex = temp.length - index - 1;
            System.arraycopy(temp, index + 1, massQueue, index, amountElementAfterIndex);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E peek() {
        return massQueue[0];
    }

    @Override
    public E poll() {
        E buff = massQueue[0];
        E[] temp = massQueue.clone();
        size--;
        System.arraycopy(temp, 1, massQueue, 0, size);
        return buff;
    }
}
