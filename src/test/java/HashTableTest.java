package test.java;

import main.java.*;
import org.junit.Test;
import sun.invoke.empty.Empty;

import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the HashTable class.
 * Testing Framework: JUnit
 */
public class HashTableTest {

    static final int NUM_SLOTS = 47;
    /*
    Reference: ASCII printable code chart, Wikipedia, 2015.
    URL: https://en.wikipedia.org/wiki/ASCII#ASCII_printable_code_chart
     */
    static final int ASCII_UPPERCASE_M = 77;
    static final int ASCII_LOWERCASE_E = 101;
    static final int ASCII_LOWERCASE_X = 120;
    static final int ASCII_LOWERCASE_I = 105;
    static final int ASCII_LOWERCASE_C = 99;
    static final int ASCII_LOWERCASE_O = 111;

    @Test
    public void newHashTableShouldBeEmpty(){
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void sizeOfANewHashTableShouldEqualNumberOfSlots(){
        HashTable hashTable = new HashTable();
        assertEquals(NUM_SLOTS, hashTable.size());
    }

    @Test
    public void cityNameStringShouldBeCorrectlyConvertedToASCII(){
        HashTable hashTable = new HashTable();
        String key = "Mexico";
        int asciiEquivalent = ASCII_UPPERCASE_M + ASCII_LOWERCASE_E +ASCII_LOWERCASE_X +
                ASCII_LOWERCASE_I + ASCII_LOWERCASE_C + ASCII_LOWERCASE_O;
        assertEquals(asciiEquivalent, hashTable.convertToAscii(key));
    }

    @Test (expected = EmptyStringException.class)
    public void insertingEmptyKeyAndValueShouldThrowException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert("", "");
    }

    @Test (expected = EmptyStringException.class)
    public void insertingEmptyKeyShouldThrowException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert("", "zipcode");
    }

    @Test (expected = EmptyStringException.class)
    public void insertingEmptyValueShouldThrowException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert("key", "");
    }

    @Test (expected = NullPointerException.class)
    public void insertingNullKeyAndValueShouldThrowException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert(null, null);
    }

    @Test (expected = NullPointerException.class)
    public void insertingNullKeyShouldThrowException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert(null, "zipcode");
    }

    @Test (expected = NullPointerException.class)
    public void insertingNullValueShouldThrowException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert("key", null);
    }

    @Test
    public void hashFunctionTest(){
        HashTable hashTable = new HashTable();
        String key = "Mexico";
        int asciiEquivalent = ASCII_UPPERCASE_M + ASCII_LOWERCASE_E +ASCII_LOWERCASE_X +
                ASCII_LOWERCASE_I + ASCII_LOWERCASE_C + ASCII_LOWERCASE_O;
        int hashKey = asciiEquivalent % NUM_SLOTS;
        assertEquals(hashKey, hashTable.hash(key));
    }

    @Test
    public void insertingItemInEmptyHashTableShouldInsertTheItem() throws EmptyStringException, EmptyLinkedListException,
            InvalidIndexException,ItemNotFoundException, DuplicateItemException {
        HashTable hashTable = new HashTable();
        String key = "Almaty";
        String zipcode = "A5067391";
        hashTable.insert(key, zipcode);
        int hashKey = hashTable.hash(key);
        assertEquals(zipcode, hashTable.hashTable[hashKey].getCityZipcode(key));
        hashTable.hashTable[hashKey].showContentsOfTheLinkedList();
    }

    @Test
    public void insertingCollidingItemInHashTableWithoutDuplicatesShouldInsertThatItem() throws EmptyStringException,
            EmptyLinkedListException, ItemNotFoundException, DuplicateItemException{
        HashTable hashTable = new HashTable();

        // Insert a city
        String key1 = "Almaty";
        String zipcode1 = "A506791";
        hashTable.insert(key1, zipcode1);

        // Insert another city with the same hash key as Almaty, e.g. Toronto
        String key2 = "Toronto";
        String zipcode2 = "T3K8V1";
        hashTable.insert(key2, zipcode2);

        int hashKey1 = hashTable.hash(key1);
        int hashKey2 = hashTable.hash(key2);
        // Check that their hash keys are the same
        assertEquals(hashKey1, hashKey2);

        // Check that both cities are inserted in the hash table
        assertEquals(zipcode1, hashTable.hashTable[hashKey1].getCityZipcode(key1));
        assertEquals(zipcode2, hashTable.hashTable[hashKey2].getCityZipcode(key2));
    }

    @Test (expected = DuplicateItemException.class)
    public void insertingTheSameItemInHashTableShouldRaiseException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        String key = "Almaty";
        String zipcode = "A506791";
        hashTable.insert(key, zipcode);
        // Attempt to insert a duplicate
        hashTable.insert(key, zipcode);
    }

    @Test (expected = DuplicateItemException.class)
    public void insertingCollidingItemInHashTableWithDuplicatesShouldRaiseException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();

        // Insert a city
        String key1 = "Almaty";
        String zipcode1 = "A506791";
        hashTable.insert(key1, zipcode1);

        // Insert another city with the same hash key as Almaty, e.g. Toronto
        String key2 = "Toronto";
        String zipcode2 = "T3K8V1";
        hashTable.insert(key2, zipcode2);

        // Attempt to insert a duplicate of Almaty
        String key3 = "Almaty";
        String zipcode3 = "A506791";
        hashTable.insert(key3, zipcode3);
    }

    @Test
    public void hashTableShouldIndicateItIsNotEmptyAfterAddingItemToIt() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        hashTable.insert("Toronto", "T3K8V1");
        assertFalse(hashTable.isEmpty());
    }

    @Test
    public void insertingItemsInDifferentSlotsShouldMaintainNonEmptyStatusOfHashTable() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        // Insert two items with different hash keys
        hashTable.insert("Toronto", "T3K8V1");
        assertFalse(hashTable.isEmpty());
        hashTable.insert("Tokyo", "6748J45");
        // Check that their hash keys are different (i.e. the items are in different slots)
        assertNotEquals(hashTable.hash("Toronto"), hashTable.hash("Tokyo"));
        // Check that the table is not empty at this point
        assertFalse(hashTable.isEmpty());
    }

    @Test
    public void insertingCollidingItemsInTheSameSlotShouldMaintainNonEmptyStatusOfHashTable() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        // Insert a city
        String key1 = "Almaty";
        String zipcode1 = "A506791";
        hashTable.insert(key1, zipcode1);
        // Insert another city with the same hash key as Almaty, e.g. Toronto
        String key2 = "Toronto";
        String zipcode2 = "T3K8V1";
        hashTable.insert(key2, zipcode2);
        // Check that their hash keys are the same
        assertEquals(hashTable.hash("Almaty"), hashTable.hash("Toronto"));
        // Check that the table is not empty at this point
        assertFalse(hashTable.isEmpty());
    }
}
