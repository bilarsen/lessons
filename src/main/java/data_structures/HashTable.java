package data_structures;

import lombok.Getter;

@Getter
public class HashTable {

    private final int size;

    private final int step;

    private final String[] slots;

    public HashTable(int size, int step) {
        this.size = size;
        this.step = step;
        slots = new String[this.size];
        for (int i = 0; i < this.size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        if (value.isEmpty())
            return 0;
        int hash = 1;
        for (char c : value.toCharArray())
            hash = hash * 31 + c;
        return Math.abs(hash) % size;
    }

    public int seekSlot(String value) {
        int index = hashFun(value);
        int distance = 0;
        for (int laps = 0; laps < step; laps = distance / size) {
            if (slots[index] == null) return index;
            index = (index + step) % size;
            distance += step;
        }
        return -1;
    }

    public int put(String value) {
        int index = seekSlot(value);
        if (index != -1) slots[index] = value;
        return index;
    }

    public int find(String value) {
        for (int i = 0; i < size; i++) {
            if (slots[i] != null && slots[i].equals(value)) return i;
        }
        return -1;
    }
}