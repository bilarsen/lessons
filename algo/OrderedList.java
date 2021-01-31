import java.util.*;


class Node<T> {

    public T value;

    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {

    public Node<T> head, tail;

    private boolean _ascending;

    private int size;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
        size = 0;
    }

    public int compare(T v1, T v2) {
        int result;
        if (v1 instanceof Number) {
            result = Double.compare(((Number) v1).doubleValue(), ((Number) v2).doubleValue());
        } else if (v1 instanceof String) {
            result = ((String) v1).trim().compareTo(((String) v2).trim());
        } else {
            return Integer.MIN_VALUE;
        }
        return Integer.compare(result, 0);
        // -1 если v1 < v2
        // 0 если v1 == v2
        // +1 если v1 > v2
    }

    public void add(T value) {
        // автоматическая вставка value 
        // в нужную позицию
        Node<T> node = new Node<>(value);
        if (size == 0) {
            head = node;
            tail = node;
        } else if (_ascending) {
            addAscending(node);
        } else {
            addDescending(node);
        }
        size++;
    }

    private void addAscending(Node<T> node) {
        if (compare(head.value, node.value) == 1 || compare(head.value, node.value) == 0) {
            node.next = head;
            head.prev = node;
            head = node;
        } else if (compare(tail.value, node.value) == -1 || compare(tail.value, node.value) == 0) {
            node.prev = tail;
            tail.next = node;
            tail = node;
        } else {
            Node<T> current = head.next;
            while (compare(current.value, node.value) < 0) {
                current = current.next;
            }
            insertBeforeNode(current, node);
        }
    }

    private void addDescending(Node<T> node) {
        if (compare(head.value, node.value) == -1 || compare(head.value, node.value) == 0) {
            node.next = head;
            head.prev = node;
            head = node;
        } else if (compare(tail.value, node.value) == 1 || compare(tail.value, node.value) == 0) {
            node.prev = tail;
            tail.next = node;
            tail = node;
        } else {
            Node<T> current = head.next;
            while (compare(current.value, node.value) > 0) {
                current = current.next;
            }
            insertBeforeNode(current, node);
        }
    }

    private void insertBeforeNode(Node<T> beforeNode, Node<T> insertNode) {
        Node<T> prev = beforeNode.prev;
        prev.next = insertNode;
        beforeNode.prev = insertNode;
        insertNode.prev = prev;
        insertNode.next = beforeNode;
    }

    public Node<T> find(T val) {
        if (size == 0) {
            return null; // здесь будет ваш код
        }
        return _ascending ? findAscending(val) : findDescending(val);
    }

    private Node<T> findAscending(T val) {
        if (compare(tail.value, val) == -1) {
            return null;
        }
        for (Node<T> node = head; node != null; node = node.next) {
            if (compare(node.value, val) == 1) {
                return null;
            } else if (compare(node.value, val) == 0) {
                return node;
            }
        }
        return null;
    }

    private Node<T> findDescending(T val) {
        if (compare(tail.value, val) == 1) {
            return null;
        }
        for (Node<T> node = head; node != null; node = node.next) {
            if (compare(node.value, val) == -1) {
                return null;
            } else if (compare(node.value, val) == 0) {
                return node;
            }
        }
        return null;
    }

    public void delete(T val) {
        // здесь будет ваш код
        Node<T> node = find(val);
        if (node != null) {
            removeNode(node);
            size--;
        }
    }

    private void removeNode(Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
    }

    public void clear(boolean asc) {
        _ascending = asc;
        // здесь будет ваш код
        Node<T> node = head;
        while (node != null) {
            Node<T> next = node.next;
            node.next = null;
            node.prev = null;
            node = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public int count() {
        return size; // здесь будет ваш код подсчёта количества элементов в списке
    }

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного 
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}