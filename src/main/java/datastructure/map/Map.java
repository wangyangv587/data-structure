package datastructure.map;

/**
 * @author: Alex
 * @date: 2018/12/10 16:56
 * description:
 */
public interface Map<K,V> {

    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V value);
    int getSize();
    boolean isEmpty();
}
