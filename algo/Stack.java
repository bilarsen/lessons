import java.util.*;

public class Stack<T> {

    private LinkedList<T> list;

    public Stack() {
        // инициализация внутреннего хранилища стека
        list = new LinkedList<>();
    }

    public int size() {
        // размер текущего стека
        return list.size();
    }

    public T pop() {
        // ваш код
        if (!list.isEmpty()) {
            return list.removeFirst();
        }
        return null;  // если стек пустой
    }

    public void push(T val) {
        // ваш код
        list.addFirst(val);
    }

    public T peek() {
        // ваш код
        if (!list.isEmpty()) {
            return list.getFirst();
        }
        return null; // если стек пустой
    }
}