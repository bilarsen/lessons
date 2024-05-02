package data_structures.tree;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class BalancedBST {

    private BSTNode root;

    public void generateTree(int[] a) {
        if (a == null || a.length == 0) return;
        int[] array = Arrays.copyOf(a, a.length);
        Arrays.sort(array);
        generateBBST(root, array, 0, array.length - 1);
    }

    public boolean isBalanced() {
        if (root == null) return false;
        int leftHeight = 0;
        int rightHeight = 0;
        if (root.left != null) leftHeight = getHeight(root.left);
        if (root.right != null) rightHeight = getHeight(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    private BSTNode generateBBST(BSTNode root, int[] array, int start, int end) {
        if (start > end) return null;

        int mid = (start + end + 1) / 2;
        BSTNode node = new BSTNode(array[mid], root);
        if (node.getParent() == null) {
            node.level = 1;
            this.root = node;
        } else {
            node.level = node.parent.level + 1;
        }

        node.left = generateBBST(node, array, start, mid - 1);
        node.right = generateBBST(node, array, mid + 1, end);

        return node;
    }

    private int getHeight(BSTNode root) {
        if (root.left == null && root.right == null) return root.level;
        int leftDepth = 0;
        int rightDepth = 0;
        if (root.left != null) leftDepth = getHeight(root.left);
        if (root.right != null) rightDepth = getHeight(root.right);
        return Math.max(leftDepth, rightDepth);
    }

    @Getter
    public static class BSTNode {

        private final int key;

        private final BSTNode parent;

        private BSTNode left;

        private BSTNode right;

        private int level;

        public BSTNode(int key, BSTNode parent) {
            this.key = key;
            this.parent = parent;
            left = null;
            right = null;
        }
    }

}
