import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleTreeTest {

    private SimpleTree<Integer> simpleTree;


    private SimpleTree<Integer> getSimpleTree() {
        return new SimpleTree<>(new SimpleTreeNode<>(9, null));
    }

    private SimpleTree<Integer> fillSimpleTree() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node17 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node22 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node20 = new SimpleTreeNode<>(20, null);

        simpleTree.AddChild(root, node4);
        simpleTree.AddChild(root, node17);
        simpleTree.AddChild(node4, node3);
        simpleTree.AddChild(node4, node6);
        simpleTree.AddChild(node6, node5);
        simpleTree.AddChild(node6, node7);
        simpleTree.AddChild(node17, node22);
        simpleTree.AddChild(node22, node20);

        return simpleTree;
    }

    @Test
    public void testAddChild() {
        simpleTree = getSimpleTree();
        Assert.assertEquals(Integer.valueOf(9), simpleTree.Root.NodeValue);
        Assert.assertNull(simpleTree.Root.Parent);
        Assert.assertNull(simpleTree.Root.Children);

        simpleTree.AddChild(simpleTree.Root, new SimpleTreeNode<>(4, simpleTree.Root));
        simpleTree.AddChild(simpleTree.Root, new SimpleTreeNode<>(17, null));
        int[] expected = new int[]{4, 17};
        Assert.assertArrayEquals(expected, getChildren(simpleTree.Root));
        Assert.assertEquals(Integer.valueOf(4), simpleTree.Root.Children.get(0).NodeValue);
        Assert.assertEquals(Integer.valueOf(17), simpleTree.Root.Children.get(1).NodeValue);
    }

    @Test
    public void testDeleteNode() {
        simpleTree = getSimpleTree();
        simpleTree.DeleteNode(simpleTree.Root);
        Assert.assertEquals(Integer.valueOf(9), simpleTree.Root.NodeValue);

        simpleTree.AddChild(simpleTree.Root, new SimpleTreeNode<>(4, simpleTree.Root));
        SimpleTreeNode<Integer> node17 = new SimpleTreeNode<>(17, simpleTree.Root);
        simpleTree.AddChild(simpleTree.Root, node17);
        SimpleTreeNode<Integer> node22 = new SimpleTreeNode<>(22, node17);
        simpleTree.AddChild(node17, node22);
        SimpleTreeNode<Integer> node20 = new SimpleTreeNode<>(20, node22);
        simpleTree.AddChild(node22, node20);

        simpleTree.DeleteNode(node17);
        Assert.assertEquals(Integer.valueOf(4), simpleTree.Root.Children.get(0).NodeValue);
        Assert.assertEquals(Integer.valueOf(22), simpleTree.Root.Children.get(1).NodeValue);
        Assert.assertEquals(node22, simpleTree.Root.Children.get(1));
        Assert.assertEquals(Integer.valueOf(20), simpleTree.Root.Children.get(1).Children.get(0).NodeValue);
        Assert.assertEquals(node20, simpleTree.Root.Children.get(1).Children.get(0));
    }

    @Test
    public void testGetAllNodes() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        List<SimpleTreeNode<Integer>> expected = new ArrayList<>();
        expected.add(root);
        simpleTree = new SimpleTree<>(root);
        List<SimpleTreeNode<Integer>> actual = simpleTree.GetAllNodes();
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node17 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node22 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node20 = new SimpleTreeNode<>(20, null);

        simpleTree.AddChild(root, node4);
        simpleTree.AddChild(root, node17);
        simpleTree.AddChild(node4, node3);
        simpleTree.AddChild(node4, node6);
        simpleTree.AddChild(node6, node5);
        simpleTree.AddChild(node6, node7);
        simpleTree.AddChild(node17, node22);
        simpleTree.AddChild(node22, node20);

        expected.add(node3);
        expected.add(node5);
        expected.add(node7);
        expected.add(node6);
        expected.add(node4);
        expected.add(node20);
        expected.add(node22);
        expected.add(node17);

        actual = simpleTree.GetAllNodes();
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    public void testFindNodesByValue() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        List<SimpleTreeNode<Integer>> expected = new ArrayList<>();
        expected.add(root);
        simpleTree = new SimpleTree<>(root);
        List<SimpleTreeNode<Integer>> actual = simpleTree.FindNodesByValue(9);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        actual = simpleTree.FindNodesByValue(0);
        Assert.assertEquals(0, actual.size());

        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(20, null);
        SimpleTreeNode<Integer> node17 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(20, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node22 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node20 = new SimpleTreeNode<>(20, null);

        simpleTree.AddChild(root, node4);
        simpleTree.AddChild(root, node17);
        simpleTree.AddChild(node4, node3);
        simpleTree.AddChild(node4, node6);
        simpleTree.AddChild(node6, node5);
        simpleTree.AddChild(node6, node7);
        simpleTree.AddChild(node17, node22);
        simpleTree.AddChild(node22, node20);

        actual = simpleTree.FindNodesByValue(20);
        expected.remove(root);
        expected.add(node4);
        expected.add(node6);
        expected.add(node20);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    public void testMoveNode() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node17 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node22 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> node20 = new SimpleTreeNode<>(20, null);

        simpleTree.AddChild(root, node4);
        simpleTree.AddChild(root, node17);
        simpleTree.AddChild(node4, node3);
        simpleTree.AddChild(node4, node6);
        simpleTree.AddChild(node6, node5);
        simpleTree.AddChild(node6, node7);
        simpleTree.AddChild(node17, node22);
        simpleTree.AddChild(node22, node20);

        simpleTree.MoveNode(node4, node22);
        Assert.assertEquals(1, simpleTree.Root.Children.size());
        Assert.assertEquals(node17, simpleTree.Root.Children.get(0));
        Assert.assertEquals(2, node22.Children.size());
        Assert.assertEquals(node4, node22.Children.get(1));
        Assert.assertTrue(node4.Parent == node22);
        Assert.assertTrue(node3.Parent == node4);
        Assert.assertTrue(node6.Parent == node4);
        Assert.assertTrue(node5.Parent == node6);
        Assert.assertTrue(node7.Parent == node6);
    }

    @Test
    public void testCount() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);
        Assert.assertEquals(1, simpleTree.Count());

        simpleTree = fillSimpleTree();
        Assert.assertEquals(9, simpleTree.Count());
    }

    @Test
    public void testLeafCount() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);
        Assert.assertEquals(1, simpleTree.LeafCount());

        simpleTree = fillSimpleTree();
        Assert.assertEquals(4, simpleTree.LeafCount());

        SimpleTreeNode<Integer> node6 = simpleTree.FindNodesByValue(6).get(0);
        Assert.assertNotNull(node6);
        Assert.assertEquals(Integer.valueOf(6), node6.NodeValue);
        simpleTree.DeleteNode(node6);
        Assert.assertEquals(0, simpleTree.FindNodesByValue(6).size());
        Assert.assertEquals(4, simpleTree.LeafCount());

        SimpleTreeNode<Integer> node7 = simpleTree.FindNodesByValue(7).get(0);
        Assert.assertNotNull(node7);
        Assert.assertEquals(Integer.valueOf(7), node7.NodeValue);
        simpleTree.DeleteNode(node7);
        Assert.assertEquals(0, simpleTree.FindNodesByValue(7).size());
        Assert.assertEquals(3, simpleTree.LeafCount());
    }

    @Test
    public void testLeafCountAfterNodeDeletion() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(9, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> n1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> n2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> n3 = new SimpleTreeNode<>(3, null);
        simpleTree.AddChild(root, n1);
        simpleTree.AddChild(root, n2);
        simpleTree.AddChild(root, n3);

        SimpleTreeNode<Integer> n12 = new SimpleTreeNode<>(12, null);
        SimpleTreeNode<Integer> n13 = new SimpleTreeNode<>(13, null);
        simpleTree.AddChild(n1, n12);
        simpleTree.AddChild(n1, n13);
        Assert.assertEquals(6, simpleTree.Count());

        SimpleTreeNode<Integer> n21 = new SimpleTreeNode<>(21, null);
        simpleTree.AddChild(n2, n21);
        Assert.assertEquals(7, simpleTree.Count());
        Assert.assertEquals(4, simpleTree.LeafCount());

        simpleTree.MoveNode(n2, n3);
        Assert.assertEquals(7, simpleTree.Count());
        Assert.assertEquals(3, simpleTree.LeafCount());

        simpleTree.DeleteNode(n21);
        Assert.assertEquals(6, simpleTree.Count());
        Assert.assertEquals(3, simpleTree.LeafCount());
    }

    @Test
    public void testEvenTrees() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        simpleTree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);

        simpleTree.AddChild(root, node2);
        simpleTree.AddChild(root, node3);
        simpleTree.AddChild(root, node6);
        simpleTree.AddChild(node2, node5);
        simpleTree.AddChild(node2, node7);
        simpleTree.AddChild(node3, node4);
        simpleTree.AddChild(node6, node8);
        simpleTree.AddChild(node8, node9);
        simpleTree.AddChild(node8, node10);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(3);
        expected.add(1);
        expected.add(6);
        List<Integer> actual = simpleTree.EvenTrees();
        Assert.assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    private int[] getChildren(SimpleTreeNode<Integer> node) {
        int[] children = new int[node.Children.size()];
        int i = 0;
        for (SimpleTreeNode<Integer> child : node.Children) {
            children[i++] = child.NodeValue;
        }
        return children;
    }

    private int[] getValues(List<SimpleTreeNode<Integer>> nodes) {
        int[] values = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            values[i] = nodes.get(i).NodeValue;
        }
        return values;
    }
}
