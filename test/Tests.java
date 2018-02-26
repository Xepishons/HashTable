import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Logger;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private Logger logger = Logger.getLogger(Tests.class.getName());

    @Test
    public void put() {
        HashTable<Character, Byte> HashTableT1 = new HashTable();
        assertEquals(new Byte((byte) 8), HashTableT1.put('a', (byte) 8));
        assertFalse(new Byte((byte) 8).equals(HashTableT1.put('a', (byte) 12)));
        logger.info("TABLE_1\n:" + HashTableT1.toString());

        HashTable<String, Integer> HashTableT2 = new HashTable();
        assertEquals(new Integer(19), HashTableT2.put("Ivan", 19));
        assertEquals(new Integer(37), HashTableT2.put("Ivan", 37));
        logger.info("TABLE_2:\n" + HashTableT2.toString());

        HashTable<Integer, String> HashTableT3 = new HashTable();
        logger.info("TABLE_3:\n" + HashTableT3.toString());
        HashTableT3.put(1, "A");
        HashTableT3.put(2, "B");
        HashTableT3.put(3, "C");
        HashTableT3.put(4, "D");
        HashTableT3.put(5, "E");
        logger.info("TABLE_3:\n" + HashTableT3.toString());
    }

    @Test
    public void remove() {
        HashTable<String, Integer> HashTable = new HashTable();
        assertEquals(new Integer(63), HashTable.put("Arnold", 63));
        assertEquals(null, HashTable.remove("Jim"));
        logger.info("TABLE\n:" + HashTable.toString());
    }

    @Test
    public void get() {
        HashTable<String, Integer> HashTable = new HashTable();
        HashTable.put("Kate",22);
        HashTable.put("Jim",1);
        HashTable.put("Natan",2);
        HashTable.put("Vladimir",99);
        HashTable.put("Anton",-1);
        assertEquals(new Integer(22), HashTable.get("Kate"));
        assertEquals(new Integer(-1), HashTable.get("Anton"));
        assertEquals(new Integer(99), HashTable.get("Vladimir"));
        logger.info("TABLE\n:" + HashTable.toString());
    }

    @Test
    public void size(){
        HashTable<Integer, String> HashTable = new HashTable();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        assertEquals(5, HashTable.size());
    }

    @Test
    public void isEmpty(){
        HashTable<Integer, String> HashTable = new HashTable();
        assertEquals(true, HashTable.isEmpty());

        HashTable.put(1, "A");
        HashTable.remove(1);
        assertEquals(true, HashTable.isEmpty());

        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        assertEquals(false, HashTable.isEmpty());
    }

    @Test
    public void containsKey(){
        HashTable<Integer, String> HashTable = new HashTable();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        assertEquals(true, HashTable.containsKey(1));
        assertEquals(true, HashTable.containsKey(3));
        assertEquals(false, HashTable.containsKey(0));
        assertEquals(false, HashTable.containsKey(6));
    }

    @Test
    public void containsValue(){
        HashTable<Integer, String> HashTable = new HashTable();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        assertEquals(true, HashTable.containsValue("A"));
        assertEquals(true, HashTable.containsValue("D"));
        assertEquals(false, HashTable.containsValue("S"));
        assertEquals(false, HashTable.containsValue(6));
    }

    @Test
    public void putAll(){
        HashTable<Integer, String> HashTable = new HashTable();
        Map map = new HashMap();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        HashTable.putAll(map);
        assertEquals("A", HashTable.get(1));
        assertEquals("B", HashTable.get(2));
        assertEquals("E", HashTable.get(5));
    }

    @Test
    public void clear(){
        HashTable<Integer, String> HashTable = new HashTable();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        HashTable.clear();
        assertEquals(0, HashTable.size());
    }

    @Test
    public void keySet(){
        HashTable<Integer, String> HashTable = new HashTable();
        HashSet  set = new HashSet();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        assertEquals(set, HashTable.keySet());
    }

    @Test
    public void values(){
        HashTable<Integer, String> HashTable = new HashTable();
        ArrayList arrayList = new ArrayList();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");
        assertEquals(arrayList, HashTable.values());
    }

    @Test
    public void entrySet(){
        HashTable<Integer, String> HashTable = new HashTable();
        HashTable.put(1, "A");
        HashTable.put(2, "B");
        HashTable.put(3, "C");
        HashTable.put(4, "D");
        HashTable.put(5, "E");
//        System.out.println(HashTable.entrySet());
        logger.info("TABLE:\n" + HashTable.toString());
    }
}
