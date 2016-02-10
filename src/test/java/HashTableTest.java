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
    static final String CITY_MEXICO = "Mexico";

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
        int asciiEquivalentOfMexico = getAsciiEquivalentOfMexico();
        assertEquals(asciiEquivalentOfMexico, hashTable.convertToAscii(CITY_MEXICO));
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
        int hashKeyOfMexico = getAsciiEquivalentOfMexico() % NUM_SLOTS;
        assertEquals(hashKeyOfMexico, hashTable.hashKeyOf(CITY_MEXICO));
    }

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
        insertTorontoAndAlmaty(hashTable);
        // Get hash keys of colliding cities
        int hashKeyOfAlmaty = hashTable.hashKeyOf(CITY_ALMATY);
        int hashKeyOfToronto = hashTable.hashKeyOf(CITY_TORONTO);
        // Check that their hash keys are the same
        assertEquals(hashKeyOfAlmaty, hashKeyOfToronto);
        // Check that both cities are inserted in the hash table
        assertEquals(ZIPCODE_ALMATY, hashTable.hashTable[hashKeyOfAlmaty].getCityZipcode(CITY_ALMATY));
        assertEquals(ZIPCODE_TORONTO, hashTable.hashTable[hashKeyOfToronto].getCityZipcode(CITY_TORONTO));
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
        insertTorontoAndAlmaty(hashTable);
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
        insertTorontoAndAlmaty(hashTable);
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
        insertTorontoAndAlmaty(hashTable);
        // Search for the cities
        assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
        assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
    }

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

    @Test
    public void deletingTheOnlyCityShouldDeleteItWithNoExceptionsThrown() throws EmptyStringException, DuplicateItemException,
            EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = insertTokyoInHashTable();
        // Confirm that the city is there
        assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
        int hashKey = hashTable.hashKeyOf(CITY_TOKYO);
        assertFalse(hashTable.hashTable[hashKey].isEmpty());
        // Delete the city
        hashTable.delete(CITY_TOKYO);
        // Confirm that the city is no longer in the hash table
        assertTrue(hashTable.hashTable[hashKey].isEmpty());
    }

    @Test (expected = ItemNotFoundException.class)
    public void searchingForDeletedItemShouldNotFindThatItem() throws EmptyStringException, DuplicateItemException,
            EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = insertTokyoInHashTable();
        // Confirm that the city is there
        assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
        // Delete the city
        hashTable.delete(CITY_TOKYO);
        // Confirm that the city is not in the hash table by searching for it:
        assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
    }

    @Test (expected = ItemNotFoundException.class)
    public void deletingCollidingItemShouldDeleteItAndShouldLeaveOtherItemsIntact() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        insertTorontoAndAlmaty(hashTable);
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

    @Test
    public void deletingAllItemsInASlotShouldMakeThatSlotEmpty() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        insertTorontoAndAlmaty(hashTable);
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

    @Test (expected = ItemNotFoundException.class)
    public void repeatedlyDeletingSameCityFromEmptySlotShouldThrowItemNotFoundException() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = insertTokyoInHashTable();
        // Confirm that the city is there
        assertEquals(ZIPCODE_TOKYO, hashTable.search(CITY_TOKYO));
        // Delete the city
        hashTable.delete(CITY_TOKYO);
        // Confirm that the city is no longer in the hash table
        int hashKey = hashTable.hashKeyOf(CITY_TOKYO);
        assertTrue(hashTable.hashTable[hashKey].isEmpty());
        // Try to delete the same city again (i.e. from the same slot)
        hashTable.delete(CITY_TOKYO);
    }

    @Test (expected = ItemNotFoundException.class)
    public void deletingSameCityFromNonEmptySlotShouldPropagateItemNotFoundException() throws EmptyStringException,
            DuplicateItemException, EmptyHashTableException,
            ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        insertTorontoAndAlmaty(hashTable);
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

    @Test
    public void addingAnItemToHashTableShouldShowThatTheTableIsNotEmpty() throws EmptyStringException, DuplicateItemException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
        assertFalse(hashTable.isEmpty());
    }

    @Test
    public void deletingAllItemsFromHashTableShouldMakeItEmpty() throws EmptyStringException, DuplicateItemException,
            EmptyHashTableException, EmptyLinkedListException, ItemNotFoundException{
        HashTable hashTable = new HashTable();
        assertTrue(hashTable.isEmpty());
        insertFourCities(hashTable);
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
        insertAlmatyAndTokyo(hashTable);
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
        insertAlmatyAndTokyo(hashTable);
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

    @Test (expected = ItemNotFoundException.class)
    public void testDeletingLastAddedItemInTheSameSlot() throws EmptyStringException, DuplicateItemException,EmptyHashTableException, ItemNotFoundException, EmptyLinkedListException{
        HashTable hashTable = new HashTable();
        insertTorontoAndAlmaty(hashTable);
        hashTable.delete(CITY_ALMATY);
        // Confirm that Toronto is still there
        assertEquals(ZIPCODE_TORONTO, hashTable.search(CITY_TORONTO));
        // Confirm that Almaty is not there. It should throw ItemNotFound exception.
        assertEquals(ZIPCODE_ALMATY, hashTable.search(CITY_ALMATY));
    }

    @Test
    public void showingContentsOfHashTableTest() throws EmptyStringException, DuplicateItemException {
        HashTable hashTable = new HashTable();
        insertFourCities(hashTable);
        hashTable.showContents();
    }

    @Test
    public void showingEmptyHashTableTest(){
        HashTable hashTable = new HashTable();
        hashTable.showContents();
    }

    /*
    Helper methods for the unit tests above.
     */

    private void insertTorontoAndAlmaty(HashTable hashTable) throws EmptyStringException, DuplicateItemException {
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
    }

    private void insertAlmatyAndTokyo(HashTable hashTable) throws EmptyStringException, DuplicateItemException {
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
    }

    private void insertFourCities(HashTable hashTable) throws EmptyStringException, DuplicateItemException {
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        hashTable.insert(CITY_SINGAPORE, ZIPCODE_SINGAPORE);
    }

    private int getAsciiEquivalentOfMexico(){
        return ASCII_UPPERCASE_M + ASCII_LOWERCASE_E +ASCII_LOWERCASE_X +
                ASCII_LOWERCASE_I + ASCII_LOWERCASE_C + ASCII_LOWERCASE_O;
    }

    private HashTable insertTokyoInHashTable() throws EmptyStringException, DuplicateItemException {
        HashTable hashTable = new HashTable();
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
        return hashTable;
    }
}
