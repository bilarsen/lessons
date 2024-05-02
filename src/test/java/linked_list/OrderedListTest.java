package linked_list;

import data_structures.linked_list.OrderedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderedListTest {

    private OrderedList<Integer> orderedList;

    @Test
    @DisplayName("comparing")
    void testCompare() {
        orderedList = new OrderedList<>(true);
        assertEquals(-1, orderedList.compare(1, 3));
        assertEquals(0, orderedList.compare(3, 3));
        assertEquals(1, orderedList.compare(3, 1));

        OrderedList<String> orderedListString = new OrderedList<>(true);
        assertEquals(-1, orderedListString.compare("John", "john"));
        assertEquals(0, orderedListString.compare("John", "John"));
        assertEquals(1, orderedListString.compare("john", "John"));

        OrderedList<Short> orderedListShort = new OrderedList<>(true);
        assertEquals(-1, orderedListShort.compare((short) 1, (short) 3));
        assertEquals(0, orderedListShort.compare((short) 3, (short) 3));
        assertEquals(1, orderedListShort.compare((short) 3, (short) 1));

        OrderedList<Long> orderedListLong = new OrderedList<>(true);
        assertEquals(-1, orderedListLong.compare(1L, 3L));
        assertEquals(0, orderedListLong.compare(3L, 3L));
        assertEquals(1, orderedListLong.compare(3L, 1L));

        OrderedList<Float> orderedListFloat = new OrderedList<>(true);
        assertEquals(-1, orderedListFloat.compare(1.3f, 1.4f));
        assertEquals(0, orderedListFloat.compare(3.0f, 3.0f));
        assertEquals(1, orderedListFloat.compare(1.34f, 1.33f));

        OrderedList<Double> orderedListDouble = new OrderedList<>(true);
        assertEquals(-1, orderedListDouble.compare(1.0007d, 1.0008d));
        assertEquals(0, orderedListDouble.compare(3.000d, 3.000d));
        assertEquals(1, orderedListDouble.compare(1.3456d, 1.345d));
    }

    @Test
    @DisplayName("adding ascending")
    void testAddAscending() {
        orderedList = new OrderedList<>(true);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        assertEquals(Integer.valueOf(1), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(5), orderedList.getTail().getValue());
        assertNull(orderedList.getHead().getPrev());
        assertNull(orderedList.getTail().getNext());
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));

        orderedList = new OrderedList<>(true);
        orderedList.add(2);
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertEquals(orderedList.getHead(), orderedList.getTail());
        assertNull(orderedList.getHead().getPrev());
        assertNull(orderedList.getTail().getNext());
        assertEquals(1, orderedList.count());

        orderedList = new OrderedList<>(true);
        expected = new Integer[]{2, 2, 2, 2, 2};
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertNull(orderedList.getHead().getPrev());
        assertNull(orderedList.getTail().getNext());
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));
        assertNotEquals(orderedList.getHead(), orderedList.getTail());
    }

    @Test
    @DisplayName("adding descending")
    void testAddDescending() {
        orderedList = new OrderedList<>(false);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        assertEquals(Integer.valueOf(5), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(1), orderedList.getTail().getValue());
        assertNull(orderedList.getHead().getPrev());
        assertNull(orderedList.getTail().getNext());
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));

        orderedList = new OrderedList<>(false);
        orderedList.add(2);
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertEquals(orderedList.getHead(), orderedList.getTail());
        assertNull(orderedList.getHead().getPrev());
        assertNull(orderedList.getTail().getNext());
        assertEquals(1, orderedList.count());

        orderedList = new OrderedList<>(false);
        expected = new Integer[]{2, 2, 2, 2, 2};
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        orderedList.add(2);
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertNull(orderedList.getHead().getPrev());
        assertNull(orderedList.getTail().getNext());
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));
        assertNotEquals(orderedList.getHead(), orderedList.getTail());
    }

    @Test
    @DisplayName("finding node")
    void testFind() {
        orderedList = new OrderedList<>(true);
        assertEquals(0, orderedList.count());
        assertNull(orderedList.find(1));

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        assertNull(orderedList.find(0));
        assertNull(orderedList.find(6));
        assertEquals(Integer.valueOf(2), orderedList.find(2).getValue());

        orderedList.add(5);
        OrderedList.Node<Integer> node = orderedList.find(5);
        assertEquals(node, orderedList.find(5));
        assertEquals(Integer.valueOf(5), orderedList.getTail().getValue());
        assertNotEquals(node, orderedList.getTail());
        assertEquals(Integer.valueOf(5), orderedList.getTail().getPrev().getValue());

        orderedList = new OrderedList<>(false);
        assertEquals(0, orderedList.count());
        assertNull(orderedList.find(1));

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        assertNull(orderedList.find(0));
        assertNull(orderedList.find(6));
        assertEquals(Integer.valueOf(2), orderedList.find(2).getValue());

        orderedList.add(5);
        node = orderedList.find(5);
        assertEquals(node, orderedList.find(5));
        assertEquals(Integer.valueOf(5), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(5), orderedList.getHead().getNext().getValue());
        assertNotEquals(node, orderedList.getHead().getNext());
        assertEquals(node, orderedList.getHead());
    }

    @Test
    @DisplayName("deleting ascending")
    void testDeleteAscending() {
        orderedList = new OrderedList<>(true);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);

        orderedList.delete(0);
        assertArrayEquals(expected, toArray(orderedList));
        assertEquals(5, orderedList.count());

        expected = new Integer[]{1, 2, 4, 5};
        orderedList.delete(3);
        assertEquals(4, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2, 4, 5};
        orderedList.delete(1);
        assertEquals(3, orderedList.count());
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(5), orderedList.getTail().getValue());
        assertNull(orderedList.getHead().getPrev());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2, 4};
        orderedList.delete(5);
        assertEquals(2, orderedList.count());
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(4), orderedList.getTail().getValue());
        assertNull(orderedList.getTail().getNext());
        assertEquals(orderedList.getHead(), orderedList.getTail().getPrev());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2};
        orderedList.delete(4);
        assertEquals(1, orderedList.count());
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertNull(orderedList.getTail().getNext());
        assertNull(orderedList.getTail().getPrev());
        assertNull(orderedList.getHead().getNext());
        assertNull(orderedList.getHead().getPrev());
        assertEquals(orderedList.getHead(), orderedList.getTail());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[0];
        orderedList.delete(2);
        assertEquals(0, orderedList.count());
        assertNull(orderedList.getTail());
        assertNull(orderedList.getHead());
        assertArrayEquals(expected, toArray(orderedList));

        orderedList.delete(2);
        assertEquals(0, orderedList.count());
        assertNull(orderedList.getTail());
        assertNull(orderedList.getHead());
        assertArrayEquals(expected, toArray(orderedList));
    }

    @Test
    @DisplayName("deleting descending")
    void testDeleteDescending() {
        orderedList = new OrderedList<>(false);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);

        orderedList.delete(0);
        assertArrayEquals(expected, toArray(orderedList));
        assertEquals(5, orderedList.count());

        expected = new Integer[]{5, 4, 2, 1};
        orderedList.delete(3);
        assertEquals(4, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{5, 4, 2};
        orderedList.delete(1);
        assertEquals(3, orderedList.count());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertEquals(Integer.valueOf(5), orderedList.getHead().getValue());
        assertNull(orderedList.getTail().getNext());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{4, 2};
        orderedList.delete(5);
        assertEquals(2, orderedList.count());
        assertEquals(Integer.valueOf(4), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertNull(orderedList.getHead().getPrev());
        assertEquals(orderedList.getHead(), orderedList.getTail().getPrev());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[]{2};
        orderedList.delete(4);
        assertEquals(1, orderedList.count());
        assertEquals(Integer.valueOf(2), orderedList.getHead().getValue());
        assertEquals(Integer.valueOf(2), orderedList.getTail().getValue());
        assertNull(orderedList.getTail().getNext());
        assertNull(orderedList.getTail().getPrev());
        assertNull(orderedList.getHead().getNext());
        assertNull(orderedList.getHead().getPrev());
        assertEquals(orderedList.getHead(), orderedList.getTail());
        assertArrayEquals(expected, toArray(orderedList));

        expected = new Integer[0];
        orderedList.delete(2);
        assertEquals(0, orderedList.count());
        assertNull(orderedList.getTail());
        assertNull(orderedList.getHead());
        assertArrayEquals(expected, toArray(orderedList));

        orderedList.delete(2);
        assertEquals(0, orderedList.count());
        assertNull(orderedList.getTail());
        assertNull(orderedList.getHead());
        assertArrayEquals(expected, toArray(orderedList));
    }

    @Test
    @DisplayName("clearing all nodes")
    void testClear() {
        orderedList = new OrderedList<>(true);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));

        orderedList.clear(false);
        expected = new Integer[0];
        assertArrayEquals(expected, toArray(orderedList));
        assertEquals(0, orderedList.count());

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        expected = new Integer[]{5, 4, 3, 2, 1};
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));

        orderedList.clear(true);
        expected = new Integer[0];
        assertArrayEquals(expected, toArray(orderedList));
        assertEquals(0, orderedList.count());

        orderedList.add(2);
        orderedList.add(4);
        orderedList.add(3);
        orderedList.add(5);
        orderedList.add(1);
        expected = new Integer[]{1, 2, 3, 4, 5};
        assertEquals(5, orderedList.count());
        assertArrayEquals(expected, toArray(orderedList));
    }

    private Integer[] toArray(OrderedList<Integer> orderedList) {
        Integer[] array = new Integer[orderedList.count()];
        OrderedList.Node<Integer> node = orderedList.getHead();
        for (int i = 0; i < array.length; i++) {
            array[i] = node.getValue();
            node = node.getNext();
        }
        return array;
    }
}
