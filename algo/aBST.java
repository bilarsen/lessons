import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей

    private int depth;

    public aBST(int depth) {
        this.depth = depth;
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = (int) Math.pow(this.depth + 1, 2) - 1;
        Tree = new Integer[tree_size > 0 ? tree_size : 1];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key) {
        // ищем в массиве индекс ключа
        int level = 0;
        int index = 0;
        while (level <= depth) {
            if (Tree[index] == null) return -index;
            else if (Tree[index] == key) return index;
            if (key < Tree[index]) index = index * 2 + 1;
            else index = index * 2 + 2;
            level++;
        }
        return null; // не найден
    }

    public int AddKey(int key) {
        // добавляем ключ в массив
        Integer index = FindKeyIndex(key);
        if (index == null) return -1;
        index = Math.abs(index);
        Tree[index] = key;
        return index;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }
}