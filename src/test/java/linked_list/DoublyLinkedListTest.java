package linked_list;

import data_structures.linked_list.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoublyLinkedListTest {

    private DoublyLinkedList linkedList;

    private int[] expected;

    @BeforeEach
    public void setUp() {
        linkedList = new DoublyLinkedList();
        linkedList.addInTail(new DoublyLinkedList.Node(1));
        linkedList.addInTail(new DoublyLinkedList.Node(2));
        linkedList.addInTail(new DoublyLinkedList.Node(3));
        linkedList.addInTail(new DoublyLinkedList.Node(4));
        linkedList.addInTail(new DoublyLinkedList.Node(5));
        linkedList.addInTail(new DoublyLinkedList.Node(6));
        linkedList.addInTail(new DoublyLinkedList.Node(1));
        linkedList.addInTail(new DoublyLinkedList.Node(2));
    }

    @Test
    @DisplayName("finding a node")
    void testFind() {
        DoublyLinkedList.Node node = linkedList.find(1);
        assertEquals(1, node.getValue());
        assertNotEquals(node, linkedList.getTail().getPrev());
        assertEquals(node, linkedList.getHead());

        DoublyLinkedList list = new DoublyLinkedList();
        node = list.find(1);
        assertNull(node);
    }

    @Test
    @DisplayName("removing single node")
    void testRemoveSingleNode() {
        expected = new int[]{1};

        DoublyLinkedList list = new DoublyLinkedList();
        list.addInTail(new DoublyLinkedList.Node(1));
        list.addInTail(new DoublyLinkedList.Node(2));
        list.remove(2);

        assertArrayEquals(expected, toArray(list));
        assertEquals(list.getHead(), list.getTail());

        expected = new int[0];
        list.remove(1);

        assertFalse(list.remove(1));
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertArrayEquals(expected, toArray(list));
    }

    @Test
    @DisplayName("removing middle node")
    void testRemoveMiddleNode() {
        expected = new int[]{1, 3, 4, 5, 6, 1, 2};

        linkedList.remove(2);

        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(1, linkedList.getHead().getValue());
        assertEquals(2, linkedList.getTail().getValue());
    }

    @Test
    @DisplayName("removing head node")
    void testRemoveHeadNode() {
        expected = new int[]{2, 3, 4, 5, 6, 1, 2};

        linkedList.remove(1);

        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(2, linkedList.getHead().getValue());
        assertEquals(2, linkedList.getTail().getValue());
        assertNotEquals(linkedList.getHead(), linkedList.getTail());
        assertNull(linkedList.getHead().getPrev());
    }

    @Test
    @DisplayName("removing tail node")
    void testRemoveTailNode() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 1, 2};

        linkedList.addInTail(new DoublyLinkedList.Node(7));
        assertEquals(7, linkedList.getTail().getValue());
        linkedList.remove(7);

        assertNull(linkedList.getTail().getNext());
        assertEquals(2, linkedList.getTail().getValue());
        assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    @DisplayName("removing all")
    void testRemoveAll() {
        expected = new int[]{1, 3, 4, 5, 6, 1};

        linkedList.removeAll(2);

        assertArrayEquals(expected, toArray(linkedList));
        assertNotEquals(linkedList.getHead(), linkedList.getTail());

        expected = new int[]{3, 4, 5, 6};
        linkedList.removeAll(1);
        assertEquals(3, linkedList.getHead().getValue());
        assertEquals(6, linkedList.getTail().getValue());
        assertNull(linkedList.getHead().getPrev());
        assertNull(linkedList.getTail().getNext());
        assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    @DisplayName("clearing nodes")
    void testClear() {
        expected = new int[0];

        linkedList.clear();

        assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    @DisplayName("finding all")
    void testFindAll() {
        expected = new int[]{1, 1};

        List<DoublyLinkedList.Node> list = linkedList.findAll(1);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.get(i).getValue();
        }

        assertArrayEquals(expected, actual);

        list = linkedList.findAll(0);
        assertTrue(list.isEmpty());

        DoublyLinkedList test = new DoublyLinkedList();
        list = test.findAll(1);
        assertTrue(list.isEmpty());

        linkedList.clear();
        list = linkedList.findAll(1);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("counting nodes")
    void testCount() {
        assertEquals(8, linkedList.count());

        linkedList.clear();
        assertEquals(0, linkedList.count());
    }

    @Test
    @DisplayName("inserting after")
    void testInsertAfter() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 999, 1, 2};
        linkedList.insertAfter(new DoublyLinkedList.Node(6), new DoublyLinkedList.Node(999));
        assertArrayEquals(expected, toArray(linkedList));

        linkedList.removeAll(1);
        linkedList.removeAll(2);

        expected = new int[]{3, 4, 5, 6, 999, 777};
        linkedList.insertAfter(new DoublyLinkedList.Node(999), new DoublyLinkedList.Node(777));
        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(777, linkedList.getTail().getValue());
        assertEquals(3, linkedList.getHead().getValue());
        assertNull(linkedList.getHead().getPrev());
        assertNull(linkedList.getTail().getNext());
    }

    @Test
    @DisplayName("inserting after null")
    void testInsertAfterNull() {
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 1, 2};
        linkedList.insertAfter(null, new DoublyLinkedList.Node(0));
        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(0, linkedList.getHead().getValue());
        assertNull(linkedList.getHead().getPrev());
        assertEquals(1, linkedList.getHead().getNext().getValue());
        assertEquals(linkedList.getHead(), linkedList.getHead().getNext().getPrev());

        expected = new int[]{777};
        linkedList.clear();
        linkedList.insertAfter(null, new DoublyLinkedList.Node(777));
        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(777, linkedList.getHead().getValue());
        assertEquals(linkedList.getHead(), linkedList.getTail());
    }

    private int[] toArray(DoublyLinkedList linkedList) {
        int[] array = new int[linkedList.count()];
        int i = 0;
        for (DoublyLinkedList.Node node = linkedList.getHead(); node != null; node = node.getNext()) {
            array[i++] = node.getValue();
        }
        return array;
    }
}
