package MyLinkedLIst;

public interface Linked<E> {
    void addLast(E e);
    void addFist(E e);
    int size();
    E getElementByIndex(int counter);
    void remove(int index);
    int  clear();
}
