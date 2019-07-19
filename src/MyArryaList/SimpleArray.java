package MyArryaList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class SimpleArray<E> implements Simple<E> {

    public static void main(String[] args) {
        Integer[] nine = {4, 5, 2};
        SimpleArray<Integer> newInt = new SimpleArray<>(nine);
        newInt.add(45);
        newInt.add(11);

        newInt.remove(0);

        for (Integer integer : newInt) {
            System.out.println(integer);
        }

    }

    private E[] values;

    public SimpleArray(E[] e) {
        values = e;
    }

    @Override
    public boolean add(E e) {
        values = Arrays.copyOf(values, values.length + 1);
        values[values.length - 1] = e;

        return false;
    }

    @Override
    public void remove(int index) {
        E[] temp = values.clone();
        values = Arrays.copyOf(values, values.length - 1);
        System.arraycopy(temp, 0, values, 0, index);
        int amountElementAfterIndex = temp.length - index - 1;
        System.arraycopy(temp, index + 1, values, index, amountElementAfterIndex);

    }

    @Override
    public void clear() {
        for (int i = 0; i <= values.length; i++) {
            remove(i);
        }
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }
}
