import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DequeTest {

    private Deque<Integer> deque;

    private Integer[] expected;

    @Before
    public void setup() {
        deque = new Deque<>();
        deque.addFront(5);
        deque.addFront(4);
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        deque.addTail(6);
        deque.addTail(7);
        deque.addTail(8);
        deque.addTail(9);
        deque.addTail(10);
    }

    @Test
    public void testSize() {
        expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Assert.assertEquals(10, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[0];
        Assert.assertEquals(0, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
    }

    @Test
    public void testAddFront() {
        expected = new Integer[]{777, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        deque.addFront(777);
        Assert.assertEquals(11, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[]{1};
        deque.addFront(1);
        Assert.assertEquals(1, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
    }

    @Test
    public void testAddTail() {
        expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        deque.addTail(11);
        Assert.assertEquals(11, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[]{1};
        deque.addTail(1);
        Assert.assertEquals(1, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
    }

    @Test
    public void testRemoveFront() {
        expected = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
        Assert.assertEquals(Integer.valueOf(1), deque.removeFront());
        Assert.assertEquals(9, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[0];
        Assert.assertEquals(0, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
        Assert.assertNull(deque.removeFront());
        deque.addTail(1);
        Assert.assertEquals(Integer.valueOf(1), deque.removeFront());
        Assert.assertEquals(0, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
    }

    @Test
    public void testRemoveTail() {
        expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertEquals(Integer.valueOf(10), deque.removeTail());
        Assert.assertEquals(9, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[0];
        Assert.assertEquals(0, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
        Assert.assertNull(deque.removeTail());
        deque.addFront(1);
        Assert.assertEquals(Integer.valueOf(1), deque.removeTail());
        Assert.assertEquals(0, deque.size());
        Assert.assertArrayEquals(expected, toArray(deque));
    }

    private Integer[] toArray(Deque<Integer> deque) {
        int len = deque.size();
        Integer[] array = new Integer[len];
        for (int i = 0; i < len; i++) {
            array[i] = deque.removeFront();
        }
        return array;
    }
}
