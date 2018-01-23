import java.util.*;

public class HashTable<K, V> implements Map<K, V> {

    private List<Pair<K, V>> table;

    HashTable() {
        this.table = new ArrayList<Pair<K, V>>() {{
            //init start pair:
            Pair<K, V> initPair = new Pair<>(null, null);
            initPair.deletePair();
            add(initPair);
        }};
    }

    //Test Constructor:
    @SafeVarargs
    HashTable(Pair<K, V>... pairs) {
        Set<K> testSet = new HashSet<>();
        int nullCount = 0;
        for (Pair<K, V> pair : pairs) {
            if (pair != null && pair.getKey() != null) {
                testSet.add(pair.getKey());
            } else {
                nullCount++;
            }
        }
        if (testSet.size() != pairs.length - nullCount) {
            throw new IllegalArgumentException("There are duplicated keys");
        }
        //this.table = Arrays.asList(pairs) -> throws UnsupportedOperationException because .asList() returns List
        this.table = new ArrayList<Pair<K, V>>() {{
            addAll(Arrays.asList(pairs));
        }};
    }

    public V put(K k, V v) {
        if (k == null) {
            return null;
        }
        int h = transformHashCode(k);
        int count = 0;
        int i = h;
        while (count < table.size()) {
            if (table.get(i) == null || table.get(i).isDeleted() || table.get(i).getKey() == k) {
                table.set(i, new Pair<>(k, v));
                return table.get(i).getValue();
            }
            i = (i + 1) % table.size();
            count++;
        }
        Pair<K, V> newPair = new Pair<>(k, v);
        table.add(newPair);
        return v;
    }

    public V remove(Object k) {
        if (k == null) {
            return null;
        }
        int h = transformHashCode(k);
        int count = 0;
        int i = h;
        while (count < table.size()) {
            if (table.get(i) != null && table.get(i).getKey() == k && !table.get(h).isDeleted()) {
                table.get(i).deletePair();
                return table.get(i).getValue();
            }
            i = (i + 1) % table.size();
            count++;
        }
        return null;
    }

    public V get(Object k) {
        if (k == null) {
            return null;
        }
        int h = transformHashCode(k);
        int count = 0;
        int i = h;
        while (count < table.size()) {
            if (table.get(i).getKey() == k && !table.get(i).isDeleted()) {
                return table.get(i).getValue();
            }
            i = (i + 1) % table.size();
            count++;
        }
        return null;
    }

    private int transformHashCode(Object o) {
        return o.hashCode() < table.size() ? o.hashCode() : o.hashCode() % table.size();
    }


    public int size() {
        return table.size();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        List<K> keys = new ArrayList<>();
        keys.addAll(m.keySet());
        for (K k : keys) {
            put(k, m.get(k));
        }
    }

    public void clear() {
        table.clear();
    }

    public Set<K> keySet() {
        return new HashSet<K>() {{
            for (Pair<K, V> pair : table) {
                add(pair.getKey());
            }
        }};
    }

    public Collection<V> values() {
        return new ArrayList<V>() {{
            for (Pair<K, V> pair : table) {
                add(pair.getValue());
            }
        }};
    }

    public Set<Entry<K, V>> entrySet() {
        return new HashSet<Entry<K, V>>() {{
            this.addAll(table);
        }};
    }

    @Override
    public String toString() {
        return "table=" + table;
    }
}