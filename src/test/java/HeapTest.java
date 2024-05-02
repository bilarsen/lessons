import data_structures.Heap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HeapTest {

    private Heap heap;

    @Test
    @DisplayName("getting max")
    void testGetMax() {
        heap = new Heap();
        assertEquals(-1, heap.getMax());
        heap.setHeapArray(new int[]{11, 9, 4, 7, 8, 3, 1, 2, 5, 6, -1, -1, -1, -1, -1});
        heap.setEmptySlot(10);
        int[] expected = new int[]{9, 8, 4, 7, 6, 3, 1, 2, 5, -1, -1, -1, -1, -1, -1};
        assertEquals(11, heap.getMax());
        assertArrayEquals(expected, heap.getHeapArray());
    }

    @Test
    @DisplayName("adding")
    void testAdd() {
        heap = new Heap();
        heap.setHeapArray(new int[7]);
        Arrays.fill(heap.getHeapArray(), -1);

        int[] expected = new int[]{1, -1, -1, -1, -1, -1, -1};
        heap.add(1);
        assertArrayEquals(expected, heap.getHeapArray());

        expected = new int[]{2, 1, -1, -1, -1, -1, -1};
        heap.add(2);
        assertArrayEquals(expected, heap.getHeapArray());

        expected = new int[]{3, 1, 2, -1, -1, -1, -1};
        heap.add(3);
        assertArrayEquals(expected, heap.getHeapArray());

        expected = new int[]{4, 3, 2, 1, -1, -1, -1};
        heap.add(4);
        assertArrayEquals(expected, heap.getHeapArray());

        expected = new int[]{5, 4, 2, 1, 3, -1, -1};
        heap.add(5);
        assertArrayEquals(expected, heap.getHeapArray());

        expected = new int[]{6, 4, 5, 1, 3, 2, -1};
        heap.add(6);
        assertArrayEquals(expected, heap.getHeapArray());

        expected = new int[]{7, 4, 6, 1, 3, 2, 5};
        heap.add(7);
        assertArrayEquals(expected, heap.getHeapArray());

        assertFalse(heap.add(0));
    }

    @Test
    @DisplayName("making heap")
    void testMakeHeap() {
        heap = new Heap();
        int[] array = new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        heap.makeHeap(array, 3);

        int[] expected = new int[]{17, 15, 10, 8, 13, 3, 6, 1, 5, 4, 9, -1, -1, -1, -1};
        assertEquals(15, heap.getHeapArray().length);
        assertEquals(11, heap.getEmptySlot());
        assertArrayEquals(expected, heap.getHeapArray());
    }
}
