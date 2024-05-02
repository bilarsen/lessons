package data_structures;

import lombok.Getter;

import java.lang.reflect.Array;

@Getter
public class NativeDictionary<T> {

    private final  int size;

    private final  String[] slots;

    private final T[] values;

    private int step;

    public NativeDictionary(int size, Class clazz) {
        this.size = size;
        slots = new String[this.size];
        values = (T[]) Array.newInstance(clazz, this.size);
        step = this.size <= 7 ? 1 : this.size / 7;
        step = step > 1 && this.size % step == 0 ? step + 1 : step;
    }

    public int hashFun(String key) {
        if (key.isEmpty()) return 0;
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = hash * 31 + c;
        }
        return (hash & Integer.MAX_VALUE) % size;
    }

    public boolean isKey(String key) {
        return find(key) != -1;
    }

    public void put(String key, T value) {
        int index = hashFun(key);
        int distance = 0;
        for (int laps = 0; laps < step; laps = distance / size) {
            if (slots[index] == null || slots[index].equals(key)) {
                slots[index] = key;
                values[index] = value;
                return;
            }
            index = (index + step) % size;
            distance += step;
        }
    }

    public T get(String key) {
        int index = find(key);
        if (index != -1) return values[index];
        return null;
    }

    private int find(String key) {
        int index = hashFun(key);
        int distance = 0;
        for (int laps = 0; laps < step; laps = distance / size) {
            if (slots[index] == null) break;
            else if (slots[index].equals(key)) return index;
            index = (index + step) % size;
            distance += step;
        }
        return -1;
    }
}