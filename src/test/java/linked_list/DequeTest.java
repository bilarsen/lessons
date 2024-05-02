package linked_list;

import data_structures.linked_list.Deque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DequeTest {

    private Deque<Integer> deque;

    private Integer[] expected;

    @BeforeEach
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
    @DisplayName("checking size")
    void testSize() {
        expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(10, deque.size());
        assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[0];
        assertEquals(0, deque.size());
        assertArrayEquals(expected, toArray(deque));
    }

    @Test
    @DisplayName("adding to front")
    void testAddFront() {
        expected = new Integer[]{777, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        deque.addFront(777);
        assertEquals(11, deque.size());
        assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[]{1};
        deque.addFront(1);
        assertEquals(1, deque.size());
        assertArrayEquals(expected, toArray(deque));
    }

    @Test
    @DisplayName("adding to tail")
    void testAddTail() {
        expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        deque.addTail(11);
        assertEquals(11, deque.size());
        assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[]{1};
        deque.addTail(1);
        assertEquals(1, deque.size());
        assertArrayEquals(expected, toArray(deque));
    }

    @Test
    @DisplayName("removing from front")
    void testRemoveFront() {
        expected = new Integer[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(Integer.valueOf(1), deque.removeFront());
        assertEquals(9, deque.size());
        assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[0];
        assertEquals(0, deque.size());
        assertArrayEquals(expected, toArray(deque));
        assertNull(deque.removeFront());
        deque.addTail(1);
        assertEquals(Integer.valueOf(1), deque.removeFront());
        assertEquals(0, deque.size());
        assertArrayEquals(expected, toArray(deque));
    }

    @Test
    @DisplayName("removing from tail")
    void testRemoveTail() {
        expected = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(Integer.valueOf(10), deque.removeTail());
        assertEquals(9, deque.size());
        assertArrayEquals(expected, toArray(deque));

        deque = new Deque<>();
        expected = new Integer[0];
        assertEquals(0, deque.size());
        assertArrayEquals(expected, toArray(deque));
        assertNull(deque.removeTail());
        deque.addFront(1);
        assertEquals(Integer.valueOf(1), deque.removeTail());
        assertEquals(0, deque.size());
        assertArrayEquals(expected, toArray(deque));
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
