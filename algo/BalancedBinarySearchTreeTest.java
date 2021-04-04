import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BalancedBinarySearchTreeTest {

    private BalancedBST tree;

    @Test
    public void testGenerateTree() {
        tree = new BalancedBST();
        int[] array = new int[]{2, 3, 1};
        tree.GenerateTree(array);
        Assert.assertEquals(1, tree.Root.Level);
        Assert.assertEquals(2, tree.Root.LeftChild.Level);
        Assert.assertEquals(2, tree.Root.RightChild.Level);
        int[] expected = new int[]{2, 1, 3};
        int[] actual = treeToArray(tree.Root);
        Assert.assertArrayEquals(expected, actual);

        array = new int[]{4, 2, 1, 3};
        tree = new BalancedBST();
        tree.GenerateTree(array);
        expected = new int[]{3, 2, 4, 1};
        actual = treeToArray(tree.Root);
        Assert.assertArrayEquals(expected, actual);

        array = new int[]{5, 1, 3, 2, 4, 7, 6};
        tree = new BalancedBST();
        tree.GenerateTree(array);
        expected = new int[]{4, 2, 6, 1, 3, 5, 7};
        actual = treeToArray(tree.Root);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testIsBalanced() {
        tree = new BalancedBST();
        int[] array = new int[]{2, 3, 1};
        tree.GenerateTree(array);
        Assert.assertTrue(tree.IsBalanced(tree.Root));

        tree = new BalancedBST();
        array = new int[]{4, 2, 1, 3};
        tree.GenerateTree(array);
        Assert.assertTrue(tree.IsBalanced(tree.Root));

        tree = new BalancedBST();
        array = new int[]{5, 1, 3, 2, 4, 7, 6};
        tree.GenerateTree(array);
        Assert.assertTrue(tree.IsBalanced(tree.Root));
    }

    private int[] treeToArray(BSTNode root) {
        List<BSTNode> list = new ArrayList<>();
        ArrayDeque<BSTNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            BSTNode node = deque.poll();
            list.add(node);
            if (node.LeftChild != null) deque.offer(node.LeftChild);
            if (node.RightChild != null) deque.offer(node.RightChild);
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).NodeKey;
        }
        return array;
    }
}