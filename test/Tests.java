import org.junit.Test;
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

        HashTable<String, Integer> HashTableT2 = new HashTable(new Pair("Joe", 18)
                , new Pair("Kate", 22)
                , new Pair("John", 56)
                , null
                , new Pair(null, 36)
                , new Pair("Jim", null)
                , new Pair("Vladimir", 64)
                , null
                , null
                , new Pair(null, null)
                , new Pair("Victor", 56));
        assertEquals(new Integer(19), HashTableT2.put("Ivan", 19));
        assertEquals(new Integer(37), HashTableT2.put("Ivan", 37));
        assertEquals(null, HashTableT2.put(null, null));
        assertEquals(null, HashTableT2.put("Mike", null));
        logger.info("TABLE_2:\n" + HashTableT2.toString());

        HashTable<Integer, String> HashTableT3 = new HashTable(null, null, null, null, null, null, null, null, null, null);
        logger.info("TABLE_3:\n" + HashTableT3.toString());
        HashTableT3.put(1, "A");
        HashTableT3.put(2, "B");
        HashTableT3.put(null, "A");
        HashTableT3.put(3, "C");
        HashTableT3.put(null, null);
        HashTableT3.put(4, "D");
        HashTableT3.put(7, null);
        HashTableT3.put(5, "E");
        logger.info("TABLE_3:\n" + HashTableT3.toString());
    }

    @Test
    public void remove() {
        HashTable<String, Integer> HashTableT = new HashTable(new Pair("Joe", 18)
                , new Pair("Kate", 22)
                , new Pair("John", 56)
                , new Pair(null, 36)
                , new Pair("Jim", null)
                , new Pair("Vladimir", 64)
                , null
                , null
                , new Pair(null, null)
                , new Pair("Victor", 56));
        assertTrue(null == HashTableT.remove(null));
        assertTrue(null == HashTableT.remove("Alex"));
        assertEquals(new Integer(56), HashTableT.remove("John"));
        //check put again:
        assertEquals(new Integer(63), HashTableT.put("Arnold", 63));
        assertEquals(null, HashTableT.remove("Jim"));
        logger.info("TABLE\n:" + HashTableT.toString());
    }

    @Test
    public void get() {
        HashTable<String, Integer> HashTableT = new HashTable(new Pair("Joe", 18)
                , new Pair("Kate", 22)
                , new Pair("John", 56)
                , new Pair(null, 36)
                , new Pair("Jim", null)
                , new Pair("Vladimir", 64)
                , null
                , null
                , new Pair(null, null)
                , new Pair("Victor", 56));
        assertEquals(null, HashTableT.get(null));
        assertEquals(new Integer(22), HashTableT.get("Kate"));
        assertEquals(null, HashTableT.get("Jim"));
        logger.info("TABLE\n:" + HashTableT.toString());
    }
}
