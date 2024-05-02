package tree;

import data_structures.tree.BinarySearchTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    @Test
    @DisplayName("finding node by key")
    void testFindNodeByKey() {
        BinarySearchTree.BSTNode<Integer> root = new BinarySearchTree.BSTNode<>(9, 9, null);
        BinarySearchTree.BSTNode<Integer> node4 = new BinarySearchTree.BSTNode<>(4, 4, root);
        BinarySearchTree.BSTNode<Integer> node17 = new BinarySearchTree.BSTNode<>(17, 17, root);
        BinarySearchTree.BSTNode<Integer> node3 = new BinarySearchTree.BSTNode<>(3, 3, node4);
        BinarySearchTree.BSTNode<Integer> node6 = new BinarySearchTree.BSTNode<>(6, 6, node4);
        BinarySearchTree.BSTNode<Integer> node22 = new BinarySearchTree.BSTNode<>(22, 22, node17);
        BinarySearchTree.BSTNode<Integer> node5 = new BinarySearchTree.BSTNode<>(5, 5, node6);
        BinarySearchTree.BSTNode<Integer> node7 = new BinarySearchTree.BSTNode<>(7, 7, node6);
        BinarySearchTree.BSTNode<Integer> node20 = new BinarySearchTree.BSTNode<>(20, 20, node22);
        root.setLeft(node4);
        root.setRight(node17);
        node4.setLeft(node3);
        node4.setRight(node6);
        node6.setLeft(node5);
        node6.setRight(node7);
        node17.setRight(node22);
        node22.setLeft(node20);

        BinarySearchTree.BSTFind<Integer> bstFind;

        tree = new BinarySearchTree<>(null);
        bstFind = tree.findNodeByKey(9);
        assertNull(bstFind.getNode());
        assertFalse(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());

        tree = new BinarySearchTree<>(root);

        bstFind = tree.findNodeByKey(3);
        assertEquals(3, bstFind.getNode().getKey());
        assertTrue(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());

        bstFind = tree.findNodeByKey(1);
        assertEquals(3, bstFind.getNode().getKey());
        assertFalse(bstFind.isNodeFound());
        assertTrue(bstFind.isAddLeft());

        BinarySearchTree.BSTNode<Integer> node1 = new BinarySearchTree.BSTNode<>(1, 1, node3);
        node3.setLeft(node1);
        bstFind = tree.findNodeByKey(1);
        assertEquals(1, bstFind.getNode().getKey());
        assertTrue(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());

        bstFind = tree.findNodeByKey(16);
        assertEquals(17, bstFind.getNode().getKey());
        assertFalse(bstFind.isNodeFound());
        assertTrue(bstFind.isAddLeft());

        BinarySearchTree.BSTNode<Integer> node16 = new BinarySearchTree.BSTNode<>(16, 16, node17);
        node17.setLeft(node16);
        bstFind = tree.findNodeByKey(16);
        assertEquals(16, bstFind.getNode().getKey());
        assertTrue(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());

        bstFind = tree.findNodeByKey(23);
        assertEquals(22, bstFind.getNode().getKey());
        assertFalse(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());

        BinarySearchTree.BSTNode<Integer> node23 = new BinarySearchTree.BSTNode<>(23, 23, node22);
        node22.setRight(node23);
        bstFind = tree.findNodeByKey(23);
        assertEquals(23, bstFind.getNode().getKey());
        assertTrue(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());
    }

    @Test
    @DisplayName("adding key/value")
    void testAddKeyValue() {
        BinarySearchTree.BSTFind<Integer> bstFind;

        tree = new BinarySearchTree<>(null);
        assertTrue(tree.addKeyValue(9, 9));
        bstFind = tree.findNodeByKey(9);
        assertEquals(9, bstFind.getNode().getKey());
        assertNull(bstFind.getNode().getParent());
        assertNull(bstFind.getNode().getLeft());
        assertNull(bstFind.getNode().getRight());
        assertEquals(bstFind.getNode().getKey(), tree.getRoot().getKey());

        tree.addKeyValue(4, 4);
        bstFind = tree.findNodeByKey(4);
        assertEquals(4, tree.getRoot().getLeft().getKey());
        assertEquals(tree.getRoot(), bstFind.getNode().getParent());

        tree.addKeyValue(17, 17);
        bstFind = tree.findNodeByKey(17);
        assertEquals(17, tree.getRoot().getRight().getKey());
        assertEquals(tree.getRoot(), bstFind.getNode().getParent());

        tree.addKeyValue(3, 3);
        bstFind = tree.findNodeByKey(3);
        assertEquals(4, bstFind.getNode().getParent().getKey());
        assertEquals(bstFind.getNode(), bstFind.getNode().getParent().getLeft());

        tree.addKeyValue(6, 6);
        bstFind = tree.findNodeByKey(6);
        assertEquals(4, bstFind.getNode().getParent().getKey());
        assertEquals(bstFind.getNode(), bstFind.getNode().getParent().getRight());

        tree.addKeyValue(5, 5);
        bstFind = tree.findNodeByKey(5);
        assertEquals(6, bstFind.getNode().getParent().getKey());
        assertEquals(bstFind.getNode(), bstFind.getNode().getParent().getLeft());

        tree.addKeyValue(7, 7);
        bstFind = tree.findNodeByKey(7);
        assertEquals(6, bstFind.getNode().getParent().getKey());
        assertEquals(bstFind.getNode(), bstFind.getNode().getParent().getRight());

        tree.addKeyValue(22, 22);
        bstFind = tree.findNodeByKey(22);
        assertEquals(17, bstFind.getNode().getParent().getKey());
        assertEquals(bstFind.getNode(), bstFind.getNode().getParent().getRight());

        tree.addKeyValue(20, 20);
        bstFind = tree.findNodeByKey(20);
        assertEquals(22, bstFind.getNode().getParent().getKey());
        assertEquals(bstFind.getNode(), bstFind.getNode().getParent().getLeft());

        assertFalse(tree.addKeyValue(20, 20));
        assertFalse(tree.addKeyValue(20, 1));
        assertFalse(tree.addKeyValue(9, null));
        assertFalse(tree.addKeyValue(6, 6));
    }

    @Test
    @DisplayName("finding min/max nodes")
    void testFindMinMax() {
        tree = new BinarySearchTree<>(null);
        assertNull(tree.findMinMaxKey(tree.getRoot(), true));
        assertNull(tree.findMinMaxKey(tree.getRoot(), false));

        tree = fillTree();
        assertEquals(3, tree.findMinMaxKey(tree.getRoot(), false).getKey());
        assertEquals(22, tree.findMinMaxKey(tree.getRoot(), true).getKey());

        BinarySearchTree.BSTFind<Integer> bstFind = tree.findNodeByKey(4);
        assertEquals(3, tree.findMinMaxKey(bstFind.getNode(), false).getKey());
        assertEquals(7, tree.findMinMaxKey(bstFind.getNode(), true).getKey());

        bstFind = tree.findNodeByKey(17);
        assertEquals(17, tree.findMinMaxKey(bstFind.getNode(), false).getKey());
        assertEquals(22, tree.findMinMaxKey(bstFind.getNode(), true).getKey());
    }

    @Test
    @DisplayName("deleting node by key")
    void testDeleteNodeByKey() {
        tree = new BinarySearchTree<>(null);
        assertFalse(tree.deleteNodeByKey(0));

        tree = fillTree();
        assertEquals(9, tree.getNodeCount());
        tree.deleteNodeByKey(4);
        assertEquals(8, tree.getNodeCount());
        BinarySearchTree.BSTFind<Integer> bstFind = tree.findNodeByKey(4);
        assertEquals(3, bstFind.getNode().getKey());
        assertFalse(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());
        assertEquals(5, tree.getRoot().getLeft().getKey());
        bstFind = tree.findNodeByKey(5);
        assertEquals(5, bstFind.getNode().getKey());
        assertTrue(bstFind.isNodeFound());
        assertFalse(bstFind.isAddLeft());

        tree.deleteNodeByKey(9);
        assertEquals(7, tree.getNodeCount());
        assertEquals(17, tree.getRoot().getKey());
        assertEquals(22, tree.getRoot().getRight().getKey());
        assertEquals(5, tree.getRoot().getLeft().getKey());
    }

    @Test
    @DisplayName("flattening all nodes")
    void testFlattenAllNodes() {
        tree = new BinarySearchTree<>(null);
        List<BinarySearchTree.BSTNode<Integer>> actual = tree.flattenAllNodes();
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());

        int[] expected = new int[]{9, 4, 17, 3, 6, 22, 5, 7, 20};
        tree = fillTree();
        assertArrayEquals(expected, extractValues(tree.flattenAllNodes()));
    }

    @Test
    @DisplayName("deepening all nodes")
    void testDeepenAllNodes() {
        tree = new BinarySearchTree<>(null);
        List<BinarySearchTree.BSTNode<Integer>> actual = tree.deepenAllNodes(6);
        assertEquals(0, actual.size());
        assertTrue(actual.isEmpty());

        tree = fillTree();
        int[] expected;

        //in-order
        expected = new int[]{3, 4, 5, 6, 7, 9, 17, 20, 22};
        assertArrayEquals(expected, extractValues(tree.deepenAllNodes(0)));

        //post-order
        expected = new int[]{3, 5, 7, 6, 4, 20, 22, 17, 9};
        assertArrayEquals(expected, extractValues(tree.deepenAllNodes(1)));

        //pre-order
        expected = new int[]{9, 4, 3, 6, 5, 7, 17, 22, 20};
        assertArrayEquals(expected, extractValues(tree.deepenAllNodes(2)));
    }

    private BinarySearchTree<Integer> fillTree() {
        BinarySearchTree.BSTNode<Integer> root = new BinarySearchTree.BSTNode<>(9, 9, null);
        tree = new BinarySearchTree<>(root);
        tree.addKeyValue(4, 4);
        tree.addKeyValue(3, 3);
        tree.addKeyValue(17, 17);
        tree.addKeyValue(22, 22);
        tree.addKeyValue(6, 6);
        tree.addKeyValue(5, 5);
        tree.addKeyValue(7, 7);
        tree.addKeyValue(20, 20);
        return tree;
    }

    private int[] extractValues(List<BinarySearchTree.BSTNode<Integer>> nodes) {
        int[] values = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            values[i] = nodes.get(i).getKey();
        }
        return values;
    }
}
