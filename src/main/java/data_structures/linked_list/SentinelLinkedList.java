package data_structures.linked_list;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SentinelLinkedList {

    private final Node.SentinelNode head;

    private final Node.SentinelNode tail;

    public SentinelLinkedList() {
        head = new Node.SentinelNode();
        tail = new Node.SentinelNode();
        head.next = tail;
        tail.prev = head;
    }

    public void addInTail(Node item) {
        Node prev = tail.prev;
        prev.next = item;
        item.prev = prev;
        tail.prev = item;
        item.next = tail;
    }

    public Node find(int value) {
        for (Node node = head.next; node != tail; node = node.next) {
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }

    public List<Node> findAll(int value) {
        List<Node> nodes = new ArrayList<>();
        for (Node node = head.next; node != tail; node = node.next) {
            if (node.value == value) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public boolean remove(int value) {
        Node node = find(value);
        if (node == null) {
            return false;
        }
        removeNode(node);
        return true;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        node.next = null;
        node.prev = null;
    }

    public void removeAll(int value) {
        for (Node node = head.next; node != tail;) {
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
        Node node = head.next;
        while (node != tail) {
            Node next = node.next;
            node.prev = null;
            node.next = null;
            node = next;
        }
        head.next = tail;
        tail.prev = head;
    }

    public int count() {
        int size = 0;
        for (Node node = head.next; node != tail; node = node.next)
            size++;
        return size;
    }

    public void insertAfter(Node nodeAfter, Node nodeToInsert) {
        if (nodeAfter == null) {
            Node first = head.next;
            first.prev = nodeToInsert;
            nodeToInsert.next = first;
            nodeToInsert.prev = head;
            head.next = nodeToInsert;
        } else {
            Node node = find(nodeAfter.value);
            if (node != null) {
                Node next = node.next;
                next.prev = nodeToInsert;
                node.next = nodeToInsert;
                nodeToInsert.prev = node;
                nodeToInsert.next = next;
            }
        }
    }

    public int[] toArray() {
        int[] array = new int[count()];
        int i = 0;
        for (Node node = head.next; node != tail; node = node.next) {
            array[i++] = node.value;
        }
        return array;
    }

    @Getter
    public static class Node {

        private final int value;

        protected Node next;

        protected Node prev;

        public Node(int value) {
            this.value = value;
            next = null;
            prev = null;
        }
        public static class SentinelNode extends Node {

            public SentinelNode() {
                super(Integer.MIN_VALUE);
            }
        }

    }

}
