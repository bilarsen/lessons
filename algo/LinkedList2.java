import java.util.*;

public class LinkedList2 {

    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        // здесь будет ваш код поиска
        for (Node node = head; node != null; node = node.next) {
            if (node.value == _value) {
                return node;
            }
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        // здесь будет ваш код поиска всех узлов по заданному значению
        for (Node node = head; node != null; node = node.next) {
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

    public void removeAll(int _value) {
        // здесь будет ваш код удаления всех узлов по заданному значению
        for (Node node = head; node != null;) {
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
        for (Node node = head; node != null; node = node.next) {
            size++;
        }
        return size; // здесь будет ваш код подсчёта количества элементов в списке
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // здесь будет ваш код вставки узла после заданного узла
        // если _nodeAfter = null
        // добавьте новый элемент первым в списке
        if (_nodeAfter == null) {
            if (count() == 0) {
                addInTail(_nodeToInsert);
            } else {
                _nodeToInsert.next = head;
                head.prev = _nodeToInsert;
                head = _nodeToInsert;
            }
        } else {
            Node node = find(_nodeAfter.value);
            if (node != null) {
                if (node == tail) {
                    addInTail(_nodeToInsert);
                } else {
                    Node next = node.next;
                    node.next = _nodeToInsert;
                    _nodeToInsert.next = next;
                    _nodeToInsert.prev = node;
                    next.prev = _nodeToInsert;
                }
            }
        }
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
}