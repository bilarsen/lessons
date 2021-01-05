import java.util.ArrayList;

public class LinkedListSentinel {

    private Node.SentinelNode head;

    private Node.SentinelNode tail;

    public LinkedListSentinel() {
        head = new Node.SentinelNode();
        tail = new Node.SentinelNode();
        head.next = tail;
        tail.prev = head;
    }

    public void addInTail(Node _item) {
        Node prev = tail.prev;
        prev.next = _item;
        _item.prev = prev;
        tail.prev = _item;
        _item.next = tail;
    }

    public Node find(int _value) {
        // здесь будет ваш код поиска
        for (Node node = head.next; node != tail; node = node.next) {
            if (node.value == _value) {
                return node;
            }
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        // здесь будет ваш код поиска всех узлов по заданному значению
        for (Node node = head.next; node != tail; node = node.next) {
            if (node.value == _value) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public boolean remove(int _value) {
        // здесь будет ваш код удаления одного узла по заданному значению
        Node node = find(_value);
        if (node == null) {
            return false;
        }
        removeNode(node);
        return true; // если узел был удалён
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        node.next = null;
        node.prev = null;
    }

    public void removeAll(int _value) {
        // здесь будет ваш код удаления всех узлов по заданному значению
        for (Node node = head.next; node != tail;) {
            if (node.value == _value) {
                Node temp = node;
                node = node.next;
                removeNode(temp);
            } else {
                node = node.next;
            }
        }
    }

    public void clear() {
        // здесь будет ваш код очистки всего списка
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
        for (Node node = head.next; node != tail; node = node.next) {
            size++;
        }
        return size; // здесь будет ваш код подсчёта количества элементов в списке
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // здесь будет ваш код вставки узла после заданного узла
        // если _nodeAfter = null
        // добавьте новый элемент первым в списке
        if (_nodeAfter == null) {
            Node first = head.next;
            first.prev = _nodeToInsert;
            _nodeToInsert.next = first;
            _nodeToInsert.prev = head;
            head.next = _nodeToInsert;
        } else {
            Node node = find(_nodeAfter.value);
            if (node != null) {
                Node next = node.next;
                next.prev = _nodeToInsert;
                node.next = _nodeToInsert;
                _nodeToInsert.prev = node;
                _nodeToInsert.next = next;
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
}

class Node {

    public int value;

    public Node next;

    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }

    public static class SentinelNode extends Node {

        public SentinelNode() {
            super(Integer.MIN_VALUE);
        }
    }
}