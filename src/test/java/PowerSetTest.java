import data_structures.PowerSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PowerSetTest {

    private PowerSet powerSet;

    @BeforeEach
    public void setup() {
        powerSet = new PowerSet();
    }

    @Test
    @DisplayName("putting")
    void testPut() {
        assertEquals(0, powerSet.size());

        powerSet.put("0123456789");
        assertEquals(1, powerSet.size());
        powerSet.put("0123456789");
        assertEquals(1, powerSet.size());

        powerSet.put("hello");
        assertEquals(2, powerSet.size());
        powerSet.put("hello");
        assertEquals(2, powerSet.size());

        powerSet.put(null);
        assertEquals(2, powerSet.size());
    }

    @Test
    @DisplayName("getting an element")
    void testGet() {
        assertFalse(powerSet.get("hello"));

        powerSet.put("hello");
        assertTrue(powerSet.get("hello"));

        powerSet.put("0123456789");
        assertTrue(powerSet.get("0123456789"));

        assertFalse(powerSet.get(null));
    }

    @Test
    @DisplayName("removing an element")
    void testRemove() {
        assertFalse(powerSet.remove("hello"));
        assertEquals(0, powerSet.size());

        powerSet.put("hello");
        powerSet.put("world");
        powerSet.put("test");
        powerSet.put("0123456789");
        powerSet.put("deleted");
        powerSet.put("remove me");
        powerSet.put("foo");
        powerSet.put("bar");
        assertEquals(8, powerSet.size());

        powerSet.remove(null);
        assertEquals(8, powerSet.size());

        assertTrue(powerSet.remove("hello"));
        assertEquals(7, powerSet.size());
        assertFalse(powerSet.remove("hello"));
        assertEquals(7, powerSet.size());
        assertTrue(powerSet.remove("world"));
        assertEquals(6, powerSet.size());
        assertTrue(powerSet.remove("test"));
        assertEquals(5, powerSet.size());
        assertTrue(powerSet.remove("0123456789"));
        assertEquals(4, powerSet.size());
        assertTrue(powerSet.remove("deleted"));
        assertEquals(3, powerSet.size());
        assertTrue(powerSet.remove("remove me"));
        assertEquals(2, powerSet.size());
        assertTrue(powerSet.remove("foo"));
        assertEquals(1, powerSet.size());
        assertTrue(powerSet.remove("bar"));
        assertEquals(0, powerSet.size());
        assertFalse(powerSet.remove("bar"));
        assertEquals(0, powerSet.size());
    }

    @Test
    @DisplayName("intersecting 2 sets")
    void testIntersection() {
        powerSet.put("hello");
        powerSet.put("world");
        powerSet.put("test");
        powerSet.put("0123456789");
        powerSet.put("deleted");
        powerSet.put("remove me");
        powerSet.put("foo");
        powerSet.put("bar");

        PowerSet set = new PowerSet();
        set.put("");
        set.put("hello");
        set.put("world");
        set.put("test");
        set.put("0123456789");
        set.put("not found");
        set.put("me");
        set.put("foobar");

        List<String> expected = Arrays.asList("hello", "world", "test", "0123456789");
        List<String> actual = powerSet.intersection(set).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(4, powerSet.intersection(set).size());

        set.remove("hello");
        set.remove("world");
        set.remove("test");
        set.remove("0123456789");
        assertEquals(0, powerSet.intersection(set).size());

        set.remove("");
        set.remove("not found");
        set.remove("me");
        set.remove("foobar");
        assertEquals(0, powerSet.intersection(set).size());

        set.put("word");
        powerSet.remove("hello");
        powerSet.remove("world");
        powerSet.remove("test");
        powerSet.remove("0123456789");
        powerSet.remove("deleted");
        powerSet.remove("remove me");
        powerSet.remove("foo");
        powerSet.remove("bar");
        assertEquals(0, powerSet.intersection(set).size());
    }

    @Test
    @DisplayName("getting union of 2 sets")
    void testUnion() {
        PowerSet set = new PowerSet();
        assertEquals(0, powerSet.union(set).size());

        assertEquals(0, powerSet.union(null).size());

        powerSet.put("hello");
        powerSet.put("world");
        powerSet.put("test");
        powerSet.put("0123456789");
        powerSet.put("deleted");
        powerSet.put("remove me");
        powerSet.put("foo");
        powerSet.put("bar");

        List<String> expected = new ArrayList<>(powerSet.toList());
        List<String> actual = powerSet.union(set).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(8, powerSet.union(set).size());

        set.put("");
        set.put("hello");
        set.put("world");
        set.put("test");
        set.put("0123456789");
        set.put("not found");
        set.put("me");
        set.put("foobar");

        expected.addAll(set.toList());
        actual = powerSet.union(set).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(12, powerSet.union(set).size());

        powerSet.remove("hello");
        powerSet.remove("world");
        powerSet.remove("test");
        powerSet.remove("0123456789");
        powerSet.remove("deleted");
        powerSet.remove("remove me");
        powerSet.remove("foo");
        powerSet.remove("bar");

        expected = set.toList();
        actual = powerSet.union(set).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(8, powerSet.union(set).size());
    }

    @Test
    @DisplayName("differencing 2 sets")
    void testDifference() {
        PowerSet set = new PowerSet();
        assertEquals(0, powerSet.difference(set).size());

        assertEquals(0, powerSet.difference(null).size());

        powerSet.put("hello");
        powerSet.put("world");
        powerSet.put("test");
        powerSet.put("0123456789");
        powerSet.put("deleted");
        powerSet.put("remove me");
        powerSet.put("foo");
        powerSet.put("bar");

        List<String> expected = new ArrayList<>(powerSet.toList());
        List<String> actual = powerSet.difference(set).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(8, powerSet.difference(set).size());
        actual = powerSet.difference(null).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(8, powerSet.difference(set).size());

        set.put("");
        set.put("hello");
        set.put("world");
        set.put("test");
        set.put("0123456789");
        set.put("not found");
        set.put("me");
        set.put("foobar");

        expected = Arrays.asList("deleted", "remove me", "foo", "bar");
        actual = powerSet.difference(set).toList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        assertEquals(4, powerSet.difference(set).size());

        powerSet.remove("hello");
        powerSet.remove("world");
        powerSet.remove("test");
        powerSet.remove("0123456789");
        powerSet.remove("deleted");
        powerSet.remove("remove me");
        powerSet.remove("foo");
        powerSet.remove("bar");

        assertEquals(0, powerSet.difference(set).size());
    }

    @Test
    @DisplayName("checking for a subset")
    void testIsSubset() {
        PowerSet set = new PowerSet();
        assertTrue(powerSet.isSubset(set));
        assertTrue(powerSet.isSubset(null));

        powerSet.put("hello");
        powerSet.put("world");
        powerSet.put("test");
        powerSet.put("0123456789");
        powerSet.put("deleted");
        powerSet.put("remove me");
        powerSet.put("foo");
        powerSet.put("bar");

        set.put("");
        set.put("world");
        set.put("test");
        set.put("0123456789");
        assertFalse(powerSet.isSubset(set));

        set.remove("");
        assertTrue(powerSet.isSubset(set));

        set.put("hello");
        set.put("deleted");
        set.put("remove me");
        set.put("foo");
        set.put("bar");
        assertTrue(powerSet.isSubset(set));

        powerSet.remove("hello");
        powerSet.remove("world");
        powerSet.remove("test");
        powerSet.remove("0123456789");
        powerSet.remove("deleted");
        powerSet.remove("remove me");
        powerSet.remove("foo");
        powerSet.remove("bar");
        assertFalse(powerSet.isSubset(set));
    }
}