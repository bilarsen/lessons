package tree;

import data_structures.tree.ArrayBBST;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GenerateBBSTArrayTest {

    @Test
    @DisplayName("generating ArrayBBST")
    void testGenerateArrayBSST() {
        int[] expected = new int[]{2, 1, 3};
        int[] incoming = new int[]{2, 3, 1};
        int[] actual = ArrayBBST.generateBBSTArray(incoming);
        assertArrayEquals(expected, actual);

        expected = new int[]{3, 2, 4, 1};
        incoming = new int[]{4, 2, 1, 3};
        actual = ArrayBBST.generateBBSTArray(incoming);
        assertArrayEquals(expected, actual);

        expected = new int[]{4, 2, 6, 1, 3, 5, 7};
        incoming = new int[]{5, 1, 3, 2, 4, 7, 6};
        actual = ArrayBBST.generateBBSTArray(incoming);
        assertArrayEquals(expected, actual);
    }
}
