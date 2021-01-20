import java.util.*;

public class Queue<T> {

    private final Stack<T> incoming;

    private final Stack<T> outgoing;

    public Queue() {
        // инициализация внутреннего хранилища очереди
        incoming = new Stack<>();
        outgoing = new Stack<>();
    }

    public void enqueue(T item) {
        // вставка в хвост
        incoming.push(item);
    }

    public T dequeue() {
        // выдача из головы
        if (!outgoing.isEmpty()) {
            return outgoing.pop();
        } else {
            if (incoming.isEmpty()) {
                return null; // null если очередь пустая
            } else {
                while (incoming.size() > 1) {
                    outgoing.push(incoming.pop());
                }
                return incoming.pop();
            }
        }
    }

    public int size() {
        return incoming.size() + outgoing.size(); // размер очереди
    }

    public void rotate(int positions) {
        if (outgoing.isEmpty() && incoming.isEmpty()) {
            return;
        }
        int shift = positions % size();
        shift = shift < 0 ? shift + size() : shift;
        if (incoming.isEmpty() || (!incoming.isEmpty() && shift < outgoing.size())) {
            int keepCount = outgoing.size() - shift;
            while (outgoing.size() > keepCount) {
                incoming.push(outgoing.pop());
            }
        } else {
            if (shift == outgoing.size()) {
                shift = 0;
            } else {
                shift %= incoming.size();
            }
            while (incoming.size() > shift) {
                outgoing.push(incoming.pop());
            }
        }
    }
}