package tree;

import data_structures.tree.SimpleTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleTreeTest {

    private SimpleTree<Integer> simpleTree;


    private SimpleTree<Integer> getSimpleTree() {
        return new SimpleTree<>(new SimpleTree.SimpleTreeNode<>(9, null));
    }

    private SimpleTree<Integer> fillSimpleTree() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTree.SimpleTreeNode<Integer> node4 = new SimpleTree.SimpleTreeNode<>(4, null);
        SimpleTree.SimpleTreeNode<Integer> node17 = new SimpleTree.SimpleTreeNode<>(17, null);
        SimpleTree.SimpleTreeNode<Integer> node3 = new SimpleTree.SimpleTreeNode<>(3, null);
        SimpleTree.SimpleTreeNode<Integer> node6 = new SimpleTree.SimpleTreeNode<>(6, null);
        SimpleTree.SimpleTreeNode<Integer> node5 = new SimpleTree.SimpleTreeNode<>(5, null);
        SimpleTree.SimpleTreeNode<Integer> node7 = new SimpleTree.SimpleTreeNode<>(7, null);
        SimpleTree.SimpleTreeNode<Integer> node22 = new SimpleTree.SimpleTreeNode<>(22, null);
        SimpleTree.SimpleTreeNode<Integer> node20 = new SimpleTree.SimpleTreeNode<>(20, null);

        simpleTree.addChild(root, node4);
        simpleTree.addChild(root, node17);
        simpleTree.addChild(node4, node3);
        simpleTree.addChild(node4, node6);
        simpleTree.addChild(node6, node5);
        simpleTree.addChild(node6, node7);
        simpleTree.addChild(node17, node22);
        simpleTree.addChild(node22, node20);

        return simpleTree;
    }

    @Test
    @DisplayName("adding child")
    void testAddChild() {
        simpleTree = getSimpleTree();
        assertEquals(Integer.valueOf(9), simpleTree.root().getValue());
        assertNull(simpleTree.root().getParent());
        assertNull(simpleTree.root().getChildren());

        simpleTree.addChild(simpleTree.root(), new SimpleTree.SimpleTreeNode<>(4, simpleTree.root()));
        simpleTree.addChild(simpleTree.root(), new SimpleTree.SimpleTreeNode<>(17, null));
        int[] expected = new int[]{4, 17};
        assertArrayEquals(expected, getChildren(simpleTree.root()));
        assertEquals(Integer.valueOf(4), simpleTree.root().getChildren().getFirst().getValue());
        assertEquals(Integer.valueOf(17), simpleTree.root().getChildren().get(1).getValue());
    }

    @Test
    @DisplayName("deleting node")
    void testDeleteNode() {
        simpleTree = getSimpleTree();
        simpleTree.deleteNode(simpleTree.root());
        assertEquals(Integer.valueOf(9), simpleTree.root().getValue());

        simpleTree.addChild(simpleTree.root(), new SimpleTree.SimpleTreeNode<>(4, simpleTree.root()));
        SimpleTree.SimpleTreeNode<Integer> node17 = new SimpleTree.SimpleTreeNode<>(17, simpleTree.root());
        simpleTree.addChild(simpleTree.root(), node17);
        SimpleTree.SimpleTreeNode<Integer> node22 = new SimpleTree.SimpleTreeNode<>(22, node17);
        simpleTree.addChild(node17, node22);
        SimpleTree.SimpleTreeNode<Integer> node20 = new SimpleTree.SimpleTreeNode<>(20, node22);
        simpleTree.addChild(node22, node20);

        simpleTree.deleteNode(node17);
        assertEquals(Integer.valueOf(4), simpleTree.root().getChildren().getFirst().getValue());
        assertEquals(Integer.valueOf(22), simpleTree.root().getChildren().get(1).getValue());
        assertEquals(node22, simpleTree.root().getChildren().get(1));
        assertEquals(Integer.valueOf(20), simpleTree.root().getChildren().get(1).getChildren().getFirst().getValue());
        assertEquals(node20, simpleTree.root().getChildren().get(1).getChildren().getFirst());
    }

    @Test
    @DisplayName("getting all nodes")
    void testGetAllNodes() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        List<SimpleTree.SimpleTreeNode<Integer>> expected = new ArrayList<>();
        expected.add(root);
        simpleTree = new SimpleTree<>(root);
        List<SimpleTree.SimpleTreeNode<Integer>> actual = simpleTree.getAllNodes();
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        SimpleTree.SimpleTreeNode<Integer> node4 = new SimpleTree.SimpleTreeNode<>(4, null);
        SimpleTree.SimpleTreeNode<Integer> node17 = new SimpleTree.SimpleTreeNode<>(17, null);
        SimpleTree.SimpleTreeNode<Integer> node3 = new SimpleTree.SimpleTreeNode<>(3, null);
        SimpleTree.SimpleTreeNode<Integer> node6 = new SimpleTree.SimpleTreeNode<>(6, null);
        SimpleTree.SimpleTreeNode<Integer> node5 = new SimpleTree.SimpleTreeNode<>(5, null);
        SimpleTree.SimpleTreeNode<Integer> node7 = new SimpleTree.SimpleTreeNode<>(7, null);
        SimpleTree.SimpleTreeNode<Integer> node22 = new SimpleTree.SimpleTreeNode<>(22, null);
        SimpleTree.SimpleTreeNode<Integer> node20 = new SimpleTree.SimpleTreeNode<>(20, null);

        simpleTree.addChild(root, node4);
        simpleTree.addChild(root, node17);
        simpleTree.addChild(node4, node3);
        simpleTree.addChild(node4, node6);
        simpleTree.addChild(node6, node5);
        simpleTree.addChild(node6, node7);
        simpleTree.addChild(node17, node22);
        simpleTree.addChild(node22, node20);

        expected.add(node3);
        expected.add(node5);
        expected.add(node7);
        expected.add(node6);
        expected.add(node4);
        expected.add(node20);
        expected.add(node22);
        expected.add(node17);

        actual = simpleTree.getAllNodes();
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("finding nodes by value")
    void testFindNodesByValue() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        List<SimpleTree.SimpleTreeNode<Integer>> expected = new ArrayList<>();
        expected.add(root);
        simpleTree = new SimpleTree<>(root);
        List<SimpleTree.SimpleTreeNode<Integer>> actual = simpleTree.findNodesByValue(9);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        actual = simpleTree.findNodesByValue(0);
        assertEquals(0, actual.size());

        SimpleTree.SimpleTreeNode<Integer> node4 = new SimpleTree.SimpleTreeNode<>(20, null);
        SimpleTree.SimpleTreeNode<Integer> node17 = new SimpleTree.SimpleTreeNode<>(17, null);
        SimpleTree.SimpleTreeNode<Integer> node3 = new SimpleTree.SimpleTreeNode<>(3, null);
        SimpleTree.SimpleTreeNode<Integer> node6 = new SimpleTree.SimpleTreeNode<>(20, null);
        SimpleTree.SimpleTreeNode<Integer> node5 = new SimpleTree.SimpleTreeNode<>(5, null);
        SimpleTree.SimpleTreeNode<Integer> node7 = new SimpleTree.SimpleTreeNode<>(7, null);
        SimpleTree.SimpleTreeNode<Integer> node22 = new SimpleTree.SimpleTreeNode<>(22, null);
        SimpleTree.SimpleTreeNode<Integer> node20 = new SimpleTree.SimpleTreeNode<>(20, null);

        simpleTree.addChild(root, node4);
        simpleTree.addChild(root, node17);
        simpleTree.addChild(node4, node3);
        simpleTree.addChild(node4, node6);
        simpleTree.addChild(node6, node5);
        simpleTree.addChild(node6, node7);
        simpleTree.addChild(node17, node22);
        simpleTree.addChild(node22, node20);

        actual = simpleTree.findNodesByValue(20);
        expected.remove(root);
        expected.add(node4);
        expected.add(node6);
        expected.add(node20);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    @DisplayName("moving node")
    void testMoveNode() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTree.SimpleTreeNode<Integer> node4 = new SimpleTree.SimpleTreeNode<>(4, null);
        SimpleTree.SimpleTreeNode<Integer> node17 = new SimpleTree.SimpleTreeNode<>(17, null);
        SimpleTree.SimpleTreeNode<Integer> node3 = new SimpleTree.SimpleTreeNode<>(3, null);
        SimpleTree.SimpleTreeNode<Integer> node6 = new SimpleTree.SimpleTreeNode<>(6, null);
        SimpleTree.SimpleTreeNode<Integer> node5 = new SimpleTree.SimpleTreeNode<>(5, null);
        SimpleTree.SimpleTreeNode<Integer> node7 = new SimpleTree.SimpleTreeNode<>(7, null);
        SimpleTree.SimpleTreeNode<Integer> node22 = new SimpleTree.SimpleTreeNode<>(22, null);
        SimpleTree.SimpleTreeNode<Integer> node20 = new SimpleTree.SimpleTreeNode<>(20, null);

        simpleTree.addChild(root, node4);
        simpleTree.addChild(root, node17);
        simpleTree.addChild(node4, node3);
        simpleTree.addChild(node4, node6);
        simpleTree.addChild(node6, node5);
        simpleTree.addChild(node6, node7);
        simpleTree.addChild(node17, node22);
        simpleTree.addChild(node22, node20);

        simpleTree.moveNode(node4, node22);
        assertEquals(1, simpleTree.root().getChildren().size());
        assertEquals(node17, simpleTree.root().getChildren().getFirst());
        assertEquals(2, node22.getChildren().size());
        assertEquals(node4, node22.getChildren().get(1));
        assertSame(node4.getParent(), node22);
        assertSame(node3.getParent(), node4);
        assertSame(node6.getParent(), node4);
        assertSame(node5.getParent(), node6);
        assertSame(node7.getParent(), node6);
    }

    @Test
    @DisplayName("counting nodes")
    void testCount() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);
        assertEquals(1, simpleTree.count());

        simpleTree = fillSimpleTree();
        assertEquals(9, simpleTree.count());
    }

    @Test
    @DisplayName("counting leaves")
    void testLeafCount() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);
        assertEquals(1, simpleTree.leafCount());

        simpleTree = fillSimpleTree();
        assertEquals(4, simpleTree.leafCount());

        SimpleTree.SimpleTreeNode<Integer> node6 = simpleTree.findNodesByValue(6).getFirst();
        assertNotNull(node6);
        assertEquals(Integer.valueOf(6), node6.getValue());
        simpleTree.deleteNode(node6);
        assertEquals(0, simpleTree.findNodesByValue(6).size());
        assertEquals(4, simpleTree.leafCount());

        SimpleTree.SimpleTreeNode<Integer> node7 = simpleTree.findNodesByValue(7).getFirst();
        assertNotNull(node7);
        assertEquals(Integer.valueOf(7), node7.getValue());
        simpleTree.deleteNode(node7);
        assertEquals(0, simpleTree.findNodesByValue(7).size());
        assertEquals(3, simpleTree.leafCount());
    }

    @Test
    @DisplayName("counting leaves after node deletion")
    void testLeafCountAfterNodeDeletion() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTree.SimpleTreeNode<Integer> n1 = new SimpleTree.SimpleTreeNode<>(1, null);
        SimpleTree.SimpleTreeNode<Integer> n2 = new SimpleTree.SimpleTreeNode<>(2, null);
        SimpleTree.SimpleTreeNode<Integer> n3 = new SimpleTree.SimpleTreeNode<>(3, null);
        simpleTree.addChild(root, n1);
        simpleTree.addChild(root, n2);
        simpleTree.addChild(root, n3);

        SimpleTree.SimpleTreeNode<Integer> n12 = new SimpleTree.SimpleTreeNode<>(12, null);
        SimpleTree.SimpleTreeNode<Integer> n13 = new SimpleTree.SimpleTreeNode<>(13, null);
        simpleTree.addChild(n1, n12);
        simpleTree.addChild(n1, n13);
        assertEquals(6, simpleTree.count());

        SimpleTree.SimpleTreeNode<Integer> n21 = new SimpleTree.SimpleTreeNode<>(21, null);
        simpleTree.addChild(n2, n21);
        assertEquals(7, simpleTree.count());
        assertEquals(4, simpleTree.leafCount());

        simpleTree.moveNode(n2, n3);
        assertEquals(7, simpleTree.count());
        assertEquals(3, simpleTree.leafCount());

        simpleTree.deleteNode(n21);
        assertEquals(6, simpleTree.count());
        assertEquals(3, simpleTree.leafCount());
    }

    @Test
    @DisplayName("evening trees")
    void testEvenTrees() {
        SimpleTree.SimpleTreeNode<Integer> root = new SimpleTree.SimpleTreeNode<>(1, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTree.SimpleTreeNode<Integer> node2 = new SimpleTree.SimpleTreeNode<>(2, null);
        SimpleTree.SimpleTreeNode<Integer> node3 = new SimpleTree.SimpleTreeNode<>(3, null);
        SimpleTree.SimpleTreeNode<Integer> node6 = new SimpleTree.SimpleTreeNode<>(6, null);
        SimpleTree.SimpleTreeNode<Integer> node5 = new SimpleTree.SimpleTreeNode<>(5, null);
        SimpleTree.SimpleTreeNode<Integer> node7 = new SimpleTree.SimpleTreeNode<>(7, null);
        SimpleTree.SimpleTreeNode<Integer> node4 = new SimpleTree.SimpleTreeNode<>(4, null);
        SimpleTree.SimpleTreeNode<Integer> node8 = new SimpleTree.SimpleTreeNode<>(8, null);
        SimpleTree.SimpleTreeNode<Integer> node9 = new SimpleTree.SimpleTreeNode<>(9, null);
        SimpleTree.SimpleTreeNode<Integer> node10 = new SimpleTree.SimpleTreeNode<>(10, null);

        simpleTree.addChild(root, node2);
        simpleTree.addChild(root, node3);
        simpleTree.addChild(root, node6);
        simpleTree.addChild(node2, node5);
        simpleTree.addChild(node2, node7);
        simpleTree.addChild(node3, node4);
        simpleTree.addChild(node6, node8);
        simpleTree.addChild(node8, node9);
        simpleTree.addChild(node8, node10);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(3);
        expected.add(1);
        expected.add(6);
        List<Integer> actual = simpleTree.evenTrees();
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    private int[] getChildren(SimpleTree.SimpleTreeNode<Integer> node) {
        int[] children = new int[node.getChildren().size()];
        int i = 0;
        for (SimpleTree.SimpleTreeNode<Integer> child : node.getChildren())
            children[i++] = child.getValue();
        return children;
    }

    private int[] getValues(List<SimpleTree.SimpleTreeNode<Integer>> nodes) {
        int[] values = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++)
            values[i] = nodes.get(i).getValue();
        return values;
    }
}
