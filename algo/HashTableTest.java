import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashTableTest {

    private HashTable hashTable;

    @Before
    public void setup() {
        hashTable = new HashTable(17, 3);
    }

    @Test
    public void testHashFun() {
        Assert.assertEquals(0, hashTable.hashFun(""));

        // hashFun("hello") == 8
        // hashFun("test") == 9
        // hashFun("gelmo") == 9
        Assert.assertEquals(hashTable.hashFun("hello"), hashTable.hashFun("hello"));
        Assert.assertTrue(hashTable.hashFun("hello") < hashTable.size);
        Assert.assertNotEquals(hashTable.hashFun("hello"), hashTable.hashFun("Hello"));
        Assert.assertEquals(hashTable.hashFun("test"), hashTable.hashFun("gelmo"));
    }

    @Test
    public void testSeekSlot() {
        Assert.assertEquals(9, hashTable.seekSlot("test"));
        hashTable.slots[9] = "test";
        Assert.assertEquals(12, hashTable.seekSlot("test"));
        hashTable.slots[12] = "test";
        Assert.assertEquals(15, hashTable.seekSlot("test"));
        hashTable.slots[15] = "test";
        Assert.assertEquals(1, hashTable.seekSlot("test"));
        hashTable.slots[1] = "test";
        Assert.assertEquals(4, hashTable.seekSlot("test"));
        hashTable.slots[4] = "test";
        Assert.assertEquals(7, hashTable.seekSlot("test"));
        hashTable.slots[7] = "test";
        Assert.assertEquals(10, hashTable.seekSlot("test"));
        hashTable.slots[10] = "test";
        Assert.assertEquals(13, hashTable.seekSlot("test"));
        hashTable.slots[13] = "test";
        Assert.assertEquals(16, hashTable.seekSlot("test"));
        hashTable.slots[16] = "test";
        Assert.assertEquals(2, hashTable.seekSlot("test"));
        hashTable.slots[2] = "test";
        Assert.assertEquals(5, hashTable.seekSlot("test"));
        hashTable.slots[5] = "test";
        Assert.assertEquals(8, hashTable.seekSlot("test"));
        hashTable.slots[8] = "test";
        Assert.assertEquals(11, hashTable.seekSlot("test"));
        hashTable.slots[11] = "test";
        Assert.assertEquals(14, hashTable.seekSlot("test"));
        hashTable.slots[14] = "test";
        Assert.assertEquals(0, hashTable.seekSlot("test"));
        hashTable.slots[0] = "test";
        Assert.assertEquals(3, hashTable.seekSlot("test"));
        hashTable.slots[3] = "test";
        Assert.assertEquals(6, hashTable.seekSlot("test"));
        hashTable.slots[6] = "test";
        Assert.assertEquals(-1, hashTable.seekSlot("test"));
    }

    @Test
    public void testPut() {
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[9]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[12]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[15]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[1]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[4]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[7]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[10]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[13]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[16]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[2]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[5]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[8]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[11]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[14]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[0]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[3]);
        hashTable.put("test");
        Assert.assertEquals("test", hashTable.slots[6]);

        Assert.assertEquals(-1, hashTable.put("test"));
    }

    @Test
    public void testFind() {
        Assert.assertEquals(-1, hashTable.find("test"));
        hashTable.put("test");
        Assert.assertEquals(9, hashTable.find("test"));
        hashTable.put("test");
        hashTable.slots[9] = null;
        Assert.assertEquals(12, hashTable.find("test"));
        hashTable.put("hello");
        Assert.assertEquals(8, hashTable.find("hello"));
        Assert.assertEquals(-1, hashTable.find("not here"));
    }
}