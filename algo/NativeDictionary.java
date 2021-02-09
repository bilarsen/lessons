import java.lang.reflect.Array;

class NativeDictionary<T> {

    public int size;

    public String[] slots;

    public T[] values;

    private int step;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        step = size <= 7 ? 1 : size / 7;
        step = step > 1 && size % step == 0 ? step + 1 : step;
    }

    public int hashFun(String key) {
        // всегда возвращает корректный индекс слота
        if (key.isEmpty()) return 0;
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = hash * 31 + c;
        }
        return (hash & Integer.MAX_VALUE) % size;
    }

    public boolean isKey(String key) {
        // возвращает true если ключ имеется,
        // иначе false
        return find(key) != -1;
    }

    public void put(String key, T value) {
        // гарантированно записываем
        // значение value по ключу key
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
        // возвращает value для key,
        // или null если ключ не найден
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