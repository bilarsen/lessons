import data_structures.NativeCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NativeCacheTest {

    private NativeCache<String> nativeCache;

    @BeforeEach
    public void setup() {
        nativeCache = new NativeCache<>(17, String.class);
    }

    @Test
    @DisplayName("putting")
    void testPut() {
        nativeCache.put("test", "test");
        assertEquals("test", nativeCache.getSlots()[13]);
        assertEquals("test", nativeCache.getValues()[13]);
        assertEquals(1, nativeCache.getHits()[13]);

        nativeCache.put("test", "new value");
        assertEquals("test", nativeCache.getSlots()[13]);
        assertEquals("new value", nativeCache.getValues()[13]);
        assertEquals(2, nativeCache.getHits()[13]);

        nativeCache.put("notest", "notest");
        assertEquals("notest", nativeCache.getSlots()[15]);
        assertEquals("notest", nativeCache.getValues()[15]);
        assertEquals(1, nativeCache.getHits()[15]);

        nativeCache.put("notest", "new notest");
        assertEquals("notest", nativeCache.getSlots()[15]);
        assertEquals("new notest", nativeCache.getValues()[15]);
        assertEquals(2, nativeCache.getHits()[15]);

        nativeCache.put("hello", "hello");
        assertEquals("hello", nativeCache.getSlots()[0]);
        assertEquals("hello", nativeCache.getValues()[0]);
        assertEquals(1, nativeCache.getHits()[0]);

        nativeCache.put("hello", "new hello");
        assertEquals("hello", nativeCache.getSlots()[0]);
        assertEquals("new hello", nativeCache.getValues()[0]);
        assertEquals(2, nativeCache.getHits()[0]);

        nativeCache.put("0123456789", "0123456789");
        assertEquals("0123456789", nativeCache.getSlots()[16]);
        assertEquals("0123456789", nativeCache.getValues()[16]);
        assertEquals(1, nativeCache.getHits()[16]);

        nativeCache.put("0123456789", "new 0123456789");
        assertEquals("0123456789", nativeCache.getSlots()[16]);
        assertEquals("new 0123456789", nativeCache.getValues()[16]);
        assertEquals(2, nativeCache.getHits()[16]);

        for (int i = 1; i < 13; i++) {
            nativeCache.getSlots()[i] = "dummy";
            nativeCache.getHits()[i] = 2;
        }
        assertNull(nativeCache.getSlots()[14]);
        assertEquals(0, nativeCache.getHits()[14]);

        nativeCache.put("world", "world");
        assertEquals("world", nativeCache.getSlots()[14]);
        assertEquals("world", nativeCache.getValues()[14]);
        assertEquals(1, nativeCache.getHits()[14]);

        nativeCache.put("foo", "foo");
        assertEquals("foo", nativeCache.getSlots()[14]);
        assertEquals("foo", nativeCache.getValues()[14]);
        assertEquals(1, nativeCache.getHits()[14]);

        nativeCache.get("foo");
        assertEquals(2, nativeCache.getHits()[14]);

        nativeCache.put("foo", "new foo");
        assertEquals("foo", nativeCache.getSlots()[14]);
        assertEquals("new foo", nativeCache.getValues()[14]);
        assertEquals(3, nativeCache.getHits()[14]);

        nativeCache.put("bar", "bar");
        assertEquals("bar", nativeCache.getSlots()[8]);
        assertEquals("bar", nativeCache.getValues()[8]);
        assertEquals(1, nativeCache.getHits()[8]);
    }
}