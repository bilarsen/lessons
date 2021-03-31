import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class GenerateBBSTArrayTest {

    @Test
    public void testGenerateBSSTArray() {
        int[] expected = new int[]{2, 1, 3};
        int[] incoming = new int[]{2, 3, 1};
        int[] actual = AlgorithmsDataStructures2.GenerateBBSTArray(incoming);
        Assert.assertArrayEquals(expected, actual);

        expected = new int[]{3, 2, 4, 1};
        incoming = new int[]{4, 2, 1, 3};
        actual = AlgorithmsDataStructures2.GenerateBBSTArray(incoming);
        Assert.assertArrayEquals(expected, actual);

        expected = new int[]{4, 2, 6, 1, 3, 5, 7};
        incoming = new int[]{5, 1, 3, 2, 4, 7, 6};
        actual = AlgorithmsDataStructures2.GenerateBBSTArray(incoming);
        Assert.assertArrayEquals(expected, actual);
    }
}
