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
        public void cityNameStringShouldBeCorrectlyConvertedToASCII(){
            HashTable hashTable = new HashTable();
            int asciiEquivalentOfMexico = HashTableTestUtilities.getAsciiEquivalentOfMexico();
            assertEquals(asciiEquivalentOfMexico, hashTable.convertToAscii(CITY_MEXICO));
        }

        @Test
        public void hashFunctionTest(){
            HashTable hashTable = new HashTable();
            int hashKeyOfMexico = HashTableTestUtilities.getAsciiEquivalentOfMexico() % NUM_SLOTS;
            assertEquals(hashKeyOfMexico, hashTable.hashKeyOf(CITY_MEXICO));
        }
    }

    public static class InsertMethodTest {

        @Test
        public void insertingItemInEmptyHashTableShouldInsertTheItem() throws EmptyStringException, EmptyLinkedListException,
                InvalidIndexException,ItemNotFoundException, DuplicateItemException {
            HashTable hashTable = new HashTable();
            hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
            int hashKey = hashTable.hashKeyOf(CITY_ALMATY);
            assertEquals(ZIPCODE_ALMATY, hashTable.hashTable[hashKey].getCityZipcode(CITY_ALMATY));
            hashTable.hashTable[hashKey].showContentsOfTheLinkedList();
        }

        @Test
        public void insertingCollidingNonDuplicateItemsShouldNotRaiseExceptions() throws EmptyStringException,
                EmptyLinkedListException, ItemNotFoundException, DuplicateItemException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Get hash keys of colliding cities
            int hashKeyOfAlmaty = hashTable.hashKeyOf(CITY_ALMATY);
            int hashKeyOfToronto = hashTable.hashKeyOf(CITY_TORONTO);
            // Check that their hash keys are the same
            assertEquals(hashKeyOfAlmaty, hashKeyOfToronto);
            // Check that both cities are inserted in the hash table
            assertEquals(ZIPCODE_ALMATY, hashTable.hashTable[hashKeyOfAlmaty].getCityZipcode(CITY_ALMATY));
            assertEquals(ZIPCODE_TORONTO, hashTable.hashTable[hashKeyOfToronto].getCityZipcode(CITY_TORONTO));
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
        public void insertingItemsInDifferentSlotsShouldMaintainNonEmptyStatusOfHashTable() throws EmptyStringException, DuplicateItemException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            // Insert two items with different hash keys
            hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
            assertFalse(hashTable.isEmpty());
            hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
            // Check that their hash keys are different (i.e. the items are in different slots)
            assertNotEquals(hashTable.hashKeyOf(CITY_TORONTO), hashTable.hashKeyOf(CITY_TOKYO));
            // Check that the table is not empty at this point
            assertFalse(hashTable.isEmpty());
        }

        @Test
        public void hashTableWithCollidingItemsShouldBeNonEmpty() throws EmptyStringException,DuplicateItemException{
            HashTable hashTable = new HashTable();
            assertTrue(hashTable.isEmpty());
            // Check that the two hash keys are the same
            assertEquals(hashTable.hashKeyOf(CITY_ALMATY), hashTable.hashKeyOf(CITY_TORONTO));
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Check that the table is not empty at this point
            assertFalse(hashTable.isEmpty());
        }
    }

    public static class DeleteMethodTest {

        @Test
        public void deletingTheOnlyItemShouldDeleteItWithNoExceptionsThrown() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = HashTableTestUtilities.insertTokyoInHashTable();
            // Confirm that the city is there
            assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
            int hashKey = hashTable.hashKeyOf(CITY_TOKYO);
            assertFalse(hashTable.hashTable[hashKey].isEmpty());
            // Delete the city
            hashTable.delete(CITY_TOKYO);
            // Confirm that the city is no longer in the hash table
            assertTrue(hashTable.hashTable[hashKey].isEmpty());
        }

        @Test
        public void deletingAllItemsInASlotShouldMakeThatSlotEmpty() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Confirm that the cities are there
            assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
            assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
            // Delete both cities
            hashTable.delete(CITY_TORONTO);
            hashTable.delete(CITY_ALMATY);
            // Check that the hash table is empty now
            int hashKeyAlmaty = hashTable.hashKeyOf(CITY_ALMATY);
            assertTrue(hashTable.hashTable[hashKeyAlmaty].isEmpty());
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
}
