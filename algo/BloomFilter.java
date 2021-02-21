public class BloomFilter {

    public int filter_len;

    private int bitArray;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        // создаём битовый массив длиной f_len ...
        bitArray = 0;
    }

    // хэш-функции
    public int hash1(String str1) {
        // 17
        // реализация ...
        int hash = 0;
        for (char ch : str1.toCharArray()) {
            hash = (hash * 17 + ch) % filter_len;
        }
        return hash;
    }

    public int hash2(String str1) {
        // 223
        // реализация ...
        int hash = 0;
        for (char ch : str1.toCharArray()) {
            hash = (hash * 223 + ch) % filter_len;
        }
        return hash;
    }

    public void add(String str1) {
        // добавляем строку str1 в фильтр
        bitArray |= (1 << hash1(str1));
        bitArray |= (1 << hash2(str1));
    }

    public boolean isValue(String str1) {
        // проверка, имеется ли строка str1 в фильтре
        return (bitArray & (1 << hash1(str1))) != 0 && (bitArray & (1 << hash2(str1))) != 0;
    }
}