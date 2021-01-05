import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LinkedListSentinelTest {

    private LinkedListSentinel linkedList;

    private int[] expected;

    @Before
    public void setUp() {
        linkedList = new LinkedListSentinel();
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
        Assert.assertNotNull(node.prev);
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertNull(node.prev.prev);

        LinkedListSentinel list = new LinkedListSentinel();
        node = list.find(1);
        Assert.assertNull(node);
    }

    @Test
    public void testRemoveSingleNode() {
        expected = new int[]{1};

        LinkedListSentinel list = new LinkedListSentinel();
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.remove(2);

        Assert.assertArrayEquals(expected, list.toArray());


        expected = new int[0];
        list.remove(1);

        Assert.assertFalse(list.remove(1));
        Assert.assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testRemoveMiddleNode() {
        expected = new int[]{1, 3, 4, 5, 6, 1, 2};

        linkedList.remove(2);

        Assert.assertArrayEquals(expected, linkedList.toArray());
        Node node = linkedList.find(2);
        Assert.assertEquals(Integer.MIN_VALUE, node.next.value);
        Assert.assertNull(node.next.next);
    }

    @Test
    public void testRemoveFirstNode() {
        expected = new int[]{2, 3, 4, 5, 6, 1, 2};

        linkedList.remove(1);

        Assert.assertArrayEquals(expected, linkedList.toArray());
        Node node = linkedList.find(2);
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertNull(node.prev.prev);
    }

    @Test
    public void testRemoveTailNode() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 1, 2};

        linkedList.addInTail(new Node(7));
        Node node = linkedList.find(7);
        Assert.assertEquals(Integer.MIN_VALUE, node.next.value);
        Assert.assertNull(node.next.next);
        linkedList.remove(7);

        Assert.assertArrayEquals(expected, linkedList.toArray());
    }

    @Test
    public void testRemoveAll() {
        expected = new int[]{1, 3, 4, 5, 6, 1};

        linkedList.removeAll(2);

        Assert.assertArrayEquals(expected, linkedList.toArray());
        Node node = linkedList.find(1);
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertNull(node.prev.prev);

        expected = new int[]{3, 4, 5, 6};
        linkedList.removeAll(1);
        node = linkedList.find(3);
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertNull(node.prev.prev);
        node = linkedList.find(6);
        Assert.assertEquals(Integer.MIN_VALUE, node.next.value);
        Assert.assertNull(node.next.next);
        Assert.assertArrayEquals(expected, linkedList.toArray());
    }

    @Test
    public void testClear() {
        expected = new int[0];

        linkedList.clear();

        Assert.assertArrayEquals(expected, linkedList.toArray());
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

        LinkedListSentinel test = new LinkedListSentinel();
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
        expected = new int[]{1, 2, 3, 4, 5, 6, 999, 1, 2};
        linkedList.insertAfter(new Node(6), new Node(999));
        Assert.assertArrayEquals(expected, linkedList.toArray());


        linkedList.removeAll(1);
        linkedList.removeAll(2);

        expected = new int[]{3, 4, 5, 6, 999, 777};
        Node node = new Node(777);
        linkedList.insertAfter(new Node(999), node);
        Assert.assertArrayEquals(expected, linkedList.toArray());
        Assert.assertEquals(999, node.prev.value);
        Assert.assertEquals(Integer.MIN_VALUE, node.next.value);
        Assert.assertNull(node.next.next);
        node = linkedList.find(3);
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertNull(node.prev.prev);
    }

    @Test
    public void testInsertAfterNull() {
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 1, 2};
        linkedList.insertAfter(null, new Node(0));
        Assert.assertArrayEquals(expected, linkedList.toArray());
        Node node = linkedList.find(0);
        Assert.assertEquals(1, node.next.value);
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertNull(node.prev.prev);
        Assert.assertEquals(node, node.next.prev);

        expected = new int[]{777};
        linkedList.clear();
        node = new Node(777);
        linkedList.insertAfter(null, node);
        Assert.assertArrayEquals(expected, linkedList.toArray());
        Assert.assertEquals(1, linkedList.count());
        Assert.assertEquals(Integer.MIN_VALUE, node.prev.value);
        Assert.assertEquals(Integer.MIN_VALUE, node.next.value);
        Assert.assertNull(node.prev.prev);
        Assert.assertNull(node.next.next);
    }
}
