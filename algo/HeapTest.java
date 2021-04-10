import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HeapTest {

    private Heap heap;

    @Test
    public void testGetMax() {
        heap = new Heap();
        Assert.assertEquals(-1, heap.GetMax());
        heap.HeapArray = new int[]{11, 9, 4, 7, 8, 3, 1, 2, 5, 6, -1, -1, -1, -1, -1};
        heap.emptySlot = 10;
        int[] expected = new int[]{9, 8, 4, 7, 6, 3, 1, 2, 5, -1, -1, -1, -1, -1, -1};
        Assert.assertEquals(11, heap.GetMax());
        Assert.assertArrayEquals(expected, heap.HeapArray);
    }

    @Test
    public void testAdd() {
        heap = new Heap();
        heap.HeapArray = new int[7];
        Arrays.fill(heap.HeapArray, -1);

        int[] expected = new int[]{1, -1, -1, -1, -1, -1, -1};
        heap.Add(1);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        expected = new int[]{2, 1, -1, -1, -1, -1, -1};
        heap.Add(2);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        expected = new int[]{3, 1, 2, -1, -1, -1, -1};
        heap.Add(3);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        expected = new int[]{4, 3, 2, 1, -1, -1, -1};
        heap.Add(4);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        expected = new int[]{5, 4, 2, 1, 3, -1, -1};
        heap.Add(5);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        expected = new int[]{6, 4, 5, 1, 3, 2, -1};
        heap.Add(6);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        expected = new int[]{7, 4, 6, 1, 3, 2, 5};
        heap.Add(7);
        Assert.assertArrayEquals(expected, heap.HeapArray);

        Assert.assertFalse(heap.Add(0));
    }

    @Test
    public void testMakeHeap() {
        heap = new Heap();
        int[] array = new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        heap.MakeHeap(array, 3);

        int[] expected = new int[]{17, 15, 10, 8, 13, 3, 6, 1, 5, 4, 9, -1, -1, -1, -1};
        Assert.assertEquals(15, heap.HeapArray.length);
        Assert.assertEquals(11, heap.emptySlot);
        Assert.assertArrayEquals(expected, heap.HeapArray);
    }
}
