package MyHashMap;

public interface MyHashMap <K,V>  {
    boolean put(K key,V valve);
    boolean delete(K key);
    boolean clear();
    int size();
    V get(K key);
}
