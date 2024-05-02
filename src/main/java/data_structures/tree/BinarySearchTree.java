package data_structures.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Getter
public class BinarySearchTree<T> {

    private BSTNode<T> root;

    private int nodeCount;

    public BinarySearchTree(BSTNode<T> node) {
        root = node;
        nodeCount = node == null ? 0 : 1;
    }

    public BSTFind<T> findNodeByKey(int key) {
        BSTFind<T> bstFind = new BSTFind<>();
        if (root != null) {
            bstFind.node = findNode(root, key);
            bstFind.nodeFound = bstFind.node.key == key;
            bstFind.addLeft = key < bstFind.node.key;
        }
        return bstFind;
    }

    public boolean addKeyValue(int key, T val) {
        BSTFind<T> bstFind = findNodeByKey(key);
        if (bstFind.nodeFound)
            return false;
        BSTNode<T> bstNode = new BSTNode<>(key, val, bstFind.node);
        if (bstFind.node == null)
            root = bstNode;
        else {
            bstFind.node.left = bstFind.addLeft ? bstNode : bstFind.node.left;
            bstFind.node.right = !bstFind.addLeft ? bstNode : bstFind.node.right;
        }
        nodeCount++;
        return true;
    }

    public BSTNode<T> findMinMaxKey(BSTNode<T> from, boolean findMax) {
        if (from == null) return null;
        if (findMax) return findMaxNode(from);
        return findMinNode(from);
    }

    public boolean deleteNodeByKey(int key) {
        BSTFind<T> bstFind = findNodeByKey(key);
        if (!bstFind.nodeFound) return false;
        root = remove(root, key);
        nodeCount--;
        return true;
    }

    public List<BSTNode<T>> flattenAllNodes() {
        List<BSTNode<T>> nodes = new ArrayList<>();
        if (root != null) breadthFirstSearch(root, nodes);
        return nodes;
    }

    public List<BSTNode<T>> deepenAllNodes(int order) {
        List<BSTNode<T>> nodes = new ArrayList<>();
        if (root != null) {
            switch (order) {
                case 0:
                    inOrderDFS(root, nodes);
                    break;
                case 1:
                    postOrderDFS(root, nodes);
                    break;
                case 2:
                    preOrderDFS(root, nodes);
                    break;
                default:
                    return nodes;
            }
        }
        return nodes;
    }

    private void breadthFirstSearch(BSTNode<T> root, List<BSTNode<T>> nodes) {
        Deque<BSTNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            nodes.add(node);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    private void inOrderDFS(BSTNode<T> root, List<BSTNode<T>> nodes) {
        if (root == null) return;
        inOrderDFS(root.left, nodes);
        nodes.add(root);
        inOrderDFS(root.right, nodes);
    }

    private void postOrderDFS(BSTNode<T> root, List<BSTNode<T>> nodes) {
        if (root == null) return;
        postOrderDFS(root.left, nodes);
        postOrderDFS(root.right, nodes);
        nodes.add(root);
    }

    private void preOrderDFS(BSTNode<T> root, List<BSTNode<T>> nodes) {
        if (root == null) return;
        nodes.add(root);
        preOrderDFS(root.left, nodes);
        preOrderDFS(root.right, nodes);
    }

    private BSTNode<T> findNode(BSTNode<T> root, int key) {
        if (root == null || root.key == key) return root;
        BSTNode<T> node;
        if (key < root.key) node = findNode(root.left, key);
        else node = findNode(root.right, key);
        return node == null ? root : node;
    }

    private BSTNode<T> findMinNode(BSTNode<T> root) {
        if (root.left == null) return root;
        return findMinNode(root.left);
    }

    private BSTNode<T> findMaxNode(BSTNode<T> root) {
        if (root.right == null) return root;
        return findMaxNode(root.right);
    }

    private BSTNode<T> remove(BSTNode<T> root, int key) {
        if (root == null) return null;
        if (key < root.key) root.left = remove(root.left, key);
        else if (key > root.key) root.right = remove(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                BSTNode<T> node = findMinNode(root.right);
                root.key = node.key;
                root.value = node.value;
                root.right = remove(root.right, node.key);
            }
        }
        return root;
    }

    @Getter
    public static class BSTNode<T> {

        private final BSTNode<T> parent;

        private int key;

        private T value;

        @Setter
        private BSTNode<T> left;

        @Setter
        private BSTNode<T> right;

        public BSTNode(int key, T value, BSTNode<T> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            left = null;
            right = null;
        }
    }

    @Getter
    public static class BSTFind<T> {

        private BSTNode<T> node;

        private boolean nodeFound;

        private boolean addLeft;

    }
}
