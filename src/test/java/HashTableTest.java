package test.java;

import main.java.*;
import main.java.exceptions.hashtable.DuplicateItemException;
import main.java.exceptions.hashtable.EmptyHashTableException;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.InvalidIndexException;
import main.java.exceptions.linkedlist.ItemNotFoundException;
import org.junit.Test;

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

    static final String CITY_ALMATY = "Almaty";
    static final String ZIPCODE_ALMATY = "A506791";
    static final String CITY_TORONTO = "Toronto";
    static final String ZIPCODE_TORONTO = "T3K8V1";
    static final String CITY_TOKYO = "Tokyo";
    static final String ZIPCODE_TOKYO = "6748J45";
    static final String CITY_SINGAPORE = "Singapore";
    static final String ZIPCODE_SINGAPORE = "S573824";

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
    public void insertingEmptyKeyAndValueShouldThrowException() throws EmptyStringException, DuplicateItemException {
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
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        int hashKey = hashTable.hash(CITY_ALMATY);
        assertEquals(ZIPCODE_ALMATY, hashTable.hashTable[hashKey].getCityZipcode(CITY_ALMATY));
        hashTable.hashTable[hashKey].showContentsOfTheLinkedList();
    }

    @Test
    public void insertingCollidingItemInHashTableWithoutDuplicatesShouldInsertThatItem() throws EmptyStringException,
            EmptyLinkedListException, ItemNotFoundException, DuplicateItemException{
        HashTable hashTable = new HashTable();

        // Insert a city
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        // Insert another city with the same hash key as Almaty, e.g. Toronto
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        // Get hash keys of the cities
        int hashKey1 = hashTable.hash(CITY_ALMATY);
        int hashKey2 = hashTable.hash(CITY_TORONTO);
        // Check that their hash keys are the same
        assertEquals(hashKey1, hashKey2);
        // Check that both cities are inserted in the hash table
        assertEquals(ZIPCODE_ALMATY, hashTable.hashTable[hashKey1].getCityZipcode(CITY_ALMATY));
        assertEquals(ZIPCODE_TORONTO, hashTable.hashTable[hashKey2].getCityZipcode(CITY_TORONTO));
    }

    @Test (expected = DuplicateItemException.class)
    public void insertingTheSameItemInHashTableShouldRaiseException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        // Attempt to insert a duplicate
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
    }

    @Test (expected = DuplicateItemException.class)
    public void insertingCollidingItemInHashTableWithDuplicatesShouldRaiseException() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        // Insert a city
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        // Insert another city with the same hash key as Almaty, e.g. Toronto
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        // Attempt to insert a duplicate of Almaty
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
    }

    @Test
    public void hashTableShouldIndicateItIsNotEmptyAfterAddingItemToIt() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        assertFalse(hashTable.isEmpty());
    }

    @Test
    public void insertingItemsInDifferentSlotsShouldMaintainNonEmptyStatusOfHashTable() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        // Insert two items with different hash keys
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        assertFalse(hashTable.isEmpty());
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
        // Check that their hash keys are different (i.e. the items are in different slots)
        assertNotEquals(hashTable.hash(CITY_TORONTO), hashTable.hash(CITY_TOKYO));
        // Check that the table is not empty at this point
        assertFalse(hashTable.isEmpty());
    }

    @Test
    public void insertingCollidingItemsInTheSameSlotShouldMaintainNonEmptyStatusOfHashTable() throws EmptyStringException,DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        // Insert a city
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        // Insert another city with the same hash key as Almaty, e.g. Toronto
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        // Check that their hash keys are the same
        assertEquals(hashTable.hash(CITY_ALMATY), hashTable.hash(CITY_TORONTO));
        // Check that the table is not empty at this point
        assertFalse(hashTable.isEmpty());
    }

    @Test (expected = EmptyHashTableException.class)
    public void searchingForItemInEmptyTableShouldThrowException() throws EmptyHashTableException, ItemNotFoundException,
            EmptyStringException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        hashTable.search("city");
    }

    @Test (expected = ItemNotFoundException.class)
    public void searchingForNotExistingNonCollidingCityInNonEmptyTableShouldThrowException() throws EmptyHashTableException,
            EmptyStringException, DuplicateItemException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        // Add an item to the hash table, so that the table is not empty
        hashTable.insert("City", "zipcode");
        // Attempt to search for another item that does not collide with the added city
        assertEquals("zipcode", hashTable.search("capital"));
    }

    @Test
    public void searchingForExistingItemInNonEmptyTableShouldReturnItsZipcode() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        // Add an item to the hash table, so that the table is not empty
        hashTable.insert(CITY_SINGAPORE, ZIPCODE_SINGAPORE);
        // Search for the city that has been added to the hash table
        assertEquals(ZIPCODE_SINGAPORE, hashTable.search(CITY_SINGAPORE));
    }

    @Test (expected = ItemNotFoundException.class)
    public void searchingForCollidingNotExistingItemShouldThrowException() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        // Insert a city
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        // Search for another city whose hash key collides with the added city, e.g. Toronto
        assertEquals("zipcode", hashTable.search(CITY_TORONTO));
    }

    @Test (expected = EmptyStringException.class)
    public void searchingForEmptyStringShouldRaiseException() throws EmptyHashTableException, ItemNotFoundException,
            EmptyStringException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        hashTable.search("");
    }

    @Test (expected = NullPointerException.class)
    public void searchingForNullStringShouldRaiseException() throws EmptyHashTableException, ItemNotFoundException,
            EmptyStringException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        hashTable.search(null);
    }

    @Test
    public void searchingForCollidingExistingItemShouldReturnItsZipcode() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException {
        HashTable hashTable = new HashTable();
        // Insert a city
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        // Insert another city with the same hash key as Almaty, e.g. Toronto
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        // Search for the cities
        assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
        assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
    }
}
