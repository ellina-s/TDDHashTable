package test.java.HashTableTests;

import main.java.*;
import main.java.exceptions.hashtable.DuplicateItemException;
import main.java.exceptions.hashtable.EmptyHashTableException;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.InvalidIndexException;
import main.java.exceptions.linkedlist.ItemNotFoundException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the HashTable class.
 * Testing Framework: JUnit
 *
 * Note: Tests are organized in classes by methods they test.
 */
@RunWith(Enclosed.class)
public class HashTableTest {

    static final int NUM_SLOTS = 47;

    static final String CITY_ALMATY = "Almaty";
    static final String ZIPCODE_ALMATY = "A506791";
    static final String CITY_TORONTO = "Toronto";
    static final String ZIPCODE_TORONTO = "T3K8V1";
    static final String CITY_TOKYO = "Tokyo";
    static final String ZIPCODE_TOKYO = "6748J45";
    static final String CITY_SINGAPORE = "Singapore";
    static final String ZIPCODE_SINGAPORE = "S573824";
    static final String CITY_MEXICO = "Mexico";

    private static final String CONVERT_TO_ASCII_METHOD_NAME = "convertToAscii";
    private static final String HASH_KEY_METHOD_NAME = "hashKeyOf";
    private static final String HASH_TABLE_SLOTS_FIELD_NAME = "hashTable";
    private static final String DUMMY_METHOD_NAME = "dummyMethodName";
    private static final String DUMMY_FIELD_NAME = "dummyFieldName";

    public static class NewHashTableTest {

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
    }

    public static class HashFunctionTest {

        @Test
        public void cityNameStringShouldBeCorrectlyConvertedToASCII() throws HashTableUtilException {
            HashTable hashTable = new HashTable();
            Integer asciiValue = (Integer) HashTableTestUtilities.execute(CONVERT_TO_ASCII_METHOD_NAME, CITY_MEXICO, hashTable);
            int asciiEquivalentOfMexico = HashTableTestUtilities.getAsciiEquivalentOfMexico();
            assertEquals(asciiEquivalentOfMexico, asciiValue.intValue());
        }

        @Test
        public void hashFunctionTest() throws HashTableUtilException {
            HashTable hashTable = new HashTable();
            Integer mexicoHashValue = (Integer) HashTableTestUtilities.execute(HASH_KEY_METHOD_NAME, CITY_MEXICO, hashTable);
            int hashKeyOfMexico = HashTableTestUtilities.getAsciiEquivalentOfMexico() % NUM_SLOTS;
            assertEquals(hashKeyOfMexico, mexicoHashValue.intValue());
        }

        @Test
        public void collidingItemsShouldHaveSameHashValues() throws HashTableUtilException {
            HashTable hashTable = new HashTable();
            Integer torontoHashValue = (Integer) HashTableTestUtilities.execute(HASH_KEY_METHOD_NAME, CITY_TORONTO, hashTable);
            Integer almatyHashValue = (Integer) HashTableTestUtilities.execute(HASH_KEY_METHOD_NAME, CITY_ALMATY, hashTable);
            assertEquals(torontoHashValue, almatyHashValue);
        }
    }

    public static class InsertMethodTest {

        @Test
        public void insertingItemInEmptyHashTableShouldInsertTheItem() throws EmptyStringException, EmptyLinkedListException,
                InvalidIndexException,ItemNotFoundException, DuplicateItemException, HashTableUtilException {
            HashTable hashTable = new HashTable();
            hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
            ZipcodeLinkedList hashTableSlot = getHashTableSlotFor(CITY_ALMATY, hashTable);
            assertEquals(ZIPCODE_ALMATY, hashTableSlot.getCityZipcode(CITY_ALMATY));
            hashTableSlot.showContentsOfTheLinkedList();
        }

        @Test
        public void insertingCollidingNonDuplicateItemsShouldNotRaiseExceptions() throws EmptyStringException,
                EmptyLinkedListException, ItemNotFoundException, DuplicateItemException, HashTableUtilException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);

            // Check that the hash keys of these two colliding cities are the same
            assertEquals(getHashKeyOfAlmaty(hashTable), getHashKeyOfToronto(hashTable));

            // Get hash table slots of the cities
            ZipcodeLinkedList almatyHashTableSlot = getHashTableSlotFor(CITY_ALMATY, hashTable);
            ZipcodeLinkedList torontoHashTableSlot = getHashTableSlotFor(CITY_TORONTO, hashTable);

