import data_structures.NativeDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NativeDictionaryTest {

    private NativeDictionary<String> nativeDictionary;

    @BeforeEach
    public void setup() {
        nativeDictionary = new NativeDictionary<>(17, String.class);
    }

    @Test
    @DisplayName("is key present")
    void testIsKey() {
        assertFalse(nativeDictionary.isKey("test"));
        assertFalse(nativeDictionary.isKey("0123456789"));
        nativeDictionary.getSlots()[13] = "test";
        assertTrue(nativeDictionary.isKey("test"));
        assertFalse(nativeDictionary.isKey("notest"));
    }

    @Test
    @DisplayName("putting")
    void testPut() {
        nativeDictionary.put("test", "test");
        assertEquals("test", nativeDictionary.getSlots()[13]);
        assertEquals("test", nativeDictionary.getValues()[13]);

        nativeDictionary.put("test", "new value");
        assertEquals("test", nativeDictionary.getSlots()[13]);
        assertEquals("new value", nativeDictionary.getValues()[13]);

        nativeDictionary.put("notest", "notest");
        assertEquals("notest", nativeDictionary.getSlots()[15]);
        assertEquals("notest", nativeDictionary.getValues()[15]);

        nativeDictionary.put("notest", "new notest");
        assertEquals("notest", nativeDictionary.getSlots()[15]);
        assertEquals("new notest", nativeDictionary.getValues()[15]);

        nativeDictionary.put("hello", "hello");
        assertEquals("hello", nativeDictionary.getSlots()[0]);
        assertEquals("hello", nativeDictionary.getValues()[0]);

        nativeDictionary.put("hello", "new hello");
        assertEquals("hello", nativeDictionary.getSlots()[0]);
        assertEquals("new hello", nativeDictionary.getValues()[0]);

        nativeDictionary.put("0123456789", "0123456789");
        assertEquals("0123456789", nativeDictionary.getSlots()[16]);
        assertEquals("0123456789", nativeDictionary.getValues()[16]);

        nativeDictionary.put("0123456789", "new 0123456789");
        assertEquals("0123456789", nativeDictionary.getSlots()[16]);
        assertEquals("new 0123456789", nativeDictionary.getValues()[16]);

        for (int i = 1; i < 13; i++)
            nativeDictionary.getSlots()[i] = "dummy";
        assertNull(nativeDictionary.getSlots()[14]);

        nativeDictionary.put("world", "world");
        assertEquals("world", nativeDictionary.getSlots()[14]);
        assertEquals("world", nativeDictionary.getValues()[14]);

        nativeDictionary.put("missing", "missing");
        assertFalse(nativeDictionary.isKey("missing"));
    }

    @Test
    @DisplayName("getting an element")
    void testGet() {
        assertNull(nativeDictionary.get("test"));
        nativeDictionary.put("test", "value");
        assertEquals("value", nativeDictionary.get("test"));

        assertNull(nativeDictionary.get("value"));
    }
}