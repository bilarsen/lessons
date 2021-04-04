import java.util.*;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        // создаём дерево с нуля из неотсортированного массива a
        // ...
        if (a == null || a.length == 0) return;
        int[] array = Arrays.copyOf(a, a.length);
        Arrays.sort(array);
        generateBBST(Root, array, 0, array.length - 1);
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) return false; // сбалансировано ли дерево с корнем root_node
        int leftHeight = getHeight(root_node.LeftChild);
        int rightHeight = getHeight(root_node.RightChild);
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    private BSTNode generateBBST(BSTNode root, int[] array, int start, int end) {
        if (start > end) return null;

        int mid = (start + end + 1) / 2;
        BSTNode node = new BSTNode(array[mid], root);
        if (node.Parent == null) {
            node.Level = 1;
            Root = node;
        } else {
            node.Level = node.Parent.Level + 1;
        }

        node.LeftChild = generateBBST(node, array, start, mid - 1);
        node.RightChild = generateBBST(node, array, mid + 1, end);

        return node;
    }

    private int getHeight(BSTNode root) {
        if (root.LeftChild == null && root.RightChild == null) return root.Level;
        int leftDepth = 0;
        int rightDepth = 0;
        if (root.LeftChild != null) leftDepth = getHeight(root.LeftChild);
        if (root.RightChild != null) rightDepth = getHeight(root.RightChild);
        return Math.max(leftDepth, rightDepth);
    }
}