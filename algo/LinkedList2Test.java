import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LinkedList2Test {

    private LinkedList2 linkedList;

    private int[] expected;

    @Before
    public void setUp() {
        linkedList = new LinkedList2();
        linkedList.addInTail(new Node(1));
        linkedList.addInTail(new Node(2));
        linkedList.addInTail(new Node(3));
        linkedList.addInTail(new Node(4));
        linkedList.addInTail(new Node(5));
        linkedList.addInTail(new Node(6));
        linkedList.addInTail(new Node(1));
        linkedList.addInTail(new Node(2));
    }

    @Test
    public void testFind() {
        Node node = linkedList.find(1);
        Assert.assertEquals(1, node.value);
        Assert.assertNotEquals(node, linkedList.tail.prev);
        Assert.assertEquals(node, linkedList.head);

        LinkedList2 list = new LinkedList2();
        node = list.find(1);
        Assert.assertNull(node);
    }

    @Test
    public void testRemoveSingleNode() {
        expected = new int[] { 1 };

        LinkedList2 list = new LinkedList2();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.remove(2);

        Assert.assertArrayEquals(expected, toArray(list));
        Assert.assertEquals(list.head, list.tail);

        expected = new int[0];
        list.remove(1);

        Assert.assertFalse(list.remove(1));
        Assert.assertNull(list.head);
        Assert.assertNull(list.tail);
        Assert.assertArrayEquals(expected, toArray(list));
    }

    @Test
    public void testRemoveMiddleNode() {
        expected = new int[] { 1, 3, 4, 5, 6, 1, 2 };

        linkedList.remove(2);

        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(1, linkedList.head.value);
        Assert.assertEquals(2, linkedList.tail.value);
    }

    @Test
    public void testRemoveHeadNode() {
        expected = new int[] { 2, 3, 4, 5, 6, 1, 2 };

        linkedList.remove(1);

        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(2, linkedList.head.value);
        Assert.assertEquals(2, linkedList.tail.value);
        Assert.assertNotEquals(linkedList.head, linkedList.tail);
        Assert.assertNull(linkedList.head.prev);
    }

    @Test
    public void testRemoveTailNode() {
        expected = new int[] { 1, 2, 3, 4, 5, 6, 1, 2 };

        linkedList.addInTail(new Node(7));
        Assert.assertEquals(7, linkedList.tail.value);
        linkedList.remove(7);

        Assert.assertNull(linkedList.tail.next);
        Assert.assertEquals(2, linkedList.tail.value);
        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testRemoveAll() {
        expected = new int[] { 1, 3, 4, 5, 6, 1 };

        linkedList.removeAll(2);

        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertNotEquals(linkedList.head, linkedList.tail);

        expected = new int[] { 3, 4, 5, 6 };
        linkedList.removeAll(1);
        Assert.assertEquals(3, linkedList.head.value);
        Assert.assertEquals(6, linkedList.tail.value);
        Assert.assertNull(linkedList.head.prev);
        Assert.assertNull(linkedList.tail.next);
        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testClear() {
        expected = new int[0];

        linkedList.clear();

        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testFindAll() {
        expected = new int[] { 1, 1 };

        ArrayList<Node> list = linkedList.findAll(1);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.get(i).value;
        }

        Assert.assertArrayEquals(expected, actual);

        list = linkedList.findAll(0);
        Assert.assertTrue(list.isEmpty());

        LinkedList2 test = new LinkedList2();
        list = test.findAll(1);
        Assert.assertTrue(list.isEmpty());

        linkedList.clear();
        list = linkedList.findAll(1);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testCount() {
        Assert.assertEquals(8, linkedList.count());

        linkedList.clear();
        Assert.assertEquals(0, linkedList.count());
    }

    @Test
    public void testInsertAfter() {
        expected = new int[] { 1, 2, 3, 4, 5, 6, 999, 1, 2 };
        linkedList.insertAfter(new Node(6), new Node(999));
        Assert.assertArrayEquals(expected, toArray(linkedList));

        linkedList.removeAll(1);
        linkedList.removeAll(2);

        expected = new int[] { 3, 4, 5, 6, 999, 777 };
        linkedList.insertAfter(new Node(999), new Node(777));
        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(777, linkedList.tail.value);
        Assert.assertEquals(3, linkedList.head.value);
        Assert.assertNull(linkedList.head.prev);
        Assert.assertNull(linkedList.tail.next);
    }

    @Test
    public void testInsertAfterNull() {
        expected = new int[] { 0, 1, 2, 3, 4, 5, 6, 1, 2 };
        linkedList.insertAfter(null, new Node(0));
        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(0, linkedList.head.value);
        Assert.assertNull(linkedList.head.prev);
        Assert.assertEquals(1, linkedList.head.next.value);
        Assert.assertEquals(linkedList.head, linkedList.head.next.prev);

        expected = new int[] { 777 };
        linkedList.clear();
        linkedList.insertAfter(null, new Node(777));
        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(777, linkedList.head.value);
        Assert.assertEquals(linkedList.head, linkedList.tail);
    }

    private int[] toArray(LinkedList2 linkedList) {
        int[] array = new int[linkedList.count()];
        int i = 0;
        for (Node node = linkedList.head; node != null; node = node.next) {
            array[i++] = node.value;
        }
        return array;
    }
}
