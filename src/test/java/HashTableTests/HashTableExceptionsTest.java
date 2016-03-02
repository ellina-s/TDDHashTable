package test.java.HashTableTests;

import main.java.HashTable;
import main.java.exceptions.hashtable.DuplicateItemException;
import main.java.exceptions.hashtable.EmptyHashTableException;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.ItemNotFoundException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TDD unit tests related to exceptions thrown by the HashTable class.
 * Testing Framework: JUnit
 * Note: these tests were originally developed under the HashTableTest.java file.
 * To keep tests more organized, they were arranged in their own class.
 *
 * Tests are further organized by methods they test.
 * Reference: Structuring Unit Tests, by Phil Haack. Retrieved: 2016.
 * URL: http://haacked.com/archive/2012/01/02/structuring-unit-tests.aspx/
 */
@RunWith(Enclosed.class)
public class HashTableExceptionsTest {

    static final String CITY_ALMATY = "Almaty";
    static final String ZIPCODE_ALMATY = "A506791";
    static final String CITY_TORONTO = "Toronto";
    static final String ZIPCODE_TORONTO = "T3K8V1";
    static final String CITY_TOKYO = "Tokyo";
    static final String ZIPCODE_TOKYO = "6748J45";
    static final String CITY_SINGAPORE = "Singapore";

    public static class InsertMethodExceptionsTest {

        @Test(expected = EmptyStringException.class)
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

        @Test (expected = DuplicateItemException.class)
        public void insertingTheSameItemInHashTableShouldRaiseException() throws EmptyStringException, DuplicateItemException{
            HashTable hashTable = new HashTable();
            hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
            // Attempt to insert a duplicate
            hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        }

        @Test (expected = DuplicateItemException.class)
        public void insertingADuplicateCollidingItemShouldRaiseException() throws EmptyStringException, DuplicateItemException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Attempt to insert a duplicate of Almaty
            hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        }
    }

    public static class SearchMethodExceptionsTest {

        @Test (expected = EmptyHashTableException.class)
        public void searchingForItemInEmptyTableShouldThrowException() throws EmptyHashTableException, ItemNotFoundException,
                EmptyStringException, EmptyLinkedListException {
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

        @Test (expected = ItemNotFoundException.class)
        public void searchingForDeletedItemShouldNotFindThatItem() throws EmptyStringException, DuplicateItemException,
                EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = HashTableTestUtilities.insertTokyoInHashTable();
            // Confirm that the city is there
            assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
            // Delete the city
            hashTable.delete(CITY_TOKYO);
            // Confirm that the city is not in the hash table by searching for it:
            assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
        }
    }

    public static class DeleteMethodExceptionsTest {

        @Test (expected = EmptyHashTableException.class)
        public void deletingCityFromEmptyHashTableShouldNotBePossible() throws EmptyHashTableException, EmptyStringException,
                ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            hashTable.delete(CITY_TOKYO);
        }

        @Test (expected = EmptyStringException.class)
        public void deletingEmptyStringShouldNotBePermitted() throws EmptyHashTableException, EmptyStringException,
                ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            hashTable.delete("");
        }

        @Test (expected = NullPointerException.class)
        public void deletingNullStringShouldNotBePermitted() throws EmptyHashTableException, EmptyStringException,
                ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            hashTable.delete(null);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingNonExistingCityFromNonEmptyHashTableShouldThrowException() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            // Add a city, so that the hash table is not empty
            hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
            // Try to delete a different city
            hashTable.delete(CITY_SINGAPORE);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingCollidingItemShouldDeleteItAndShouldLeaveOtherItemsIntact() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Confirm that the cities are there
            assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
            assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
            // Delete one of the cities
            hashTable.delete(CITY_TORONTO);
            // Confirm that the other city is still there
            assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
            // Confirm that the deleted city is not there. It should throw ItemNotFound exception.
            assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
        }

        @Test (expected = ItemNotFoundException.class)
        public void repeatedlyDeletingSameCityFromEmptySlotShouldThrowItemNotFoundException() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException, HashTableUtilException {
            HashTable hashTable = HashTableTestUtilities.insertTokyoInHashTable();
            // Confirm that the city is there
            assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
            // Delete the city
            hashTable.delete(CITY_TOKYO);
            // Confirm that the city is no longer in the hash table
            int hashKey = HashTableTestUtilities.getHashKeyOf(CITY_TOKYO, hashTable);
            assertTrue(hashTable.hashTable[hashKey].isEmpty());
            // Try to delete the same city again (i.e. from the same slot)
            hashTable.delete(CITY_TOKYO);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingSameCityFromNonEmptySlotShouldPropagateItemNotFoundException() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException,
                ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            // Delete one of the cities
            hashTable.delete(CITY_TORONTO);
            // Try to delete the same city again (the slot still contains the other city)
            hashTable.delete(CITY_TORONTO);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingCollidingCityFromEmptySlotShouldThrowItemNotFoundException() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            // Add a city
            hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
            // Delete it
            hashTable.delete(CITY_TORONTO);
            // Try to delete a city that goes to the same slot as Toronto:
            hashTable.delete(CITY_ALMATY);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingNonExistingCollidingItemFromNonEmptySlotShouldThrowException() throws EmptyStringException,
                DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            // Add a city
            hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
            // Attempt to delete a colliding item
            hashTable.delete(CITY_ALMATY);
        }

        @Test (expected = ItemNotFoundException.class)
        public void testDeletingLastAddedItemInTheSameSlot() throws EmptyStringException, DuplicateItemException,EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
            HashTable hashTable = new HashTable();
            HashTableTestUtilities.insertTorontoAndAlmaty(hashTable);
            hashTable.delete(CITY_ALMATY);
            // Confirm that Toronto is still there
            assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
            // Confirm that Almaty is not there. It should throw ItemNotFound exception.
            assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
        }
    }
}
