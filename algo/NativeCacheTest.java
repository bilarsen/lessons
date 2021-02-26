import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NativeCacheTest {

    private NativeCache<String> nativeCache;

    @Before
    public void setup() {
        nativeCache = new NativeCache<>(17, String.class);
    }

    @Test
    public void testPut() {
        nativeCache.put("test", "test");
        Assert.assertEquals("test", nativeCache.slots[13]);
        Assert.assertEquals("test", nativeCache.values[13]);
        Assert.assertEquals(1, nativeCache.hits[13]);

        nativeCache.put("test", "new value");
        Assert.assertEquals("test", nativeCache.slots[13]);
        Assert.assertEquals("new value", nativeCache.values[13]);
        Assert.assertEquals(2, nativeCache.hits[13]);

        nativeCache.put("notest", "notest");
        Assert.assertEquals("notest", nativeCache.slots[15]);
        Assert.assertEquals("notest", nativeCache.values[15]);
        Assert.assertEquals(1, nativeCache.hits[15]);

        nativeCache.put("notest", "new notest");
        Assert.assertEquals("notest", nativeCache.slots[15]);
        Assert.assertEquals("new notest", nativeCache.values[15]);
        Assert.assertEquals(2, nativeCache.hits[15]);

        nativeCache.put("hello", "hello");
        Assert.assertEquals("hello", nativeCache.slots[0]);
        Assert.assertEquals("hello", nativeCache.values[0]);
        Assert.assertEquals(1, nativeCache.hits[0]);

        nativeCache.put("hello", "new hello");
        Assert.assertEquals("hello", nativeCache.slots[0]);
        Assert.assertEquals("new hello", nativeCache.values[0]);
        Assert.assertEquals(2, nativeCache.hits[0]);

        nativeCache.put("0123456789", "0123456789");
        Assert.assertEquals("0123456789", nativeCache.slots[16]);
        Assert.assertEquals("0123456789", nativeCache.values[16]);
        Assert.assertEquals(1, nativeCache.hits[16]);

        nativeCache.put("0123456789", "new 0123456789");
        Assert.assertEquals("0123456789", nativeCache.slots[16]);
        Assert.assertEquals("new 0123456789", nativeCache.values[16]);
        Assert.assertEquals(2, nativeCache.hits[16]);

        for (int i = 1; i < 13; i++) {
            nativeCache.slots[i] = "dummy";
            nativeCache.hits[i] = 2;
        }
        Assert.assertNull(nativeCache.slots[14]);
        Assert.assertEquals(0, nativeCache.hits[14]);

        nativeCache.put("world", "world");
        Assert.assertEquals("world", nativeCache.slots[14]);
        Assert.assertEquals("world", nativeCache.values[14]);
        Assert.assertEquals(1, nativeCache.hits[14]);

        nativeCache.put("foo", "foo");
        Assert.assertEquals("foo", nativeCache.slots[14]);
        Assert.assertEquals("foo", nativeCache.values[14]);
        Assert.assertEquals(1, nativeCache.hits[14]);

        nativeCache.get("foo");
        Assert.assertEquals(2, nativeCache.hits[14]);

        nativeCache.put("foo", "new foo");
        Assert.assertEquals("foo", nativeCache.slots[14]);
        Assert.assertEquals("new foo", nativeCache.values[14]);
        Assert.assertEquals(3, nativeCache.hits[14]);

        nativeCache.put("bar", "bar");
        Assert.assertEquals("bar", nativeCache.slots[8]);
        Assert.assertEquals("bar", nativeCache.values[8]);
        Assert.assertEquals(1, nativeCache.hits[8]);
    }
}