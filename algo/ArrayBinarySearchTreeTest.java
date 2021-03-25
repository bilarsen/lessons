import org.junit.Assert;
import org.junit.Test;

public class ArrayBinarySearchTreeTest {

    private aBST tree;

    @Test
    public void testFindKeyIndex() {
        tree = new aBST(0);
        Assert.assertEquals(1, tree.Tree.length);
        Assert.assertNull(tree.Tree[0]);
        Assert.assertEquals(Integer.valueOf(0), tree.FindKeyIndex(100500));

        fillTree();
        Assert.assertEquals(15, tree.Tree.length);
        Assert.assertEquals(Integer.valueOf(6), tree.FindKeyIndex(22));
        Assert.assertNull(tree.FindKeyIndex(8));
        Assert.assertEquals(Integer.valueOf(-14), tree.FindKeyIndex(32));
    }

    @Test
    public void testAddKey() {
        tree = new aBST(0);
        tree.AddKey(100500);
        Assert.assertEquals(Integer.valueOf(100500), tree.Tree[0]);

        tree = new aBST(3);
        tree.AddKey(9);
        tree.AddKey(4);
        tree.AddKey(17);
        tree.AddKey(3);
        tree.AddKey(6);
        tree.AddKey(5);
        tree.AddKey(7);
        tree.AddKey(22);
        tree.AddKey(20);

        Assert.assertEquals(Integer.valueOf(9), tree.Tree[0]);
        Assert.assertEquals(Integer.valueOf(4), tree.Tree[1]);
        Assert.assertEquals(Integer.valueOf(17), tree.Tree[2]);
        Assert.assertEquals(Integer.valueOf(3), tree.Tree[3]);
        Assert.assertEquals(Integer.valueOf(6), tree.Tree[4]);
        Assert.assertEquals(null, tree.Tree[5]);
        Assert.assertEquals(Integer.valueOf(22), tree.Tree[6]);
        Assert.assertEquals(null, tree.Tree[7]);
        Assert.assertEquals(null, tree.Tree[8]);
        Assert.assertEquals(Integer.valueOf(5), tree.Tree[9]);
        Assert.assertEquals(Integer.valueOf(7), tree.Tree[10]);
        Assert.assertEquals(null, tree.Tree[11]);
        Assert.assertEquals(null, tree.Tree[12]);
        Assert.assertEquals(Integer.valueOf(20), tree.Tree[13]);
        Assert.assertEquals(null, tree.Tree[14]);

        tree.AddKey(32);
        Assert.assertEquals(Integer.valueOf(32), tree.Tree[14]);
    }

    private void fillTree() {
        tree = new aBST(3);
        tree.Tree[0] = 9;
        tree.Tree[1] = 4;
        tree.Tree[2] = 17;
        tree.Tree[3] = 3;
        tree.Tree[4] = 6;
        tree.Tree[5] = null;
        tree.Tree[6] = 22;
        tree.Tree[7] = null;
        tree.Tree[8] = null;
        tree.Tree[9] = 5;
        tree.Tree[10] = 7;
        tree.Tree[11] = null;
        tree.Tree[12] = null;
        tree.Tree[13] = 20;
        tree.Tree[14] = null;
    }
}
