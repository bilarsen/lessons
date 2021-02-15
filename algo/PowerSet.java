import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PowerSet {

    private final LinkedList<String>[] hashtable;
    private int elements;

    public PowerSet() {
        // ваша реализация хранилища
        hashtable = new LinkedList[20_000];
        elements = 0;
    }

    public int size() {
        // количество элементов в множестве
        return elements;
    }

    public void put(String value) {
        // всегда срабатывает
        if (value == null) return;
        if (bucketInsert(value)) elements++;
    }

    public boolean get(String value) {
        // возвращает true если value имеется в множестве,
        // иначе false
        if (value == null) return false;
        return find(value) != null;
    }

    public boolean remove(String value) {
        // возвращает true если value удалено
        // иначе false
        if (value == null) return false;
        if (bucketRemove(value)) {
            elements--;
            return true;
        }
        return false;
    }

    public PowerSet intersection(PowerSet set2) {
        // пересечение текущего множества и set2
        if (set2 == null || set2.size() == 0) return null;
        PowerSet powerSet = new PowerSet();
        for (LinkedList<String> bucket : hashtable) {
            if (bucket == null) continue;
            for (String str : bucket) {
                if (set2.get(str) == true) powerSet.put(str);
            }
        }
        return powerSet.size() == 0 ? null : powerSet;
    }

    public PowerSet union(PowerSet set2) {
        // объединение текущего множества и set2
        PowerSet powerSet = new PowerSet();
        powerSet.putAll(this);
        powerSet.putAll(set2);
        return powerSet.size() == 0 ? null : powerSet;
    }

    public PowerSet difference(PowerSet set2) {
        // разница текущего множества и set2
        if (set2 == null || set2.size() == 0) return this.size() == 0 ? null : this;
        PowerSet powerSet = new PowerSet();
        for (LinkedList<String> bucket : hashtable) {
            if (bucket == null) continue;
            for (String str : bucket) {
                if (!set2.get(str)) powerSet.put(str);
            }
        }
        return powerSet.size() == 0 ? null : powerSet;
    }

    public boolean isSubset(PowerSet set2) {
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
        if (set2 == null || set2.size() == 0) return false;
        return set2.toList().stream().allMatch(this::get);
    }

    public boolean putAll(PowerSet powerSet) {
        if (powerSet == null || powerSet.size() == 0) return false;
        powerSet.toList().forEach(this::put);
        return true;
    }

    public List<String> toList() {
        if (elements == 0) return null;
        List<String> list = new ArrayList<>();
        for (LinkedList<String> bucket : hashtable) {
            if (bucket == null) continue;
            bucket.forEach(list::add);
        }
        return list;
    }

    private int hashFun(String value) {
        if (value.isEmpty()) {
            return 0;
        }
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = hash * 31 + c;
        }
        return (hash & Integer.MAX_VALUE) % hashtable.length;
    }

    private boolean bucketInsert(String value) {
        int index = hashFun(value);
        LinkedList<String> bucket = hashtable[index];
        if (bucket == null) {
            hashtable[index] = bucket = new LinkedList<>();
            bucket.add(value);
            return true;
        } else if (bucket.contains(value)) {
            return false;
        } else {
            bucket.add(value);
            return true;
        }
    }

    private boolean bucketRemove(String value) {
        int index = hashFun(value);
        LinkedList<String> bucket = hashtable[index];
        if (bucket == null) return false;
        return bucket.remove(value);
    }

    private String find(String value) {
        int index = hashFun(value);
        LinkedList<String> bucket = hashtable[index];
        if (bucket == null) return null;
        return bucket.stream()
                .filter(s -> s.equals(value))
                .findFirst()
                .orElse(null);
    }
}