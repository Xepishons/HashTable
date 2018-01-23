import java.util.Map;

public class Pair<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private boolean deleted;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }

    boolean isDeleted() {
        return deleted;
    }

    void deletePair() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "\nPair{" +
                "key=" + key +
                ", value=" + value +
                ", deleted=" + deleted +
                '}';
    }
}
