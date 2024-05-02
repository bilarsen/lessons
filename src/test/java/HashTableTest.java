import data_structures.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {

    private HashTable hashTable;

    @BeforeEach
    void setup() {
        hashTable = new HashTable(17, 3);
    }

    @Test
    @DisplayName("hashing values")
    void testHashFun() {
        assertEquals(0, hashTable.hashFun(""));

        assertEquals(hashTable.hashFun("hello"), hashTable.hashFun("hello"));
        assertTrue(hashTable.hashFun("hello") < hashTable.getSize());
        assertNotEquals(hashTable.hashFun("hello"), hashTable.hashFun("Hello"));
        assertEquals(hashTable.hashFun("test"), hashTable.hashFun("gelmo"));
    }

    @Test
    @DisplayName("seeking slots")
    void testSeekSlot() {
        assertEquals(9, hashTable.seekSlot("test"));
        hashTable.getSlots()[9] = "test";
        assertEquals(12, hashTable.seekSlot("test"));
        hashTable.getSlots()[12] = "test";
        assertEquals(15, hashTable.seekSlot("test"));
        hashTable.getSlots()[15] = "test";
        assertEquals(1, hashTable.seekSlot("test"));
        hashTable.getSlots()[1] = "test";
        assertEquals(4, hashTable.seekSlot("test"));
        hashTable.getSlots()[4] = "test";
        assertEquals(7, hashTable.seekSlot("test"));
        hashTable.getSlots()[7] = "test";
        assertEquals(10, hashTable.seekSlot("test"));
        hashTable.getSlots()[10] = "test";
        assertEquals(13, hashTable.seekSlot("test"));
        hashTable.getSlots()[13] = "test";
        assertEquals(16, hashTable.seekSlot("test"));
        hashTable.getSlots()[16] = "test";
        assertEquals(2, hashTable.seekSlot("test"));
        hashTable.getSlots()[2] = "test";
        assertEquals(5, hashTable.seekSlot("test"));
        hashTable.getSlots()[5] = "test";
        assertEquals(8, hashTable.seekSlot("test"));
        hashTable.getSlots()[8] = "test";
        assertEquals(11, hashTable.seekSlot("test"));
        hashTable.getSlots()[11] = "test";
        assertEquals(14, hashTable.seekSlot("test"));
        hashTable.getSlots()[14] = "test";
        assertEquals(0, hashTable.seekSlot("test"));
        hashTable.getSlots()[0] = "test";
        assertEquals(3, hashTable.seekSlot("test"));
        hashTable.getSlots()[3] = "test";
        assertEquals(6, hashTable.seekSlot("test"));
        hashTable.getSlots()[6] = "test";
        assertEquals(-1, hashTable.seekSlot("test"));
    }

    @Test
    @DisplayName("putting values")
    void testPut() {
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[9]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[12]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[15]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[1]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[4]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[7]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[10]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[13]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[16]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[2]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[5]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[8]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[11]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[14]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[0]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[3]);
        hashTable.put("test");
        assertEquals("test", hashTable.getSlots()[6]);

        assertEquals(-1, hashTable.put("test"));
    }

    @Test
    @DisplayName("finding elements")
    void testFind() {
        assertEquals(-1, hashTable.find("test"));
        hashTable.put("test");
        assertEquals(9, hashTable.find("test"));
        hashTable.put("test");
        hashTable.getSlots()[9] = null;
        assertEquals(12, hashTable.find("test"));
        hashTable.put("hello");
        assertEquals(8, hashTable.find("hello"));
        assertEquals(-1, hashTable.find("not here"));
    }
}