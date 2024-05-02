package data_structures;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Heap {

    private int[] heapArray;

    private int emptySlot;

    public Heap() {
        heapArray = null;
        emptySlot = 0;
    }

    public void makeHeap(int[] array, int depth) {
        int size = (int) Math.pow(2, depth + 1) - 1;
        heapArray = new int[size];
        Arrays.fill(heapArray, -1);
        for (int num : array) add(num);
    }

    public int getMax() {
        if (emptySlot == 0) return -1;
        int root = heapArray[0];
        swap(0, emptySlot - 1);
        heapArray[emptySlot - 1] = -1;
        emptySlot--;
        shuffleDown(0);
        return root;
    }

    public boolean add(int key) {
        if (emptySlot == heapArray.length) return false;
        heapArray[emptySlot] = key;
        shuffleUp(emptySlot++);
        return true;
    }

    private void shuffleDown(int index) {
        int parent = (index - 1) / 2;
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        if (leftChild >= heapArray.length) return;
        else if (rightChild >= heapArray.length) {
            if (heapArray[index] <= heapArray[parent] && heapArray[index] > heapArray[leftChild]) return;
            swap(index, leftChild);
        } else {
            if (heapArray[index] <= heapArray[parent]
                    && heapArray[index] > heapArray[leftChild]
                    && heapArray[index] > heapArray[rightChild]) return;
            int maxIndex = heapArray[leftChild] > heapArray[rightChild] ? leftChild : rightChild;
            swap(index, maxIndex);
            shuffleDown(maxIndex);
        }
    }

    private void shuffleUp(int index) {
        int parent = (index - 1) / 2;
        if (heapArray[parent] < heapArray[index]) {
            swap(parent, index);
            shuffleUp(parent);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = heapArray[firstIndex];
        heapArray[firstIndex] = heapArray[secondIndex];
        heapArray[secondIndex] = temp;
    }
}