import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private BST<Integer> tree;

    @Test
    public void testFindNodeByKey() {
        BSTNode<Integer> root = new BSTNode<>(9, 9, null);
        BSTNode<Integer> node4 = new BSTNode<>(4, 4, root);
        BSTNode<Integer> node17 = new BSTNode<>(17, 17, root);
        BSTNode<Integer> node3 = new BSTNode<>(3, 3, node4);
        BSTNode<Integer> node6 = new BSTNode<>(6, 6, node4);
        BSTNode<Integer> node22 = new BSTNode<>(22, 22, node17);
        BSTNode<Integer> node5 = new BSTNode<>(5, 5, node6);
        BSTNode<Integer> node7 = new BSTNode<>(7, 7, node6);
        BSTNode<Integer> node20 = new BSTNode<>(20, 20, node22);
        root.LeftChild = node4;
        root.RightChild = node17;
        node4.LeftChild = node3;
        node4.RightChild = node6;
        node6.LeftChild = node5;
        node6.RightChild = node7;
        node17.RightChild = node22;
        node22.LeftChild = node20;

        BSTFind<Integer> bstFind;

        tree = new BST<>(null);
        bstFind = tree.FindNodeByKey(9);
        Assert.assertNull(bstFind.Node);
        Assert.assertFalse(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);

        tree = new BST<>(root);

        bstFind = tree.FindNodeByKey(3);
        Assert.assertEquals(3,bstFind.Node.NodeKey);
        Assert.assertTrue(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);

        bstFind = tree.FindNodeByKey(1);
        Assert.assertEquals(3,bstFind.Node.NodeKey);
        Assert.assertFalse(bstFind.NodeHasKey);
        Assert.assertTrue(bstFind.ToLeft);

        BSTNode<Integer> node1 = new BSTNode<>(1, 1, node3);
        node3.LeftChild = node1;
        bstFind = tree.FindNodeByKey(1);
        Assert.assertEquals(1,bstFind.Node.NodeKey);
        Assert.assertTrue(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);

        bstFind = tree.FindNodeByKey(16);
        Assert.assertEquals(17,bstFind.Node.NodeKey);
        Assert.assertFalse(bstFind.NodeHasKey);
        Assert.assertTrue(bstFind.ToLeft);

        BSTNode<Integer> node16 = new BSTNode<>(16, 16, node17);
        node17.LeftChild = node16;
        bstFind = tree.FindNodeByKey(16);
        Assert.assertEquals(16,bstFind.Node.NodeKey);
        Assert.assertTrue(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);

        bstFind = tree.FindNodeByKey(23);
        Assert.assertEquals(22,bstFind.Node.NodeKey);
        Assert.assertFalse(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);

        BSTNode<Integer> node23 = new BSTNode<>(23, 23, node22);
        node22.RightChild = node23;
        bstFind = tree.FindNodeByKey(23);
        Assert.assertEquals(23,bstFind.Node.NodeKey);
        Assert.assertTrue(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);
    }

    @Test
    public void testAddKeyValue() {
        BSTFind<Integer> bstFind;

        tree = new BST<>(null);
        Assert.assertTrue(tree.AddKeyValue(9, 9));
        bstFind = tree.FindNodeByKey(9);
        Assert.assertEquals(9, bstFind.Node.NodeKey);
        Assert.assertNull(bstFind.Node.Parent);
        Assert.assertNull(bstFind.Node.LeftChild);
        Assert.assertNull(bstFind.Node.RightChild);
        Assert.assertEquals(bstFind.Node.NodeKey, tree.Root.NodeKey);

        tree.AddKeyValue(4, 4);
        bstFind = tree.FindNodeByKey(4);
        Assert.assertEquals(4, tree.Root.LeftChild.NodeKey);
        Assert.assertEquals(tree.Root, bstFind.Node.Parent);

        tree.AddKeyValue(17, 17);
        bstFind = tree.FindNodeByKey(17);
        Assert.assertEquals(17, tree.Root.RightChild.NodeKey);
        Assert.assertEquals(tree.Root, bstFind.Node.Parent);

        tree.AddKeyValue(3, 3);
        bstFind = tree.FindNodeByKey(3);
        Assert.assertEquals(4, bstFind.Node.Parent.NodeKey);
        Assert.assertEquals(bstFind.Node, bstFind.Node.Parent.LeftChild);

        tree.AddKeyValue(6, 6);
        bstFind = tree.FindNodeByKey(6);
        Assert.assertEquals(4, bstFind.Node.Parent.NodeKey);
        Assert.assertEquals(bstFind.Node, bstFind.Node.Parent.RightChild);

        tree.AddKeyValue(5, 5);
        bstFind = tree.FindNodeByKey(5);
        Assert.assertEquals(6, bstFind.Node.Parent.NodeKey);
        Assert.assertEquals(bstFind.Node, bstFind.Node.Parent.LeftChild);

        tree.AddKeyValue(7, 7);
        bstFind = tree.FindNodeByKey(7);
        Assert.assertEquals(6, bstFind.Node.Parent.NodeKey);
        Assert.assertEquals(bstFind.Node, bstFind.Node.Parent.RightChild);

        tree.AddKeyValue(22, 22);
        bstFind = tree.FindNodeByKey(22);
        Assert.assertEquals(17, bstFind.Node.Parent.NodeKey);
        Assert.assertEquals(bstFind.Node, bstFind.Node.Parent.RightChild);

        tree.AddKeyValue(20, 20);
        bstFind = tree.FindNodeByKey(20);
        Assert.assertEquals(22, bstFind.Node.Parent.NodeKey);
        Assert.assertEquals(bstFind.Node, bstFind.Node.Parent.LeftChild);

        Assert.assertFalse(tree.AddKeyValue(20, 20));
        Assert.assertFalse(tree.AddKeyValue(20, 1));
        Assert.assertFalse(tree.AddKeyValue(9, null));
        Assert.assertFalse(tree.AddKeyValue(6, 6));
    }

    @Test
    public void testFinMinMax() {
        tree = new BST<>(null);
        Assert.assertNull(tree.FinMinMax(tree.Root, true));
        Assert.assertNull(tree.FinMinMax(tree.Root, false));

        tree = fillTree();
        Assert.assertEquals(3, tree.FinMinMax(tree.Root, false).NodeKey);
        Assert.assertEquals(22, tree.FinMinMax(tree.Root, true).NodeKey);

        BSTFind<Integer> bstFind = tree.FindNodeByKey(4);
        Assert.assertEquals(3, tree.FinMinMax(bstFind.Node, false).NodeKey);
        Assert.assertEquals(7, tree.FinMinMax(bstFind.Node, true).NodeKey);

        bstFind = tree.FindNodeByKey(17);
        Assert.assertEquals(17, tree.FinMinMax(bstFind.Node, false).NodeKey);
        Assert.assertEquals(22, tree.FinMinMax(bstFind.Node, true).NodeKey);
    }

    @Test
    public void testDeleteNodeByKey() {
        tree = new BST<>(null);
        Assert.assertFalse(tree.DeleteNodeByKey(0));

        tree = fillTree();
        Assert.assertEquals(9, tree.Count());
        tree.DeleteNodeByKey(4);
        Assert.assertEquals(8, tree.Count());
        BSTFind<Integer> bstFind = tree.FindNodeByKey(4);
        Assert.assertEquals(3, bstFind.Node.NodeKey);
        Assert.assertFalse(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);
        Assert.assertEquals(5, tree.Root.LeftChild.NodeKey);
        bstFind = tree.FindNodeByKey(5);
        Assert.assertEquals(5, bstFind.Node.NodeKey);
        Assert.assertTrue(bstFind.NodeHasKey);
        Assert.assertFalse(bstFind.ToLeft);

        tree.DeleteNodeByKey(9);
        Assert.assertEquals(7, tree.Count());
        Assert.assertEquals(17, tree.Root.NodeKey);
        Assert.assertEquals(22, tree.Root.RightChild.NodeKey);
        Assert.assertEquals(5, tree.Root.LeftChild.NodeKey);
    }

    private BST<Integer> fillTree() {
        BSTNode<Integer> root = new BSTNode<>(9, 9, null);
        tree = new BST<>(root);
        tree .AddKeyValue(4, 4);
        tree .AddKeyValue(3, 3);
        tree .AddKeyValue(17, 17);
        tree .AddKeyValue(22, 22);
        tree .AddKeyValue(6, 6);
        tree .AddKeyValue(5, 5);
        tree .AddKeyValue(7, 7);
        tree .AddKeyValue(20, 20);
        return tree;
    }
}
