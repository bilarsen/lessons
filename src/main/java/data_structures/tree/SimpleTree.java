package data_structures.tree;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public record SimpleTree<T>(SimpleTreeNode<T> root) {

    public void addChild(SimpleTreeNode<T> parentNode, SimpleTreeNode<T> newChild) {
        if (parentNode == null)
            return;
        if (parentNode.children == null)
            parentNode.children = new ArrayList<>();
        parentNode.children.add(newChild);
        newChild.parent = parentNode;
    }

    public void deleteNode(SimpleTreeNode<T> nodeToDelete) {
        if (nodeToDelete == null || nodeToDelete.parent == null) return;
        nodeToDelete.parent.children.remove(nodeToDelete);
        if (nodeToDelete.children != null) {
            for (SimpleTreeNode<T> child : nodeToDelete.children)
                addChild(nodeToDelete.parent, child);
            nodeToDelete.children = null;
        }
        nodeToDelete.parent = null;
        nodeToDelete = null;
    }

    public List<SimpleTreeNode<T>> getAllNodes() {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (root != null) getAllNodes(root, nodes);
        return nodes;
    }

    public List<SimpleTreeNode<T>> findNodesByValue(T val) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (root != null) findNodesByValue(root, val, nodes);
        return nodes;
    }

    public void moveNode(SimpleTreeNode<T> originalNode, SimpleTreeNode<T> newParent) {
        if (originalNode == null || originalNode == root || newParent == null || newParent == originalNode) return;
        if (originalNode.parent != null) originalNode.parent.children.remove(originalNode);
        addChild(newParent, originalNode);
    }

    public int count() {
        if (root == null) return 0;
        else if (root.children == null) return 1;
        return countNodes(root);
    }

    public int leafCount() {
        if (root == null) return 0;
        return countLeaves(root);
    }

    public List<T> evenTrees() {
        List<T> nodePairs = new ArrayList<>();
        if (root != null && count() % 2 == 0)
            findEdgesToRemove(root, nodePairs);
        return nodePairs;
    }

    private void getAllNodes(SimpleTreeNode<T> root, List<SimpleTreeNode<T>> nodes) {
        if (root.children == null) {
            nodes.add(root);
            return;
        }
        for (SimpleTreeNode<T> node : root.children)
            getAllNodes(node, nodes);
        nodes.add(root);
    }

    private void findNodesByValue(SimpleTreeNode<T> root, T value, List<SimpleTreeNode<T>> nodes) {
        if (root.value.equals(value)) nodes.add(root);
        if (root.children == null) return;
        for (SimpleTreeNode<T> child : root.children)
            findNodesByValue(child, value, nodes);
    }

    private int countNodes(SimpleTreeNode<T> root) {
        int nodes = 1;
        if (root.children != null) {
            for (SimpleTreeNode<T> child : root.children)
                nodes += countNodes(child);
        }
        return nodes;
    }

    private int countLeaves(SimpleTreeNode<T> root) {
        if (root.children == null || root.children.isEmpty()) return 1;
        int leaves = 0;
        for (SimpleTreeNode<T> child : root.children) {
            leaves += countLeaves(child);
        }
        return leaves;
    }

    private void findEdgesToRemove(SimpleTreeNode<T> root, List<T> nodePairs) {
        if (root.children != null) {
            for (SimpleTreeNode<T> child : root.children) {
                if (countNodes(child) % 2 == 0) {
                    nodePairs.add(child.parent.value);
                    nodePairs.add(child.value);
                }
                findEdgesToRemove(child, nodePairs);
            }
        }
    }

    @Getter
    public static class SimpleTreeNode<T> {

        private final T value;

        private SimpleTreeNode<T> parent;

        private List<SimpleTreeNode<T>> children;

        public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
            value = val;
            this.parent = parent;
            children = null;
        }
    }
}
