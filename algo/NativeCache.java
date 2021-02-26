import java.lang.reflect.Array;

class NativeCache<T> {

    public int size;

    public String[] slots;

    public T[] values;

    public int[] hits;

    private int step;

    public NativeCache(int size, Class clazz) {
        this.size = size;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        hits = new int[size];
        step = size <= 7 ? 1 : size / 7;
        step = step > 1 && size % step == 0 ? step + 1 : step;
    }

    private int hashFun(String key) {
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
                hits[index]++;
                return;
            }
            index = (index + step) % size;
            distance += step;
        }
        int leastHit = 0;
        for (int i = 1; i < size; i++) {
            leastHit = hits[i] < hits[leastHit] ? i : leastHit;
        }
        leastHit = hits[leastHit] == hits[index] ? index : leastHit;
        slots[leastHit] = null;
        values[leastHit] = null;
        hits[leastHit] = 0;
        put(key, value);
    }

    public T get(String key) {
        int index = find(key);
        if (index != -1) {
            hits[index]++;
            return values[index];
        }
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