import java.util.*;

class Heap {

    public int[] HeapArray; // хранит неотрицательные числа-ключи

    public int emptySlot;

    public Heap() {
        HeapArray = null;
        emptySlot = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        // создаём массив кучи HeapArray из заданного
        // размер массива выбираем на основе глубины depth
        // ...
        int size = (int) Math.pow(2, depth + 1) - 1;
        HeapArray = new int[size];
        Arrays.fill(HeapArray, -1);
        for (int num : a) Add(num);
    }

    public int GetMax() {
        // вернуть значение корня и перестроить кучу
        if (emptySlot == 0) return -1; // если куча пуста
        int root = HeapArray[0];
        swap(0, emptySlot - 1);
        HeapArray[emptySlot - 1] = -1;
        emptySlot--;
        shuffleDown(0);
        return root;
    }

    public boolean Add(int key) {
        // добавляем новый элемент key в кучу и перестраиваем её
        if (emptySlot == HeapArray.length) return false; // если куча вся заполнена
        HeapArray[emptySlot] = key;
        shuffleUp(emptySlot++);
        return true;
    }

    private void shuffleDown(int index) {
        int parent = (index - 1) / 2;
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        if (leftChild >= HeapArray.length) return;
        else if (rightChild >= HeapArray.length) {
            if (HeapArray[index] <= HeapArray[parent] && HeapArray[index] > HeapArray[leftChild]) return;
            swap(index, leftChild);
        } else {
            if (HeapArray[index] <= HeapArray[parent]
                    && HeapArray[index] > HeapArray[leftChild]
                    && HeapArray[index] > HeapArray[rightChild]) return;
            int maxIndex = HeapArray[leftChild] > HeapArray[rightChild] ? leftChild : rightChild;
            swap(index, maxIndex);
            shuffleDown(maxIndex);
        }
    }

    private void shuffleUp(int index) {
        int parent = (index - 1) / 2;
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        if (HeapArray[parent] < HeapArray[index]) {
            swap(parent, index);
            shuffleUp(parent);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = HeapArray[firstIndex];
        HeapArray[firstIndex] = HeapArray[secondIndex];
        HeapArray[secondIndex] = temp;
    }
}