package data_structures.linked_list;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LinkedList {

    private Node head;

    private Node tail;

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public List<Node> findAll(int value) {
        List<Node> nodes = new ArrayList<>();
        for (Node node = head; node != null; node = node.next) {
            if (node.value == value) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public boolean remove(int value) {
        Node node = find(value);
        if (node == null)
            return false;
        else {
            if (node == head) {
                head = head.next;
                if (head == null) {
                    tail = null;
                }
            } else {
                Node current = head;
                while (current.next != node) {
                    current = current.next;
                }
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current;
                }
            }
        }
        return true;
    }

    public void removeAll(int value) {
        for (Node node = find(value); node != null; node = find(value))
            remove(value);
    }

    public void clear() {
        Node current = head;
        while (current != null) {
            Node temp = current.next;
            current.next = null;
            current = temp;
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
                head = nodeToInsert;
            }
        } else {
            Node node = find(nodeAfter.value);
            if (node != null) {
                if (node == tail) {
                    addInTail(nodeToInsert);
                } else {
                    Node temp = node.next;
                    node.next = nodeToInsert;
                    nodeToInsert.next = temp;
                }
            }
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Node {

        private final int value;
        private Node next;
    }

}

