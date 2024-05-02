package data_structures;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

@Getter
public class DynamicArray<T> {

    private T[] array;

    private int count;

    private int capacity;

    private Class clazz;

    public DynamicArray(Class clz) {
        clazz = clz;
        count = 0;
        makeArray(16);
    }

    public void makeArray(int newCapacity) {
        if (newCapacity <= count)
            return;
        else
            capacity = Math.max(newCapacity, 16);
        if (array == null)
            array = (T[]) Array.newInstance(clazz, capacity);
        else
            array = Arrays.copyOf(array, capacity);
    }

    public T getItem(int index) {
        checkIndex(index);
        return array[index];
    }

    public void append(T itm) {
        grow();
        array[count++] = itm;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > count)
            throw new ArrayIndexOutOfBoundsException(index);
        grow();
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = itm;
        count++;
    }

    public void remove(int index) {
        checkIndex(index);
        int moveCount = count - index - 1;
        if (moveCount > 0)
            System.arraycopy(array, index + 1, array, index, moveCount);
        array[--count] = null;
        shrink();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count)
            throw new ArrayIndexOutOfBoundsException(index);
    }

    private void grow() {
        if (count == capacity)
            makeArray(capacity * 2);
    }

    private void shrink() {
        if (count < capacity / 2)
            makeArray((int) (capacity / 1.5));
    }
}