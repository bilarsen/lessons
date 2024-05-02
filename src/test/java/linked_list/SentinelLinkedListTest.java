package linked_list;

import data_structures.linked_list.SentinelLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SentinelLinkedListTest {

    private SentinelLinkedList linkedList;

    private int[] expected;

    @BeforeEach
    public void setUp() {
        linkedList = new SentinelLinkedList();
        linkedList.addInTail(new SentinelLinkedList.Node(1));
        linkedList.addInTail(new SentinelLinkedList.Node(2));
        linkedList.addInTail(new SentinelLinkedList.Node(3));
        linkedList.addInTail(new SentinelLinkedList.Node(4));
        linkedList.addInTail(new SentinelLinkedList.Node(5));
        linkedList.addInTail(new SentinelLinkedList.Node(6));
        linkedList.addInTail(new SentinelLinkedList.Node(1));
        linkedList.addInTail(new SentinelLinkedList.Node(2));
    }

    @Test
    @DisplayName("finding a node")
    void testFind() {
        SentinelLinkedList.Node node = linkedList.find(1);
        assertEquals(1, node.getValue());
        assertNotNull(node.getPrev());
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertNull(node.getPrev().getPrev());

        SentinelLinkedList list = new SentinelLinkedList();
        node = list.find(1);
        assertNull(node);
    }

    @Test
    @DisplayName("removing single node")
    void testRemoveSingleNode() {
        expected = new int[]{1};

        SentinelLinkedList list = new SentinelLinkedList();
        list.addInTail(new SentinelLinkedList.Node(1));
        list.addInTail(new SentinelLinkedList.Node(2));
        list.remove(2);

        assertArrayEquals(expected, list.toArray());


        expected = new int[0];
        list.remove(1);

        assertFalse(list.remove(1));
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    @DisplayName("removing middle node")
    void testRemoveMiddleNode() {
        expected = new int[]{1, 3, 4, 5, 6, 1, 2};

        linkedList.remove(2);

        assertArrayEquals(expected, linkedList.toArray());
        SentinelLinkedList.Node node = linkedList.find(2);
        assertEquals(Integer.MIN_VALUE, node.getNext().getValue());
        assertNull(node.getNext().getNext());
    }

    @Test
    @DisplayName("removing first node")
    void testRemoveFirstNode() {
        expected = new int[]{2, 3, 4, 5, 6, 1, 2};

        linkedList.remove(1);

        assertArrayEquals(expected, linkedList.toArray());
        SentinelLinkedList.Node node = linkedList.find(2);
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertNull(node.getPrev().getPrev());
    }

    @Test
    @DisplayName("removing tail node")
    void testRemoveTailNode() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 1, 2};

        linkedList.addInTail(new SentinelLinkedList.Node(7));
        SentinelLinkedList.Node node = linkedList.find(7);
        assertEquals(Integer.MIN_VALUE, node.getNext().getValue());
        assertNull(node.getNext().getNext());
        linkedList.remove(7);

        assertArrayEquals(expected, linkedList.toArray());
    }

    @Test
    @DisplayName("removing all")
    void testRemoveAll() {
        expected = new int[]{1, 3, 4, 5, 6, 1};

        linkedList.removeAll(2);

        assertArrayEquals(expected, linkedList.toArray());
        SentinelLinkedList.Node node = linkedList.find(1);
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertNull(node.getPrev().getPrev());

        expected = new int[]{3, 4, 5, 6};
        linkedList.removeAll(1);
        node = linkedList.find(3);
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertNull(node.getPrev().getPrev());
        node = linkedList.find(6);
        assertEquals(Integer.MIN_VALUE, node.getNext().getValue());
        assertNull(node.getNext().getNext());
        assertArrayEquals(expected, linkedList.toArray());
    }

    @Test
    @DisplayName("clearing a list")
    void testClear() {
        expected = new int[0];

        linkedList.clear();

        assertArrayEquals(expected, linkedList.toArray());
    }

    @Test
    @DisplayName("finding all")
    void testFindAll() {
        expected = new int[]{1, 1};

        List<SentinelLinkedList.Node> list = linkedList.findAll(1);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.get(i).getValue();
        }

        assertArrayEquals(expected, actual);

        list = linkedList.findAll(0);
        assertTrue(list.isEmpty());

        SentinelLinkedList test = new SentinelLinkedList();
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
        linkedList.insertAfter(new SentinelLinkedList.Node(6), new SentinelLinkedList.Node(999));
        assertArrayEquals(expected, linkedList.toArray());


        linkedList.removeAll(1);
        linkedList.removeAll(2);

        expected = new int[]{3, 4, 5, 6, 999, 777};
        SentinelLinkedList.Node node = new SentinelLinkedList.Node(777);
        linkedList.insertAfter(new SentinelLinkedList.Node(999), node);
        assertArrayEquals(expected, linkedList.toArray());
        assertEquals(999, node.getPrev().getValue());
        assertEquals(Integer.MIN_VALUE, node.getNext().getValue());
        assertNull(node.getNext().getNext());
        node = linkedList.find(3);
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertNull(node.getPrev().getPrev());
    }

    @Test
    @DisplayName("inserting after null")
    void testInsertAfterNull() {
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 1, 2};
        linkedList.insertAfter(null, new SentinelLinkedList.Node(0));
        assertArrayEquals(expected, linkedList.toArray());
        SentinelLinkedList.Node node = linkedList.find(0);
        assertEquals(1, node.getNext().getValue());
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertNull(node.getPrev().getPrev());
        assertEquals(node, node.getNext().getPrev());

        expected = new int[]{777};
        linkedList.clear();
        node = new SentinelLinkedList.Node(777);
        linkedList.insertAfter(null, node);
        assertArrayEquals(expected, linkedList.toArray());
        assertEquals(1, linkedList.count());
        assertEquals(Integer.MIN_VALUE, node.getPrev().getValue());
        assertEquals(Integer.MIN_VALUE, node.getNext().getValue());
        assertNull(node.getPrev().getPrev());
        assertNull(node.getNext().getNext());
    }
}
