package linked_list;

import data_structures.linked_list.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    private LinkedList linkedList;

    private int[] expected;

    @BeforeEach
    public void setUp() {
        linkedList = new LinkedList();
        linkedList.addInTail(new LinkedList.Node(1));
        linkedList.addInTail(new LinkedList.Node(2));
        linkedList.addInTail(new LinkedList.Node(3));
        linkedList.addInTail(new LinkedList.Node(4));
        linkedList.addInTail(new LinkedList.Node(5));
        linkedList.addInTail(new LinkedList.Node(6));
        linkedList.addInTail(new LinkedList.Node(1));
        linkedList.addInTail(new LinkedList.Node(2));
    }

    @Test
    @DisplayName("removing single node")
    void testRemoveSingleNode() {
        expected = new int[]{1};

        LinkedList list = new LinkedList();
        list.addInTail(new LinkedList.Node(1));
        list.addInTail(new LinkedList.Node(2));
        list.remove(2);

        assertArrayEquals(expected, toArray(list));
        assertEquals(list.getHead(), list.getTail());

        expected = new int[0];
        list.remove(1);

        assertFalse(list.remove(1));
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
    }

    @Test
    @DisplayName("removing tail node")
    void testRemoveTailNode() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 1, 2};

        linkedList.addInTail(new LinkedList.Node(7));
        assertEquals(7, linkedList.getTail().getValue());
        linkedList.remove(7);

        assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    @DisplayName("removing all")
    void testRemoveAll() {
        expected = new int[]{1, 3, 4, 5, 6, 1};

        linkedList.removeAll(2);

        assertArrayEquals(expected, toArray(linkedList));
        assertNotEquals(linkedList.getHead(), linkedList.getTail());
    }

    @Test
    @DisplayName("clearing linked list")
    void testClear() {
        expected = new int[0];

        linkedList.clear();

        assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    void testFindAll() {
        expected = new int[]{1, 1};

        List<LinkedList.Node> list = linkedList.findAll(1);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            actual[i] = list.get(i).getValue();

        assertArrayEquals(expected, actual);

        list = linkedList.findAll(0);
        assertTrue(list.isEmpty());

        LinkedList test = new LinkedList();
        list = test.findAll(1);
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
        linkedList.insertAfter(new LinkedList.Node(6), new LinkedList.Node(999));
        assertArrayEquals(expected, toArray(linkedList));

        expected = new int[]{3, 4, 5, 6, 999, 777};
        linkedList.removeAll(1);
        linkedList.removeAll(2);
        linkedList.insertAfter(new LinkedList.Node(999), new LinkedList.Node(777));
        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(777, linkedList.getTail().getValue());
        assertEquals(3, linkedList.getHead().getValue());
    }

    @Test
    @DisplayName("inserting after null")
    void testInsertAfterNull() {
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 1, 2};
        linkedList.insertAfter(null, new LinkedList.Node(0));
        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(0, linkedList.getHead().getValue());

        expected = new int[]{777};
        linkedList.clear();
        linkedList.insertAfter(null, new LinkedList.Node(777));
        assertArrayEquals(expected, toArray(linkedList));
        assertEquals(777, linkedList.getHead().getValue());
        assertEquals(linkedList.getHead(), linkedList.getTail());
    }

    private int[] toArray(LinkedList linkedList) {
        int[] array = new int[linkedList.count()];
        int i = 0;
        for (LinkedList.Node node = linkedList.getHead(); node != null; node = node.getNext()) {
            array[i++] = node.getValue();
        }
        return array;
    }
}
