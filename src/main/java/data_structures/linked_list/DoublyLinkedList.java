package data_structures.linked_list;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DoublyLinkedList {

    private Node head;
    private Node tail;

    public void addInTail(Node item) {
        if (head == null) {
            this.head = item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = item;
            item.prev = tail;
        }
        this.tail = item;
    }

    public Node find(int value) {
        for (Node node = head; node != null; node = node.next) {
            if (node.value == value)
                return node;
        }
        return null;
    }

    public List<Node> findAll(int value) {
        List<Node> nodes = new ArrayList<>();
        for (Node node = head; node != null; node = node.next) {
            if (node.value == value)
                nodes.add(node);
        }
        return nodes;
    }

    public boolean remove(int value) {
        Node node = find(value);
        if (node == null) return false;
        removeNode(node);
        return true;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
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

    public void removeAll(int value) {
        for (Node node = head; node != null; ) {
            if (node.value == value) {
                Node temp = node;
                node = node.next;
                removeNode(temp);
            } else {
                node = node.next;
            }
        }
    }

    public void clear() {
        Node node = head;
        while (node != null) {
            Node next = node.next;
            node.next = null;
            node.prev = null;
            node = next;
        }
        head = null;
        tail = null;
    }

    public int count() {
        int size = 0;
        for (Node node = head; node != null; node = node.next)
            size++;
        return size;
    }

    public void insertAfter(Node nodeAfter, Node nodeToInsert) {
        if (nodeAfter == null) {
            if (count() == 0) {
                addInTail(nodeToInsert);
            } else {
                nodeToInsert.next = head;
                head.prev = nodeToInsert;
                head = nodeToInsert;
            }
        } else {
            Node node = find(nodeAfter.value);
            if (node != null) {
                if (node == tail) {
                    addInTail(nodeToInsert);
                } else {
                    Node next = node.next;
                    node.next = nodeToInsert;
                    nodeToInsert.next = next;
                    nodeToInsert.prev = node;
                    next.prev = nodeToInsert;
                }
            }
        }
    }

    @Getter
    public static class Node {

        private final int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }
}
