import java.util.*;

public class LinkedList {
    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

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

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        // здесь будет ваш код поиска всех узлов
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
        } else {
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
        return true; // если узел был удалён
    }

    public void removeAll(int _value) {
        // здесь будет ваш код удаления всех узлов по заданному значению
        for (Node node = find(_value); node != null; node = find(_value)) {
            remove(_value);
        }
    }

    public void clear() {
        // здесь будет ваш код очистки всего списка
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
        for (Node node = head; node != null; node = node.next) {
            size++;
        }
        return size; // здесь будет ваш код подсчёта количества элементов в списке
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // здесь будет ваш код вставки узла после заданного
        // если _nodeAfter = null ,
        // добавьте новый элемент первым в списке
        if (_nodeAfter == null) {
            if (count() == 0) {
                addInTail(_nodeToInsert);
            } else {
                _nodeToInsert.next = head;
                head = _nodeToInsert;
            }
        } else {
            Node node = find(_nodeAfter.value);
            if (node != null) {
                if (node == tail) {
                    addInTail(_nodeToInsert);
                } else {
                    Node temp = node.next;
                    node.next = _nodeToInsert;
                    _nodeToInsert.next = temp;
                }
            }
        }
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}
