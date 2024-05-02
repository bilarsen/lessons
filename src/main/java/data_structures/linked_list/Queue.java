package data_structures.linked_list;

public class Queue<T> {

    private final java.util.Stack<T> incoming = new java.util.Stack<>();

    private final java.util.Stack<T> outgoing = new java.util.Stack<>();

    public void enqueue(T item) {
        incoming.push(item);
    }

    public T dequeue() {
        if (!outgoing.isEmpty()) {
            return outgoing.pop();
        } else {
            if (incoming.isEmpty()) {
                return null;
            } else {
                while (incoming.size() > 1) {
                    outgoing.push(incoming.pop());
                }
                return incoming.pop();
            }
        }
    }

    public int size() {
        return incoming.size() + outgoing.size();
    }

    public void rotate(int positions) {
        if (outgoing.isEmpty() && incoming.isEmpty())
            return;
        int shift = positions % size();
        shift = shift < 0 ? shift + size() : shift;
        if (incoming.isEmpty() || shift < outgoing.size()) {
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