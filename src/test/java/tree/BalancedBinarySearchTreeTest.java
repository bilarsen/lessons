package tree;

import data_structures.tree.BalancedBST;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BalancedBinarySearchTreeTest {

    private BalancedBST tree;

    @Test
    @DisplayName("generating a tree")
    void testGenerateTree() {
        tree = new BalancedBST();
        int[] array = new int[]{2, 3, 1};
        tree.generateTree(array);
        assertEquals(1, tree.getRoot().getLevel());
        assertEquals(2, tree.getRoot().getLeft().getLevel());
        assertEquals(2, tree.getRoot().getRight().getLevel());
        int[] expected = new int[]{2, 1, 3};
        int[] actual = treeToArray(tree.getRoot());
        assertArrayEquals(expected, actual);

        array = new int[]{4, 2, 1, 3};
        tree = new BalancedBST();
        tree.generateTree(array);
        expected = new int[]{3, 2, 4, 1};
        actual = treeToArray(tree.getRoot());
        assertArrayEquals(expected, actual);

        array = new int[]{5, 1, 3, 2, 4, 7, 6};
        tree = new BalancedBST();
        tree.generateTree(array);
        expected = new int[]{4, 2, 6, 1, 3, 5, 7};
        actual = treeToArray(tree.getRoot());
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("checking if tree is balanced")
    void testIsBalanced() {
        tree = new BalancedBST();
        int[] array = new int[]{2, 3, 1};
        tree.generateTree(array);
        assertTrue(tree.isBalanced());

        tree = new BalancedBST();
        array = new int[]{4, 2, 1, 3};
        tree.generateTree(array);
        assertTrue(tree.isBalanced());

        tree = new BalancedBST();
        array = new int[]{5, 1, 3, 2, 4, 7, 6};
        tree.generateTree(array);
        assertTrue(tree.isBalanced());
    }

    private int[] treeToArray(BalancedBST.BSTNode root) {
        List<BalancedBST.BSTNode> list = new ArrayList<>();
        Deque<BalancedBST.BSTNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            BalancedBST.BSTNode node = deque.poll();
            list.add(node);
            if (node.getLeft() != null) deque.offer(node.getLeft());
            if (node.getRight() != null) deque.offer(node.getRight());
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).getKey();
        }
        return array;
    }
}