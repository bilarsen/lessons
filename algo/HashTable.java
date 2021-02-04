public class HashTable {

    public int size;

    public int step;

    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        // всегда возвращает корректный индекс слота
        if (value.isEmpty()) {
            return 0;
        }
        int hash = 1;
        for (char c : value.toCharArray()) {
            hash = hash * 31 + c;
        }
        return hash % size;
    }

    public int seekSlot(String value) {
        // находит индекс пустого слота для значения, или -1
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
        // записываем значение по хэш-функции
        // возвращается индекс слота или -1
        // если из-за коллизий элемент не удаётся разместить
        int index = seekSlot(value);
        if (index != -1) slots[index] = value;
        return index;
    }

    public int find(String value) {
        // находит индекс слота со значением, или -1
        for (int i = 0; i < size; i++) {
            if (slots[i] != null && slots[i].equals(value)) return i;
        }
        return -1;
    }
}