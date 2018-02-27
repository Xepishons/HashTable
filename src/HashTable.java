import java.util.*;

public class HashTable<K, V> implements Map<K, V> {

    private Pair<K, V>[] table;

    HashTable() {
        table = new Pair[32];
    }

    public V put(K k, V v){
        if (k == null || v == null) {
            throw new NullPointerException();
        }
        int h = transformHashCode(k);
        int count = 0;
        int i = h;
        while (count < table.length) {
            if (table[i] == null || table[i].isDeleted() || table[i].getKey() == k) {
                table[i] = new Pair<>(k, v);
                return table[i].getValue();
            }
            i = (i + 1) % table.length;
            count++;
        }
        createNewArray();
        return this.put(k,v);
    }

    private void createNewArray(){
        Pair<K, V>[] tempTable = new Pair[this.table.length];
        for (int i = 0; i < table.length; i++){
            tempTable[i] = table[i];
        }
        table = new Pair[this.table.length * 2];
        for (int i = 0; i < tempTable.length; i++){
            this.put(tempTable[i].getKey(), tempTable[i].getValue());
        }
    }

    public V remove(Object k) {
        if (k == null) {
            throw new NullPointerException();
        }
        int h = transformHashCode(k);
        int count = 0;
        int i = h;
        while (count < table.length) {
            if (table[i] != null && table[i].getKey() == k && !table[h].isDeleted()) {
                table[i].deletePair();
                return table[i].getValue();
            }
            i = (i + 1) % table.length;
            count++;
        }
        return null;
    }

    public V get(Object k) {
        if (k == null) {
            throw new NullPointerException();
        }
        int h = transformHashCode(k);
        int count = 0;
        int i = h;
        while (count < table.length) {
            if (table[i].getKey() == k && !table[i].isDeleted()) {
                return table[i].getValue();
            }
            i = (i + 1) % table.length;
            count++;
        }
        return null;
    }

    private int transformHashCode(Object o) {
        return Math.abs(o.hashCode()) < table.length ? Math.abs(o.hashCode()) : Math.abs(o.hashCode()) % table.length;
    }


    public int size() {
        int temp = 0;
        for (Pair<K, V> pair : table){
            if (pair != null && !pair.isDeleted()) temp++;
        }
        return temp;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean containsKey(Object key) {
        try{
            return get(key) != null;
        } catch (NullPointerException e){
            return false;
        }

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
        table = new Pair[32];
    }

    public Set<K> keySet() {
        return new HashSet<K>() {{
            for (Pair<K, V> pair : table) {
                try {
                    add(pair.getKey());
                }catch (NullPointerException e){}
            }
        }};
    }

    public Collection<V> values() {
        return new ArrayList<V>() {{
            for (Pair<K, V> pair : table) {
                if (pair != null && !pair.isDeleted()) add(pair.getValue());
            }
        }};
    }

    public Set<Entry<K, V>> entrySet() {
        return new HashSet<Entry<K, V>>() {{
            this.addAll(Arrays.asList(table));
        }};
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "table=" + Arrays.toString(table) +
                '}';
    }
}
