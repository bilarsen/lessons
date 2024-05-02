package data_structures.linked_list;

import lombok.Getter;

import java.util.*;


@Getter
public class OrderedList<T> {

    private Node<T> head;

    private Node<T> tail;

    private boolean ascending;

    private int size;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        ascending = asc;
        size = 0;
    }

    public int compare(T v1, T v2) {
        int result;
        switch (v1) {
            case Number number -> result = Double.compare(number.doubleValue(), ((Number) v2).doubleValue());
            case String string -> result = string.trim().compareTo(((String) v2).trim());
            case null, default -> {
                return Integer.MIN_VALUE;
            }
        }
        return Integer.compare(result, 0);
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (size == 0) {
            head = node;
            tail = node;
        } else if (ascending) {
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
        if (size == 0)
            return null;
        return ascending ? findAscending(val) : findDescending(val);
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
        ascending = asc;
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
        return size;
    }

    List<Node<T>> getAll() {
        List<Node<T>> r = new ArrayList<>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    @Getter
    public static class Node<T> {

        private final T value;

        private Node<T> prev;

        private Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }

}