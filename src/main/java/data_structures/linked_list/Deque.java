package data_structures.linked_list;

import java.util.LinkedList;

public class Deque<T> {

    private final LinkedList<T> linkedList = new LinkedList<>();

    public void addFront(T item) {
        linkedList.addFirst(item);
    }

    public void addTail(T item) {
        linkedList.addLast(item);
    }

    public T removeFront() {
        if (!linkedList.isEmpty()) {
            return linkedList.removeFirst();
        }
        return null;
    }

    public T removeTail() {
        if (!linkedList.isEmpty()) {
            return linkedList.removeLast();
        }
        return null;
    }

    public int size() {
        return linkedList.size();
    }
}