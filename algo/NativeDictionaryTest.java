import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class NativeDictionaryTest {

    private NativeDictionary<String> nativeDictionary;

    @Before
    public void setup() {
        nativeDictionary = new NativeDictionary<>(17, String.class);
    }

    @Test
    public void testIsKey() {
        // hashFun("test") = 13
        // hashFun("notest") = 13
        // hashFun("hello") = 13
        // hashFun("0123456789") = 16
        Assert.assertFalse(nativeDictionary.isKey("test"));
        Assert.assertFalse(nativeDictionary.isKey("0123456789"));
        nativeDictionary.slots[13] = "test";
        Assert.assertTrue(nativeDictionary.isKey("test"));
        Assert.assertFalse(nativeDictionary.isKey("notest"));
    }

    @Test
    public void testPut() {
        nativeDictionary.put("test", "test");
        Assert.assertEquals("test", nativeDictionary.slots[13]);
        Assert.assertEquals("test", nativeDictionary.values[13]);

        nativeDictionary.put("test", "new value");
        Assert.assertEquals("test", nativeDictionary.slots[13]);
        Assert.assertEquals("new value", nativeDictionary.values[13]);

        nativeDictionary.put("notest", "notest");
        Assert.assertEquals("notest", nativeDictionary.slots[15]);
        Assert.assertEquals("notest", nativeDictionary.values[15]);

        nativeDictionary.put("notest", "new notest");
        Assert.assertEquals("notest", nativeDictionary.slots[15]);
        Assert.assertEquals("new notest", nativeDictionary.values[15]);

        nativeDictionary.put("hello", "hello");
        Assert.assertEquals("hello", nativeDictionary.slots[0]);
        Assert.assertEquals("hello", nativeDictionary.values[0]);

        nativeDictionary.put("hello", "new hello");
        Assert.assertEquals("hello", nativeDictionary.slots[0]);
        Assert.assertEquals("new hello", nativeDictionary.values[0]);

        nativeDictionary.put("0123456789", "0123456789");
        Assert.assertEquals("0123456789", nativeDictionary.slots[16]);
        Assert.assertEquals("0123456789", nativeDictionary.values[16]);

        nativeDictionary.put("0123456789", "new 0123456789");
        Assert.assertEquals("0123456789", nativeDictionary.slots[16]);
        Assert.assertEquals("new 0123456789", nativeDictionary.values[16]);

        for (int i = 1; i < 13; i++) {
            nativeDictionary.slots[i] = "dummy";
        }
        Assert.assertNull(nativeDictionary.slots[14]);

        nativeDictionary.put("world", "world");
        Assert.assertEquals("world", nativeDictionary.slots[14]);
        Assert.assertEquals("world", nativeDictionary.values[14]);

        nativeDictionary.put("missing", "missing");
        Assert.assertFalse(nativeDictionary.isKey("missing"));
    }

    @Test
    public void testGet() {
        Assert.assertNull(nativeDictionary.get("test"));
        nativeDictionary.put("test", "value");
        Assert.assertEquals("value", nativeDictionary.get("test"));

        Assert.assertNull(nativeDictionary.get("value"));
    }
}