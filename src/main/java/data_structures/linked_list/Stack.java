package data_structures.linked_list;

public class Stack<T> {

    private final java.util.LinkedList<T> list = new java.util.LinkedList<>();

    public int size() {
        return list.size();
    }

    public T pop() {
        if (!list.isEmpty())
            return list.removeFirst();
        return null;
    }

    public void push(T value) {
        list.addFirst(value);
    }

    public T peek() {
        if (!list.isEmpty())
            return list.getFirst();
        return null;
    }
}