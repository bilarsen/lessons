import java.util.*;

public class Deque<T> {

    private final LinkedList<T> linkedList;

    public Deque() {
        // инициализация внутреннего хранилища
        linkedList = new LinkedList<>();
    }

    public void addFront(T item) {
        // добавление в голову
        linkedList.addFirst(item);
    }

    public void addTail(T item) {
        // добавление в хвост
        linkedList.addLast(item);
    }

    public T removeFront() {
        // удаление из головы
        if (!linkedList.isEmpty()) {
            return linkedList.removeFirst();
        }
        return null;
    }

    public T removeTail() {
        // удаление из хвоста
        if (!linkedList.isEmpty()) {
            return linkedList.removeLast();
        }
        return null;
    }

    public int size() {
        return linkedList.size(); // размер очереди
    }
}