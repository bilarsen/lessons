import java.lang.reflect.Array;
import java.util.Arrays;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        // array = (T[]) Array.newInstance(this.clazz, new_capacity);
        // ваш код
        if (new_capacity <= count) {
           return;
        } else if (new_capacity < 16) {
            capacity = 16;
        } else {
            capacity = new_capacity;
        }

        if (array == null) {
            array = (T[]) Array.newInstance(clazz, capacity);
        } else {
            array = Arrays.copyOf(array, capacity);
        }
    }

    public T getItem(int index) {
        // ваш код
        checkIndex(index);
        return array[index];
    }

    public void append(T itm) {
        // ваш код
        grow();
        array[count++] = itm;
    }

    public void insert(T itm, int index) {
        // ваш код
        if (index < 0 || index > count) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        grow();
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = itm;
        count++;
    }

    public void remove(int index) {
        // ваш код
        checkIndex(index);
        int moveCount = count - index - 1;
        if (moveCount > 0) {
            System.arraycopy(array, index + 1, array, index, moveCount);
        }
        array[--count] = null;
        shrink();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    private void grow() {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
    }

    private void shrink() {
        if (count < capacity / 2) {
            makeArray((int) (capacity / 1.5));
        }
    }
}