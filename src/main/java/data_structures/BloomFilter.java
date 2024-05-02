package data_structures;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BloomFilter {

    private final int size;

    private int bitArray = 0;

    public void add(String string) {
        bitArray |= (1 << hash1(string));
        bitArray |= (1 << hash2(string));
    }

    public boolean contains(String string) {
        return (bitArray & (1 << hash1(string))) != 0 && (bitArray & (1 << hash2(string))) != 0;
    }

    private int hash1(String string) {
        int hash = 0;
        for (char ch : string.toCharArray()) {
            hash = (hash * 17 + ch) % size;
        }
        return hash;
    }

    private int hash2(String string) {
        int hash = 0;
        for (char ch : string.toCharArray()) {
            hash = (hash * 223 + ch) % size;
        }
        return hash;
    }

}