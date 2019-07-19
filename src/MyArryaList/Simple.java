package MyArryaList;

public interface Simple<E> extends  Iterable<E>{
    boolean add(E e);
    void remove(int index);
    void clear();
    E get(int index);
    int size();
}
