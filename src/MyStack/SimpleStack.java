package MyStack;


import com.sun.tools.javac.util.GraphUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleStack<E> implements MyStack<E> {
    private E[] objArray;
    private int stackSize;


    public SimpleStack(E[]e){
        objArray = e;
        stackSize = e.length;
    }



    //public interface methods - push, pop, top, empty & clear
    public void push(E e) {
        if (stackSize < objArray.length) {
            objArray[stackSize] = e;
            stackSize++;
        }
    }

    public E pop() {
        if (stackSize != 0) {
            stackSize--;
        }
        return (objArray[stackSize]);
    }


    @Override
    public void remove(int index) {
            E[] temp = objArray.clone();
            objArray = Arrays.copyOf(objArray, objArray.length-1);
            System.arraycopy(temp, 0, objArray, 0, index);
            int amountElementAfterIndex = temp.length - index - 1;
            System.arraycopy(temp, index + 1, objArray, index, amountElementAfterIndex);
    }

    public void clear() {
        stackSize = 0;
    }

    @Override
    public int size() {
        return stackSize;
    }

    @Override
    public E peek() {
        return objArray[stackSize];
    }

}
