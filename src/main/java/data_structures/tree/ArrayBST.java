package data_structures.tree;

import lombok.Getter;

@Getter
public class ArrayBST {

    private final Integer[] tree;

    private final int depth;

    public ArrayBST(int depth) {
        this.depth = depth;
        int treeSize = (int) Math.pow(this.depth + 1.0, 2) - 1;
        tree = new Integer[treeSize > 0 ? treeSize : 1];
        for (int i = 0; i < treeSize; i++)
            tree[i] = null;
    }

    public Integer findKeyIndex(int key) {
        int level = 0;
        int index = 0;
        while (level <= depth) {
            if (tree[index] == null) return -index;
            else if (tree[index] == key) return index;
            if (key < tree[index]) index = index * 2 + 1;
            else index = index * 2 + 2;
            level++;
        }
        return null;
    }

    public int addKey(int key) {
        Integer index = findKeyIndex(key);
        if (index == null) return -1;
        index = Math.abs(index);
        tree[index] = key;
        return index;
    }

    public int getArrayLength() {
        return tree.length;
    }
}