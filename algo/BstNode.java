import java.io.*;
import java.util.*;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {

    BSTNode<T> Root; // корень дерева, или null

    private int nodeCount;

    public BST(BSTNode<T> node) {
        Root = node;
        nodeCount = node == null ? 0 : 1;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        // ищем в дереве узел и сопутствующую информацию по ключу
        BSTFind<T> bstFind = new BSTFind<>();
        if (Root != null) {
            bstFind.Node = findNode(Root, key);
            bstFind.NodeHasKey = bstFind.Node.NodeKey == key;
            bstFind.ToLeft = key < bstFind.Node.NodeKey;
        }
        return bstFind;
    }

    public boolean AddKeyValue(int key, T val) {
        // добавляем ключ-значение в дерево
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (bstFind.NodeHasKey) return false; // если ключ уже есть
        BSTNode<T> bstNode = new BSTNode<>(key, val, bstFind.Node);
        if (bstFind.Node == null) Root = bstNode;
        else {
            bstFind.Node.LeftChild = bstFind.ToLeft ? bstNode : bstFind.Node.LeftChild;
            bstFind.Node.RightChild = !bstFind.ToLeft ? bstNode : bstFind.Node.RightChild;
        }
        nodeCount++;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        if (FromNode == null) return null;
        if (FindMax) return findMaxNode(FromNode);
        return findMinNode(FromNode);
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (!bstFind.NodeHasKey) return false; // если узел не найден
        Root = remove(Root, key);
        nodeCount--;
        return true;
    }

    public int Count() {
        return nodeCount; // количество узлов в дереве
    }

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> nodes = new ArrayList<>();
        if (Root != null) BFS(Root, nodes);
        return nodes;
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        ArrayList<BSTNode> nodes = new ArrayList<>();
        if (Root != null) {
            switch (order) {
                case 0:
                    inOrderDFS(Root, nodes);
                    break;
                case 1:
                    postOrderDFS(Root, nodes);
                    break;
                case 2:
                    preOrderDFS(Root, nodes);
                    break;
                default:
                    return nodes;
            }
        }
        return nodes;
    }

    private void BFS(BSTNode<T> root, ArrayList<BSTNode> nodes) {
        ArrayDeque<BSTNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            nodes.add(node);
            if (node.LeftChild != null) queue.offer(node.LeftChild);
            if (node.RightChild != null) queue.offer(node.RightChild);
        }
    }

    private void inOrderDFS(BSTNode<T> root, ArrayList<BSTNode> nodes) {
        if (root == null) return;
        inOrderDFS(root.LeftChild, nodes);
        nodes.add(root);
        inOrderDFS(root.RightChild, nodes);
    }

    private void postOrderDFS(BSTNode<T> root, ArrayList<BSTNode> nodes) {
        if (root == null) return;
        postOrderDFS(root.LeftChild, nodes);
        postOrderDFS(root.RightChild, nodes);
        nodes.add(root);
    }

    private void preOrderDFS(BSTNode<T> root, ArrayList<BSTNode> nodes) {
        if (root == null) return;
        nodes.add(root);
        preOrderDFS(root.LeftChild, nodes);
        preOrderDFS(root.RightChild, nodes);
    }

    private BSTNode<T> findNode(BSTNode<T> root, int key) {
        if (root == null || root.NodeKey == key) return root;
        BSTNode<T> node;
        if (key < root.NodeKey) node = findNode(root.LeftChild, key);
        else node = findNode(root.RightChild, key);
        return node == null ? root : node;
    }

    private BSTNode<T> findMinNode(BSTNode<T> root) {
        if (root.LeftChild == null) return root;
        return findMinNode(root.LeftChild);
    }

    private BSTNode<T> findMaxNode(BSTNode<T> root) {
        if (root.RightChild == null) return root;
        return findMaxNode(root.RightChild);
    }

    private BSTNode<T> remove(BSTNode<T> root, int key) {
        if (root == null) return null;
        if (key < root.NodeKey) root.LeftChild = remove(root.LeftChild, key);
        else if (key > root.NodeKey) root.RightChild = remove(root.RightChild, key);
        else {
            if (root.LeftChild == null) return root.RightChild;
            else if (root.RightChild == null) return root.LeftChild;
            else {
                BSTNode<T> node = findMinNode(root.RightChild);
                root.NodeKey = node.NodeKey;
                root.NodeValue = node.NodeValue;
                root.RightChild = remove(root.RightChild, node.NodeKey);
            }
        }
        return root;
    }
}