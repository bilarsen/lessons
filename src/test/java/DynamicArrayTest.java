import data_structures.DynamicArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DynamicArrayTest {

    private DynamicArray<Integer> dynamicArray;

    @BeforeEach
    public void setup() {
        dynamicArray = new DynamicArray<>(Integer.class);
        dynamicArray.append(1);
        dynamicArray.append(2);
        dynamicArray.append(3);
        dynamicArray.append(4);
        dynamicArray.append(5);
        dynamicArray.append(6);
        dynamicArray.append(7);
        dynamicArray.append(8);
        dynamicArray.append(9);
        dynamicArray.append(10);
        dynamicArray.append(11);
        dynamicArray.append(12);
        dynamicArray.append(13);
        dynamicArray.append(14);
        dynamicArray.append(15);
        dynamicArray.append(16);
    }

    @Test
    @DisplayName("making array")
    void testMakeArray() {
        dynamicArray.makeArray(8);
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(16, dynamicArray.getArray().length);

        dynamicArray.makeArray(16);
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(16, dynamicArray.getArray().length);

        dynamicArray.makeArray(18);
        assertEquals(18, dynamicArray.getCapacity());
        assertEquals(18, dynamicArray.getArray().length);
        assertEquals(16, dynamicArray.getCount());
        assertEquals(Integer.valueOf(16), dynamicArray.getItem(15));
        assertNull(dynamicArray.getArray()[16]);
        assertNull(dynamicArray.getArray()[17]);
    }

    @Test
    @DisplayName("getting negative index")
    void testGetNegativeIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.getItem(-1));
    }

    @Test
    @DisplayName("getting a large index")
    void testGetLargeIndex() {
        int index = dynamicArray.getCount();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.getItem(index));
    }

    @Test
    @DisplayName("getting an item")
    void testGetItem() {
        assertEquals(Integer.valueOf(1), dynamicArray.getItem(0));
        assertEquals(Integer.valueOf(16), dynamicArray.getItem(dynamicArray.getCount() - 1));
        assertEquals(Integer.valueOf(2), dynamicArray.getItem(1));
    }

    @Test
    @DisplayName("appending an item")
    void testAppend() {
        dynamicArray.append(999);
        assertEquals(Integer.valueOf(999), dynamicArray.getItem(dynamicArray.getCount() - 1));
        assertEquals(32, dynamicArray.getCapacity());
        assertEquals(17, dynamicArray.getCount());
        assertEquals(32, dynamicArray.getArray().length);
        assertNull(dynamicArray.getArray()[17]);
        assertNull(dynamicArray.getArray()[31]);
    }

    @Test
    @DisplayName("appending to an empty array")
    void testAppendEmpty() {
        dynamicArray = new DynamicArray<>(Integer.class);
        dynamicArray.append(999);
        assertEquals(Integer.valueOf(999), dynamicArray.getItem(0));
        assertEquals(1, dynamicArray.getCount());
        assertEquals(16, dynamicArray.getCapacity());
        assertNull(dynamicArray.getArray()[1]);
    }

    @Test
    @DisplayName("inserting an item")
    void testInsert() {
        dynamicArray = new DynamicArray<>(Integer.class);
        dynamicArray.append(1);
        dynamicArray.append(2);
        dynamicArray.append(3);
        dynamicArray.append(4);
        dynamicArray.append(5);

        dynamicArray.insert(0, 0);
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5};
        Integer[] actual = Arrays.copyOfRange(dynamicArray.getArray(), 0, 6);
        assertArrayEquals(expected, actual);
        assertEquals(Integer.valueOf(0), dynamicArray.getItem(0));
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
        assertEquals(6, dynamicArray.getCount());

        dynamicArray.insert(999, 6);
        expected = new Integer[]{0, 1, 2, 3, 4, 5, 999};
        actual = Arrays.copyOfRange(dynamicArray.getArray(), 0, 7);
        assertArrayEquals(expected, actual);
        assertEquals(Integer.valueOf(999), dynamicArray.getItem(6));
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
        assertEquals(7, dynamicArray.getCount());

        dynamicArray.insert(777, 4);
        expected = new Integer[]{0, 1, 2, 3, 777, 4, 5, 999};
        actual = Arrays.copyOfRange(dynamicArray.getArray(), 0, 8);
        assertArrayEquals(expected, actual);
        assertEquals(Integer.valueOf(777), dynamicArray.getItem(4));
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
        assertEquals(8, dynamicArray.getCount());
    }

    @Test
    @DisplayName("inserting into an empty array")
    void testInsertEmpty() {
        dynamicArray = new DynamicArray<>(Integer.class);
        dynamicArray.insert(999, dynamicArray.getCount());
        assertEquals(Integer.valueOf(999), dynamicArray.getItem(0));
        assertEquals(1, dynamicArray.getCount());
        assertEquals(16, dynamicArray.getCapacity());
        assertNull(dynamicArray.getArray()[1]);
    }

    @Test
    @DisplayName("inserting negative index")
    void testInsertNegativeIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.insert(999, -1));
    }

    @Test
    @DisplayName("inserting large index")
    void testInsertLargeIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.insert(999, 17));
    }

    @Test
    @DisplayName("increasing capacity")
    void testIncreaseCapacity() {
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());

        dynamicArray.insert(999, 16);
        assertEquals(Integer.valueOf(999), dynamicArray.getItem(16));
        assertNull(dynamicArray.getArray()[17]);
        assertEquals(32, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
    }

    @Test
    @DisplayName("removing an item")
    void testRemove() {
        dynamicArray = new DynamicArray<>(Integer.class);
        dynamicArray.append(1);
        dynamicArray.append(2);
        dynamicArray.append(3);
        dynamicArray.append(4);
        dynamicArray.append(5);


        dynamicArray.remove( 0);
        Integer[] expected = new Integer[]{2, 3, 4, 5};
        Integer[] actual = Arrays.copyOfRange(dynamicArray.getArray(), 0, 4);
        assertArrayEquals(expected, actual);
        assertEquals(Integer.valueOf(2), dynamicArray.getItem(0));
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
        assertEquals(4, dynamicArray.getCount());
        assertNull(dynamicArray.getArray()[4]);

        dynamicArray.remove(dynamicArray.getCount() - 1);
        expected = new Integer[]{2, 3, 4};
        actual = Arrays.copyOfRange(dynamicArray.getArray(), 0, 3);
        assertArrayEquals(expected, actual);
        assertEquals(Integer.valueOf(4), dynamicArray.getItem(dynamicArray.getCount() - 1));
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
        assertEquals(3, dynamicArray.getCount());
        assertNull(dynamicArray.getArray()[3]);

        dynamicArray.remove(dynamicArray.getCount() / 2);
        expected = new Integer[]{2, 4};
        actual = Arrays.copyOfRange(dynamicArray.getArray(), 0, 2);
        assertArrayEquals(expected, actual);
        assertEquals(Integer.valueOf(4), dynamicArray.getItem(dynamicArray.getCount() - 1));
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(dynamicArray.getArray().length, dynamicArray.getCapacity());
        assertEquals(2, dynamicArray.getCount());
        assertNull(dynamicArray.getArray()[2]);
    }

    @Test
    @DisplayName("removing from an empty array")
    void testRemoveEmpty() {
        dynamicArray = new DynamicArray<>(Integer.class);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.remove(0));
    }

    @Test
    @DisplayName("decreasing capacity")
    void testDecreaseCapacity() {
        dynamicArray.append(999);
        assertEquals(32, dynamicArray.getCapacity());

        dynamicArray.remove(15);
        assertEquals(Integer.valueOf(999), dynamicArray.getItem(15));

        dynamicArray.remove(15);
        assertNull(dynamicArray.getArray()[15]);
        assertEquals(Integer.valueOf(15), dynamicArray.getItem(14));
        assertEquals(21, dynamicArray.getCapacity());
        assertEquals(15, dynamicArray.getCount());

        dynamicArray.append(1);
        dynamicArray.append(2);
        dynamicArray.append(3);
        dynamicArray.append(4);
        dynamicArray.append(5);
        dynamicArray.append(6);
        assertEquals(21, dynamicArray.getCapacity());
        assertEquals(21, dynamicArray.getCount());

        dynamicArray.append(7);
        assertEquals(42, dynamicArray.getCapacity());
        assertEquals(22, dynamicArray.getCount());

        dynamicArray.remove(20);
        dynamicArray.remove(20);
        assertEquals(28, dynamicArray.getCapacity());
        assertEquals(20, dynamicArray.getCount());
    }

    @Test
    @DisplayName("checking minimum capacity")
    void testMinCapacity() {
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        dynamicArray.remove(0);
        assertEquals(16, dynamicArray.getCapacity());
        assertEquals(7, dynamicArray.getCount());
    }

    @Test
    @DisplayName("removing negative index")
    void testRemoveNegativeIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.remove(-1));
    }

    @Test
    @DisplayName("removing large index")
    void testRemoveLargeIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> dynamicArray.remove(16));
    }
}