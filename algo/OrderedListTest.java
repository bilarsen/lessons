import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class OrderedListTest {

    private OrderedList<Integer> orderedList;

    @Test
    public void testCompare() {
        orderedList = new OrderedList<>(true);
        Assert.assertEquals(-1, orderedList.compare(1, 3));
        Assert.assertEquals(0, orderedList.compare(3, 3));
        Assert.assertEquals(1, orderedList.compare(3, 1));

        OrderedList<String> orderedListString = new OrderedList<>(true);
        Assert.assertEquals(-1, orderedListString.compare("John", "john"));
        Assert.assertEquals(0, orderedListString.compare("John", "John"));
        Assert.assertEquals(1, orderedListString.compare("john", "John"));

        OrderedList<Short> orderedListShort = new OrderedList<>(true);
        Assert.assertEquals(-1, orderedListShort.compare((short) 1, (short) 3));
        Assert.assertEquals(0, orderedListShort.compare((short) 3, (short) 3));
        Assert.assertEquals(1, orderedListShort.compare((short) 3, (short) 1));

        OrderedList<Long> orderedListLong = new OrderedList<>(true);
        Assert.assertEquals(-1, orderedListLong.compare(1L, 3L));
        Assert.assertEquals(0, orderedListLong.compare(3L, 3L));
        Assert.assertEquals(1, orderedListLong.compare(3L, 1L));

        OrderedList<Float> orderedListFloat = new OrderedList<>(true);
        Assert.assertEquals(-1, orderedListFloat.compare(1.3f, 1.4f));
        Assert.assertEquals(0, orderedListFloat.compare(3.0f, 3.0f));
        Assert.assertEquals(1, orderedListFloat.compare(1.34f, 1.33f));

        OrderedList<Double> orderedListDouble = new OrderedList<>(true);
        Assert.assertEquals(-1, orderedListDouble.compare(1.0007d, 1.0008d));
        Assert.assertEquals(0, orderedListDouble.compare(3.000d, 3.000d));
        Assert.assertEquals(1, orderedListDouble.compare(1.3456d, 1.345d));
    }

    @Test
    public void testAddAscending() {
        orderedList = new OrderedList<>(true);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        Assert.assertEquals(Integer.valueOf(1), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(5), orderedList.tail.value);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));

        orderedList = new OrderedList<>(true);
        orderedList.add(2);
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertEquals(orderedList.head, orderedList.tail);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(1, orderedList.count());

        orderedList = new OrderedList<>(true);
        expected = new Integer[]{2, 2, 2, 2, 2};
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));
        Assert.assertNotEquals(orderedList.head, orderedList.tail);
    }

    @Test
    public void testAddDescending() {
        orderedList = new OrderedList<>(false);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        Assert.assertEquals(Integer.valueOf(5), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(1), orderedList.tail.value);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));

        orderedList = new OrderedList<>(false);
        orderedList.add(2);
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertEquals(orderedList.head, orderedList.tail);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(1, orderedList.count());

        orderedList = new OrderedList<>(false);
        expected = new Integer[]{2, 2, 2, 2, 2};
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));
        Assert.assertNotEquals(orderedList.head, orderedList.tail);
    }

    @Test
    public void testFind() {
        orderedList = new OrderedList<>(true);
        Assert.assertEquals(0, orderedList.count());
        Assert.assertNull(orderedList.find(1));

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        Assert.assertNull(orderedList.find(0));
        Assert.assertNull(orderedList.find(6));
        Assert.assertEquals(Integer.valueOf(2), orderedList.find(2).value);

        orderedList.add(5);
        Node<Integer> node = orderedList.find(5);
        Assert.assertEquals(node, orderedList.find(5));
        Assert.assertEquals(Integer.valueOf(5), orderedList.tail.value);
        Assert.assertNotEquals(node, orderedList.tail);
        Assert.assertEquals(Integer.valueOf(5), orderedList.tail.prev.value);

        orderedList = new OrderedList<>(false);
        Assert.assertEquals(0, orderedList.count());
        Assert.assertNull(orderedList.find(1));

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        Assert.assertNull(orderedList.find(0));
        Assert.assertNull(orderedList.find(6));
        Assert.assertEquals(Integer.valueOf(2), orderedList.find(2).value);

        orderedList.add(5);
        node = orderedList.find(5);
        Assert.assertEquals(node, orderedList.find(5));
        Assert.assertEquals(Integer.valueOf(5), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(5), orderedList.head.next.value);
        Assert.assertNotEquals(node, orderedList.head.next);
        Assert.assertEquals(node, orderedList.head);
    }

    @Test
    public void testDeleteAscending() {
        orderedList = new OrderedList<>(true);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);

        orderedList.delete(0);
        Assert.assertArrayEquals(expected, toArray(orderedList));
        Assert.assertEquals(5, orderedList.count());

        expected = new Integer[]{1, 2, 4, 5};
        orderedList.delete(3);
        Assert.assertEquals(4, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2, 4, 5};
        orderedList.delete(1);
        Assert.assertEquals(3, orderedList.count());
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(5), orderedList.tail.value);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2, 4};
        orderedList.delete(5);
        Assert.assertEquals(2, orderedList.count());
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(4), orderedList.tail.value);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertEquals(orderedList.head, orderedList.tail.prev);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2};
        orderedList.delete(4);
        Assert.assertEquals(1, orderedList.count());
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertNull(orderedList.tail.prev);
        Assert.assertNull(orderedList.head.next);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertEquals(orderedList.head, orderedList.tail);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[0];
        orderedList.delete(2);
        Assert.assertEquals(0, orderedList.count());
        Assert.assertNull(orderedList.tail);
        Assert.assertNull(orderedList.head);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        orderedList.delete(2);
        Assert.assertEquals(0, orderedList.count());
        Assert.assertNull(orderedList.tail);
        Assert.assertNull(orderedList.head);
        Assert.assertArrayEquals(expected, toArray(orderedList));
    }

    @Test
    public void testDeleteDescending() {
        orderedList = new OrderedList<>(false);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);

        orderedList.delete(0);
        Assert.assertArrayEquals(expected, toArray(orderedList));
        Assert.assertEquals(5, orderedList.count());

        expected = new Integer[]{5, 4, 2, 1};
        orderedList.delete(3);
        Assert.assertEquals(4, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{5, 4, 2};
        orderedList.delete(1);
        Assert.assertEquals(3, orderedList.count());
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertEquals(Integer.valueOf(5), orderedList.head.value);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{4, 2};
        orderedList.delete(5);
        Assert.assertEquals(2, orderedList.count());
        Assert.assertEquals(Integer.valueOf(4), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertEquals(orderedList.head, orderedList.tail.prev);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2};
        orderedList.delete(4);
        Assert.assertEquals(1, orderedList.count());
        Assert.assertEquals(Integer.valueOf(2), orderedList.head.value);
        Assert.assertEquals(Integer.valueOf(2), orderedList.tail.value);
        Assert.assertNull(orderedList.tail.next);
        Assert.assertNull(orderedList.tail.prev);
        Assert.assertNull(orderedList.head.next);
        Assert.assertNull(orderedList.head.prev);
        Assert.assertEquals(orderedList.head, orderedList.tail);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[0];
        orderedList.delete(2);
        Assert.assertEquals(0, orderedList.count());
        Assert.assertNull(orderedList.tail);
        Assert.assertNull(orderedList.head);
        Assert.assertArrayEquals(expected, toArray(orderedList));

        orderedList.delete(2);
        Assert.assertEquals(0, orderedList.count());
        Assert.assertNull(orderedList.tail);
        Assert.assertNull(orderedList.head);
        Assert.assertArrayEquals(expected, toArray(orderedList));
    }

    @Test
    public void testClear() {
        orderedList = new OrderedList<>(true);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));

        orderedList.clear(false);
        expected = new Integer[0];
        Assert.assertArrayEquals(expected, toArray(orderedList));
        Assert.assertEquals(0, orderedList.count());

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        expected = new Integer[]{5, 4, 3, 2, 1};
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));

        orderedList.clear(true);
        expected = new Integer[0];
        Assert.assertArrayEquals(expected, toArray(orderedList));
        Assert.assertEquals(0, orderedList.count());

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        expected = new Integer[]{1, 2, 3, 4, 5};
        Assert.assertEquals(5, orderedList.count());
        Assert.assertArrayEquals(expected, toArray(orderedList));
    }

    private Integer[] toArray(OrderedList<Integer> orderedList) {
        Integer[] array = new Integer[orderedList.count()];
        Node<Integer> node = orderedList.head;
        for (int i = 0; i < array.length; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }
}
