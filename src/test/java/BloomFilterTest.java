import data_structures.BloomFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BloomFilterTest {

    private BloomFilter bloomFilter;

    @BeforeEach
    void setup() {
        bloomFilter = new BloomFilter(32);
    }

    @Test
    @DisplayName("containing value")
    void testIsValue() {
        bloomFilter.add("0123456789");
        assertTrue(bloomFilter.contains("0123456789"));

        bloomFilter.add("1234567890");
        assertTrue(bloomFilter.contains("1234567890"));

        bloomFilter.add("2345678901");
        assertTrue(bloomFilter.contains("2345678901"));

        bloomFilter.add("3456789012");
        assertTrue(bloomFilter.contains("3456789012"));

        bloomFilter.add("4567890123");
        assertTrue(bloomFilter.contains("4567890123"));

        bloomFilter.add("5678901234");
        assertTrue(bloomFilter.contains("5678901234"));

        bloomFilter.add("6789012345");
        assertTrue(bloomFilter.contains("6789012345"));

        bloomFilter.add("7890123456");
        assertTrue(bloomFilter.contains("7890123456"));

        bloomFilter.add("8901234567");
        assertTrue(bloomFilter.contains("8901234567"));

        bloomFilter.add("9012345678");
        assertTrue(bloomFilter.contains("9012345678"));
    }
}