            // Check that both cities are inserted in the hash table
            assertEquals(ZIPCODE_ALMATY, almatyHashTableSlot.getCityZipcode(CITY_ALMATY));
            assertEquals(ZIPCODE_TORONTO, torontoHashTableSlot.getCityZipcode(CITY_TORONTO));
        }

        @Test
        public void addingAnItemToHashTableShouldShowThatTheTableIsNotEmpty() throws EmptyStringException, DuplicateItemException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
            assertFalse(hashTable.isEmpty());
        }
    }

    public static class IsEmptyTest {

        @Test
        public void insertingItemsInDifferentSlotsShouldMaintainNonEmptyStatusOfHashTable() throws EmptyStringException,
                DuplicateItemException, HashTableUtilException {
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            // Insert two items with different hash keys
            hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
            assertFalse(hashTable.isEmpty());
            hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
            // Check that their hash keys are different (i.e. the items are in different slots)
            assertNotEquals(getHashKeyOfToronto(hashTable), getHashKeyOfTokyo(hashTable));
            // Check that the table is not empty at this point
            assertFalse(hashTable.isEmpty());
        }

        @Test
        public void hashTableWithCollidingItemsShouldBeNonEmpty() throws EmptyStringException,DuplicateItemException, HashTableUtilException {
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            // Check that the two hash keys are the same
            assertEquals(getHashKeyOfAlmaty(hashTable), getHashKeyOfToronto(hashTable));
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Check that the table is not empty at this point
            assertFalse(hashTable.isEmpty());
        }
    }

    public static class DeleteMethodTest {

        @Test
        public void deletingTheOnlyItemShouldDeleteItWithNoExceptionsThrown() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException, HashTableUtilException {
            HashTable hashTable = HashTableTestUtilities.insertTokyoInHashTable();
            // Confirm that the city is there
            assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));

            ZipcodeLinkedList tokyoSlot = getHashTableSlotFor(CITY_TOKYO, hashTable);
            assertFalse(tokyoSlot.isEmpty());
            // Delete the city
            hashTable.delete(CITY_TOKYO);
            // Confirm that the city is no longer in the hash table
            assertTrue(tokyoSlot.isEmpty());
        }

        @Test
        public void deletingAllItemsInASlotShouldMakeThatSlotEmpty() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException, HashTableUtilException {
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Confirm that the cities are there
            assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
            assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
            // Delete both cities
            hashTable.delete(CITY_TORONTO);
            hashTable.delete(CITY_ALMATY);
            // Check that the hash table is empty now
            ZipcodeLinkedList almatySlot = getHashTableSlotFor(CITY_ALMATY, hashTable);
            assertTrue(almatySlot.isEmpty());
        }

        @Test
        public void deletingAllItemsFromHashTableShouldMakeItEmpty() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, EmptyLinkedListException, ItemNotFoundException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            HashTableTestUtilities.insertFourCities(hashTable);
            // Check that the hash table is not empty
            assertFalse(hashTable.isEmpty());
            // Delete all items
            hashTable.delete(CITY_ALMATY);
            hashTable.delete(CITY_TOKYO);
            hashTable.delete(CITY_TORONTO);
            hashTable.delete(CITY_SINGAPORE);
            // Check that the hash table is empty
            assertTrue(hashTable.isEmpty());
        }

        @Test
        public void deletingSomeItemsFromHashTableShouldNotMakeItEmpty() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, EmptyLinkedListException, ItemNotFoundException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            HashTableTestUtilities.insertAlmatyAndTokyo(hashTable);
            // Delete one item
            hashTable.delete(CITY_ALMATY);
            // Check that the hash table is not empty
            assertFalse(hashTable.isEmpty());
        }

        @Test
        public void addingAnItemThatCollidesWithTheDeletedItemShouldMakeHashTableNonEmpty() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, EmptyLinkedListException, ItemNotFoundException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            // Insert some items
            hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
            // Check that the hash table is not empty
            assertFalse(hashTable.isEmpty());
            // Delete all items
            hashTable.delete(CITY_ALMATY);
            // Check that the hash table is empty
            assertTrue(hashTable.isEmpty());
            // Insert an item
            hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
            // Check that the hash table is not empty
            assertFalse(hashTable.isEmpty());
        }

        @Test
        public void addingAnItemThatDoesNotCollideWithTheDeletedItemShouldMakeHashTableNonEmpty() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, EmptyLinkedListException, ItemNotFoundException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            HashTableTestUtilities.insertAlmatyAndTokyo(hashTable);
            // Check that the hash table is not empty
            assertFalse(hashTable.isEmpty());
            // Delete all items
            hashTable.delete(CITY_ALMATY);
            hashTable.delete(CITY_TOKYO);
            // Check that the hash table is empty
            assertTrue(hashTable.isEmpty());
            // Insert an item
            hashTable.insert(CITY_SINGAPORE, ZIPCODE_SINGAPORE);
            // Check that the hash table is not empty
            assertFalse(hashTable.isEmpty());
        }
    }

    public static class SearchMethodTest {

        @Test
        public void searchingForExistingItemInNonEmptyTableShouldReturnItsZipcode() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            // Add an item to the hash table, so that the table is not empty
            hashTable.insert(CITY_SINGAPORE, ZIPCODE_SINGAPORE);
            // Search for the city that has been added to the hash table
            assertEquals(ZIPCODE_SINGAPORE, hashTable.search(CITY_SINGAPORE));
        }

        @Test
        public void searchingForCollidingExistingItemShouldReturnItsZipcode() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException {
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Search for the cities
            assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
            assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
        }
    }

    public static class ShowContentsTest {

        @Test
        public void showingContentsOfHashTableTest() throws EmptyStringException, DuplicateItemException {
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertFourCities(hashTable);
            hashTable.showContents();
        }

        @Test
        public void showingEmptyHashTableTest(){
            HashTable hashTable = new HashTable();
            hashTable.showContents();
        }
    }

    public static class UtilitiesTest {

        @Test (expected = HashTableUtilException.class)
        public void executingInvalidMethodNameShouldFail() throws HashTableUtilException {
            HashTable hashTable = new HashTable();
            Integer asciiValue = (Integer) HashTableTestUtilities.execute(DUMMY_METHOD_NAME, CITY_MEXICO, hashTable);
            int asciiEquivalentOfMexico = HashTableTestUtilities.getAsciiEquivalentOfMexico();
            assertEquals(asciiEquivalentOfMexico, asciiValue.intValue());
        }

        @Test
        public void reflectingValidFieldNameShouldWork() throws HashTableUtilException {
            HashTable hashTable = new HashTable();
            ZipcodeLinkedList[] hashTableSlots = (ZipcodeLinkedList[]) HashTableTestUtilities.getReflectedField(HASH_TABLE_SLOTS_FIELD_NAME, hashTable);
            int tokyoHashKey = getHashKeyOfTokyo(hashTable);
            assertNull(hashTableSlots[tokyoHashKey]);
        }

        @Test (expected = HashTableUtilException.class)
        public void reflectingInvalidFieldNameShouldFail() throws HashTableUtilException {
            HashTable hashTable = new HashTable();
            ZipcodeLinkedList[] hashTableSlots = (ZipcodeLinkedList[]) HashTableTestUtilities.getReflectedField(DUMMY_FIELD_NAME, hashTable);
            int tokyoHashKey = getHashKeyOfTokyo(hashTable);
            assertNull(hashTableSlots[tokyoHashKey]);
        }

    }

    /* Helper methods to get hash keys of cities. */

    private static int getHashKeyOfAlmaty(HashTable instance) throws HashTableUtilException {
        return HashTableTestUtilities.getHashKeyOf(CITY_ALMATY, instance);
    }

    private static int getHashKeyOfToronto(HashTable instance) throws HashTableUtilException {
        return HashTableTestUtilities.getHashKeyOf(CITY_TORONTO, instance);
    }

    private static int getHashKeyOfTokyo(HashTable instance) throws HashTableUtilException {
        return HashTableTestUtilities.getHashKeyOf(CITY_TOKYO, instance);
    }

    /* Get underlying hash table slots for the given instance */
    private static ZipcodeLinkedList[] getHashTableSlots(HashTable instance) throws HashTableUtilException {
        return (ZipcodeLinkedList[]) HashTableTestUtilities.getReflectedField(HASH_TABLE_SLOTS_FIELD_NAME, instance);
    }

    /* Get a hash table slot for the given city in the given hash table instance */
    private static ZipcodeLinkedList getHashTableSlotFor(String city, HashTable instance) throws HashTableUtilException {
        int hashKey = HashTableTestUtilities.getHashKeyOf(city, instance);
        ZipcodeLinkedList[] hashTableSlots = getHashTableSlots(instance);
        return hashTableSlots[hashKey];
    }
}
