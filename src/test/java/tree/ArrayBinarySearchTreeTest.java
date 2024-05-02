package tree;

import data_structures.tree.ArrayBST;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArrayBinarySearchTreeTest {

    private ArrayBST tree;

    @Test
    @DisplayName("finding key index")
    void testFindKeyIndex() {
        tree = new ArrayBST(0);
        assertEquals(1, tree.getArrayLength());
        assertNull(tree.getTree()[0]);
        assertEquals(Integer.valueOf(0), tree.findKeyIndex(100500));

        fillTree();
        assertEquals(15, tree.getArrayLength());
        assertEquals(Integer.valueOf(6), tree.findKeyIndex(22));
        assertNull(tree.findKeyIndex(8));
        assertEquals(Integer.valueOf(-14), tree.findKeyIndex(32));
    }

    @Test
    @DisplayName("adding key")
    void testAddKey() {
        tree = new ArrayBST(0);
        tree.addKey(100500);
        assertEquals(Integer.valueOf(100500), tree.getTree()[0]);

        tree = new ArrayBST(3);
        tree.addKey(9);
        tree.addKey(4);
        tree.addKey(17);
        tree.addKey(3);
        tree.addKey(6);
        tree.addKey(5);
        tree.addKey(7);
        tree.addKey(22);
        tree.addKey(20);

        assertEquals(Integer.valueOf(9), tree.getTree()[0]);
        assertEquals(Integer.valueOf(4), tree.getTree()[1]);
        assertEquals(Integer.valueOf(17), tree.getTree()[2]);
        assertEquals(Integer.valueOf(3), tree.getTree()[3]);
        assertEquals(Integer.valueOf(6), tree.getTree()[4]);
        assertNull(tree.getTree()[5]);
        assertEquals(Integer.valueOf(22), tree.getTree()[6]);
        assertNull(tree.getTree()[7]);
        assertNull(tree.getTree()[8]);
        assertEquals(Integer.valueOf(5), tree.getTree()[9]);
        assertEquals(Integer.valueOf(7), tree.getTree()[10]);
        assertNull(tree.getTree()[11]);
        assertNull(tree.getTree()[12]);
        assertEquals(Integer.valueOf(20), tree.getTree()[13]);
        assertNull(tree.getTree()[14]);

        tree.addKey(32);
        assertEquals(Integer.valueOf(32), tree.getTree()[14]);
    }

    private void fillTree() {
        tree = new ArrayBST(3);
        tree.getTree()[0] = 9;
        tree.getTree()[1] = 4;
        tree.getTree()[2] = 17;
        tree.getTree()[3] = 3;
        tree.getTree()[4] = 6;
        tree.getTree()[5] = null;
        tree.getTree()[6] = 22;
        tree.getTree()[7] = null;
        tree.getTree()[8] = null;
        tree.getTree()[9] = 5;
        tree.getTree()[10] = 7;
        tree.getTree()[11] = null;
        tree.getTree()[12] = null;
        tree.getTree()[13] = 20;
        tree.getTree()[14] = null;
    }
}
