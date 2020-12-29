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
    public void testRemoveMiddleNode() {
        expected = new int[]{1, 3, 4, 5, 6, 1, 2};

        linkedList.remove(2);

        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testRemoveHeadNode() {
        expected = new int[]{2, 3, 4, 5, 6, 1, 2};

        linkedList.remove(1);

        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testRemoveTailNode() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 1, 2};

        linkedList.addInTail(new Node(7));
        linkedList.remove(7);

        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testRemoveAll() {
        expected = new int[]{1, 3, 4, 5, 6, 1};

        linkedList.removeAll(2);

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
        expected = new int[]{1, 1};

        ArrayList<Node> list = linkedList.findAll(1);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.get(i).value;
        }

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testCount() {
        Assert.assertEquals(8, linkedList.count());
    }

    @Test
    public void testInsertAfter() {
        expected = new int[]{1, 2, 3, 4, 5, 6, 999, 1, 2};
        linkedList.insertAfter(new Node(6), new Node(999));
        Assert.assertArrayEquals(expected, toArray(linkedList));
    }

    @Test
    public void testInsertAfterNull() {
        expected = new int[]{0, 1, 2, 3, 4, 5, 6, 1, 2};
        linkedList.insertAfter(null, new Node(0));
        Assert.assertArrayEquals(expected, toArray(linkedList));
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