import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class DynArrayTest {

    private DynArray<Integer> dynArray;

    @Before
    public void setup() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
        dynArray.append(4);
        dynArray.append(5);
        dynArray.append(6);
        dynArray.append(7);
        dynArray.append(8);
        dynArray.append(9);
        dynArray.append(10);
        dynArray.append(11);
        dynArray.append(12);
        dynArray.append(13);
        dynArray.append(14);
        dynArray.append(15);
        dynArray.append(16);
    }

    @Test
    public void testMakeArray() {
        dynArray.makeArray(8);
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(16, dynArray.array.length);

        dynArray.makeArray(16);
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(16, dynArray.array.length);

        dynArray.makeArray(18);
        Assert.assertEquals(18, dynArray.capacity);
        Assert.assertEquals(18, dynArray.array.length);
        Assert.assertEquals(16, dynArray.count);
        Assert.assertEquals(Integer.valueOf(16), dynArray.getItem(15));
        Assert.assertNull(dynArray.array[16]);
        Assert.assertNull(dynArray.array[17]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        dynArray.getItem(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetLargeIndex() {
        dynArray.getItem(dynArray.count);
    }

    @Test
    public void testGetItem() {
        Assert.assertEquals(Integer.valueOf(1), dynArray.getItem(0));
        Assert.assertEquals(Integer.valueOf(16), dynArray.getItem(dynArray.count - 1));
        Assert.assertEquals(Integer.valueOf(2), dynArray.getItem(1));
    }

    @Test
    public void testAppend() {
        dynArray.append(999);
        Assert.assertEquals(Integer.valueOf(999), dynArray.getItem(dynArray.count - 1));
        Assert.assertEquals(32, dynArray.capacity);
        Assert.assertEquals(17, dynArray.count);
        Assert.assertEquals(32, dynArray.array.length);
        Assert.assertNull(dynArray.array[17]);
        Assert.assertNull(dynArray.array[31]);
    }

    @Test
    public void testAppendEmpty() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.append(999);
        Assert.assertEquals(Integer.valueOf(999), dynArray.getItem(0));
        Assert.assertEquals(1, dynArray.count);
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertNull(dynArray.array[1]);
    }

    @Test
    public void testInsert() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
        dynArray.append(4);
        dynArray.append(5);

        dynArray.insert(0, 0);
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5};
        Integer[] actual = Arrays.copyOfRange(dynArray.array, 0, 6);
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(Integer.valueOf(0), dynArray.getItem(0));
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
        Assert.assertEquals(6, dynArray.count);

        dynArray.insert(999, 6);
        expected = new Integer[]{0, 1, 2, 3, 4, 5, 999};
        actual = Arrays.copyOfRange(dynArray.array, 0, 7);
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(Integer.valueOf(999), dynArray.getItem(6));
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
        Assert.assertEquals(7, dynArray.count);

        dynArray.insert(777, 4);
        expected = new Integer[]{0, 1, 2, 3, 777, 4, 5, 999};
        actual = Arrays.copyOfRange(dynArray.array, 0, 8);
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(Integer.valueOf(777), dynArray.getItem(4));
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
        Assert.assertEquals(8, dynArray.count);
    }

    @Test
    public void testInsertEmpty() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.insert(999, dynArray.count);
        Assert.assertEquals(Integer.valueOf(999), dynArray.getItem(0));
        Assert.assertEquals(1, dynArray.count);
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertNull(dynArray.array[1]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInsertNegativeIndex() {
        dynArray.insert(999, -1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInsertLargeIndex() {
        dynArray.insert(999, 17);
    }

    @Test
    public void testIncreaseCapacity() {
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);

        dynArray.insert(999, 16);
        Assert.assertEquals(Integer.valueOf(999), dynArray.getItem(16));
        Assert.assertNull(dynArray.array[17]);
        Assert.assertEquals(32, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
    }

    @Test
    public void testRemove() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
        dynArray.append(4);
        dynArray.append(5);


        dynArray.remove( 0);
        Integer[] expected = new Integer[]{2, 3, 4, 5};
        Integer[] actual = Arrays.copyOfRange(dynArray.array, 0, 4);
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(Integer.valueOf(2), dynArray.getItem(0));
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
        Assert.assertEquals(4, dynArray.count);
        Assert.assertNull(dynArray.array[4]);

        dynArray.remove(dynArray.count - 1);
        expected = new Integer[]{2, 3, 4};
        actual = Arrays.copyOfRange(dynArray.array, 0, 3);
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(Integer.valueOf(4), dynArray.getItem(dynArray.count - 1));
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
        Assert.assertEquals(3, dynArray.count);
        Assert.assertNull(dynArray.array[3]);

        dynArray.remove(dynArray.count / 2);
        expected = new Integer[]{2, 4};
        actual = Arrays.copyOfRange(dynArray.array, 0, 2);
        Assert.assertArrayEquals(expected, actual);
        Assert.assertEquals(Integer.valueOf(4), dynArray.getItem(dynArray.count - 1));
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(dynArray.array.length, dynArray.capacity);
        Assert.assertEquals(2, dynArray.count);
        Assert.assertNull(dynArray.array[2]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveEmpty() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.remove(0);
    }

    @Test
    public void testDecreaseCapacity() {
        dynArray.append(999);
        Assert.assertEquals(32, dynArray.capacity);

        dynArray.remove(15);
        Assert.assertEquals(Integer.valueOf(999), dynArray.getItem(15));

        dynArray.remove(15);
        Assert.assertNull(dynArray.array[15]);
        Assert.assertEquals(Integer.valueOf(15), dynArray.getItem(14));
        Assert.assertEquals(21, dynArray.capacity);
        Assert.assertEquals(15, dynArray.count);

        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
        dynArray.append(4);
        dynArray.append(5);
        dynArray.append(6);
        Assert.assertEquals(21, dynArray.capacity);
        Assert.assertEquals(21, dynArray.count);

        dynArray.append(7);
        Assert.assertEquals(42, dynArray.capacity);
        Assert.assertEquals(22, dynArray.count);

        dynArray.remove(20);
        dynArray.remove(20);
        Assert.assertEquals(28, dynArray.capacity);
        Assert.assertEquals(20, dynArray.count);
    }

    @Test
    public void testMinCapacity() {
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        dynArray.remove(0);
        Assert.assertEquals(16, dynArray.capacity);
        Assert.assertEquals(7, dynArray.count);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        dynArray.remove(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemoveLargeIndex() {
        dynArray.remove(16);
    }
}