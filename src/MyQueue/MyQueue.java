package MyQueue;

public interface MyQueue<E> {
    void add(E value);
    void remove(int index);
    void clear();
    int size();
    E peek();
    E poll();
}
