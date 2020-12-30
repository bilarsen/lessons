import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LinkedListTest {

    private LinkedList linkedList;

    private int[] expected;

    @Before
    public void setUp() {
        linkedList = new LinkedList();
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
    public void testRemoveSingleNode() {
        expected = new int[]{1};

        LinkedList list = new LinkedList();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.remove(2);

        Assert.assertArrayEquals(expected, toArray(list));
        Assert.assertEquals(list.head, list.tail);

        expected = new int[0];
        list.remove(1);

        Assert.assertFalse(list.remove(1));
        Assert.assertArrayEquals(expected, toArray(list));
    }

    @Test
    public void testRemoveMiddleNode() {
        expected = new int[]{1, 3, 4, 5, 6, 1, 2};

        linkedList.remove(2);

        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(1, linkedList.head.value);
        Assert.assertEquals(2, linkedList.tail.value);
    }

    @Test
    public void testRemoveHeadNode() {
        expected = new int[]{2, 3, 4, 5, 6, 1, 2};

        linkedList.remove(1);

        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(2, linkedList.head.value);
        Assert.assertEquals(2, linkedList.tail.value);
        Assert.assertNotEquals(linkedList.head, linkedList.tail);
    }

    @Test
    public void testRemoveTailNode() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 1, 2};

        linkedList.addInTail(new Node(7));
        Assert.assertEquals(7, linkedList.tail.value);
        linkedList.remove(7);

        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testRemoveAll() {
        expected = new int[]{1, 3, 4, 5, 6, 1};

        linkedList.removeAll(2);

        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertNotEquals(linkedList.head, linkedList.tail);
    }

    @Test
    public void testClear() {
        expected = new int[0];

        linkedList.clear();

        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testFindAll() {
        expected = new int[]{1, 1};

        ArrayList<Node> list = linkedList.findAll(1);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.get(i).value;
        }

        Assert.assertArrayEquals(expected, actual);

        list = linkedList.findAll(0);
        Assert.assertTrue(list.isEmpty());

        LinkedList test = new LinkedList();
        list = test.findAll(1);
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
        expected = new int[]{1, 2, 3, 4, 5, 6, 999, 1, 2};
        linkedList.insertAfter(new Node(6), new Node(999));
        Assert.assertArrayEquals(expected, toArray(linkedList));

        expected = new int[]{3, 4, 5, 6, 999, 777};
        linkedList.removeAll(1);
        linkedList.removeAll(2);
        linkedList.insertAfter(new Node(999), new Node(777));
        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(777, linkedList.tail.value);
        Assert.assertEquals(3, linkedList.head.value);
    }

    @Test
    public void testInsertAfterNull() {
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 1, 2};
        linkedList.insertAfter(null, new Node(0));
        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(0, linkedList.head.value);

        expected = new int[]{777};
        linkedList.clear();
        linkedList.insertAfter(null, new Node(777));
        Assert.assertArrayEquals(expected, toArray(linkedList));
        Assert.assertEquals(777,linkedList.head.value);
        Assert.assertEquals(linkedList.head, linkedList.tail);
    }

    private int[] toArray(LinkedList linkedList) {
        int[] array = new int[linkedList.count()];
        int i = 0;
        for (Node node = linkedList.head; node != null; node = node.next) {
            array[i++] = node.value;
        }
        return array;
    }
}
