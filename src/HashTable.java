import java.util.*;

//public class HashTable<K, V> implements Map<K, V> {
//
//    private Pair<K, V> table[];
//
//    HashTable() {
//        this.table = new ArrayList<Pair<K, V>>() {{
//            //init start pair:
//            Pair<K, V> initPair = new Pair<>(null, null);
//            initPair.deletePair();
//            add(initPair);
//        }};
//    }
//
//    //Test Constructor:
//    @SafeVarargs
//    HashTable(Pair<K, V>... pairs) {
//        Set<K> testSet = new HashSet<>();
//        int nullCount = 0;
//        for (Pair<K, V> pair : pairs) {
//            if (pair != null && pair.getKey() != null) {
//                testSet.add(pair.getKey());
//            } else {
//                nullCount++;
//            }
//        }
//        if (testSet.size() != pairs.length - nullCount) {
//            throw new IllegalArgumentException("There are duplicated keys");
//        }
//        //this.table = Arrays.asList(pairs) -> throws UnsupportedOperationException because .asList() returns List
//        this.table = new ArrayList<Pair<K, V>>() {{
//            addAll(Arrays.asList(pairs));
//        }};
//    }
//
//    public V put(K k, V v) {
//        if (k == null) throw new NullPointerException("Key = null"); // Key <> null
//        int h = transformHashCode(k);
//        int count = 0;
//        int i = h;
//        while (count < table.size()) {
//            if (table.get(i) == null || table.get(i).isDeleted() || table.get(i).getKey() == k) {
//                table.set(i, new Pair<>(k, v));
//                return table.get(i).getValue();
//            }
//            i = (i + 1) % table.size();
//            count++;
//        }
//        Pair<K, V> newPair = new Pair<>(k, v);
//        table.add(newPair);
//        return v;
//    }
//
//    public V remove(Object k) {
//        if (k == null) {
//            return null;
//        }
//        int h = transformHashCode(k);
//        int count = 0;
//        int i = h;
//        while (count < table.size()) {
//            if (table.get(i) != null && table.get(i).getKey() == k && !table.get(h).isDeleted()) {
//                table.get(i).deletePair();
//                return table.get(i).getValue();
//            }
//            i = (i + 1) % table.size();
//            count++;
//        }
//        return null;
//    }
//
//    public V get(Object k) {
//        if (k == null) {
//            return null;
//        }
//        int h = transformHashCode(k);
//        int count = 0;
//        int i = h;
//        while (count < table.size()) {
//            if (table.get(i).getKey() == k && !table.get(i).isDeleted()) {
//                return table.get(i).getValue();
//            }
//            i = (i + 1) % table.size();
//            count++;
//        }
//        return null;
//    }
//
//    private int transformHashCode(Object o) {
//        return o.hashCode() < table.size() ? o.hashCode() : o.hashCode() % table.size();
//    }
//
//
//    public int size() { //cчитает null и удаленые как объекты
//        return table.size();
//    }
//
//    public boolean isEmpty() { //cчитает null и удаленые как объекты
//        return table.isEmpty();
//    }
//
//    public boolean containsKey(Object key) {
//        return get(key) != null;
//    }
//
//    public boolean containsValue(Object value) {
//        return values().contains(value);
//    }
//
//    public void putAll(Map<? extends K, ? extends V> m) {
//        List<K> keys = new ArrayList<>();
//        keys.addAll(m.keySet());
//        for (K k : keys) {
//            put(k, m.get(k));
//        }
//    }
//
//    public void clear() {
//        table.clear();
//    }
//
//    /**
//     * реализованы неверно. Они должны вовзращать view
//     */
//    public Set<K> keySet() {
//        return new HashSet<K>() {{
//            for (Pair<K, V> pair : table) {
//                add(pair.getKey());
//            }
//        }};
//    }
//
//    /**
//     * реализованы неверно. Они должны вовзращать view
//     */
//    public Collection<V> values() {
//        return new ArrayList<V>() {{
//            for (Pair<K, V> pair : table) {
//                add(pair.getValue());
//            }
//        }};
//    }
//
//    /**
//     * реализованы неверно. Они должны вовзращать view
//     */
//    public Set<Entry<K, V>> entrySet() {
//        return new HashSet<Entry<K, V>>() {{
//            this.addAll(table);
//        }};
//    }
//
//    @Override
//    public String toString() {
//        return "table=" + table;
//    }
//}
//


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
        this.put(k, v);
        throw new IllegalArgumentException();
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
