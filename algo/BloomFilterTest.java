import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BloomFilterTest {

    private BloomFilter bloomFilter;

    @Before
    public void setup() {
        bloomFilter = new BloomFilter(32);
    }

    @Test
    public void testIsValue() {
        bloomFilter.add("0123456789");
        Assert.assertTrue(bloomFilter.isValue("0123456789"));

        bloomFilter.add("1234567890");
        Assert.assertTrue(bloomFilter.isValue("1234567890"));

        bloomFilter.add("2345678901");
        Assert.assertTrue(bloomFilter.isValue("2345678901"));

        bloomFilter.add("3456789012");
        Assert.assertTrue(bloomFilter.isValue("3456789012"));

        bloomFilter.add("4567890123");
        Assert.assertTrue(bloomFilter.isValue("4567890123"));

        bloomFilter.add("5678901234");
        Assert.assertTrue(bloomFilter.isValue("5678901234"));

        bloomFilter.add("6789012345");
        Assert.assertTrue(bloomFilter.isValue("6789012345"));

        bloomFilter.add("7890123456");
        Assert.assertTrue(bloomFilter.isValue("7890123456"));

        bloomFilter.add("8901234567");
        Assert.assertTrue(bloomFilter.isValue("8901234567"));

        bloomFilter.add("9012345678");
        Assert.assertTrue(bloomFilter.isValue("9012345678"));
    }
}
