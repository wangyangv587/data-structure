package datastructure.hash;

import java.util.TreeMap;

/**
 * @author: Alex
 * @date: 2018/12/21 15:33
 * description:
 */
public class HashTable<K,V> {
    private static final int upperTol = 10;
    private static final int lowwerTol = 2;
    private static final int[] capacity = {53,97,193,389,769,1543,3079,6151,12289,24593,49157,98317,196613,393241,
            786433,1572869,3145739,6291469,12582917,25165843,50331653,100663319,201326611,402653189,805306457,1610612741};
    private int capacityIndex = 0;

    TreeMap<K,V>[] hashTable;
    int m;
    int size;


    public HashTable(){
        this.m = capacity[0];
        this.size = 0;
        this.hashTable = new TreeMap[m];
        for (int i = 0; i < m; i++) {
            this.hashTable[i] = new TreeMap<>();
        }
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int old = m;
        this.m = newM;
        for (int i = 0; i < old; i++) {
            TreeMap<K,V> map = hashTable[i];
            for(K key : map.keySet()){
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        this.hashTable = newHashTable;
    }

    public void add(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if(map.containsKey(key)){
            map.put(key,value);
        }else{
            map.put(key,value);
            size++;
        }
        if(size >= upperTol * upperTol && capacityIndex + 1 < capacity.length){
            capacityIndex++;
            resize(capacity[capacityIndex]);
        }
    }

    public V remove(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;
        }
        if(size < lowwerTol * m && capacityIndex - 1 >= 0){
            capacityIndex--;
            resize(capacity[capacityIndex]);
        }
        return ret;
    }

    public void set(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if(!map.containsKey(key)){
            throw new IllegalArgumentException("key is not exists");
        }
        map.put(key,value);
    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }
}
