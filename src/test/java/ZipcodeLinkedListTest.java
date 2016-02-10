package test.java;

import main.java.*;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.InvalidIndexException;
import main.java.exceptions.linkedlist.ItemNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the ZipcodeLinkedList class
 * Testing Framework: JUnit
 */
public class ZipcodeLinkedListTest {

    static final int CITY = 0;
    static final int ZIPCODE = 1;

    static final String CITY_MEXICO = "Mexico";
    static final String ZIPCODE_MEXICO = "M58RP8D";
    static final String CITY_TOKYO = "Tokyo";
    static final String ZIPCODE_TOKYO = "Y6E88941";
    static final String CITY_LONDON = "London";
    static final String ZIPCODE_LONDON = "L84765F2";
    static final String CITY_NEWYORK = "New York";
    static final String ZIPCODE_NEWYORK = "NY16462";
    static final String CITY_TORONTO = "Toronto";
    static final String ZIPCODE_TORONTO = "T4G5W6";

    @Test
    public void emptyListShouldHaveNullHead(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertNull(list.head);
    }

    @Test
    public void emptyListShouldHaveZeroCount(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertEquals(0, list.count);
    }

    @Test
    public void emptyListShouldHaveNullTail(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertNull(list.tail);
    }

    @Test
    public void newLinkedListShouldBeEmpty(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        boolean isEmpty = list.isEmpty();
        assertEquals(true, isEmpty);
    }

    @Test
    public void addingOneElementShouldIncreaseCountByOne() throws EmptyStringException {
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Count should be zero for a new empty linked list
        assertEquals(0, list.count);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        // Count should be 1 after adding a node to the linked list
        assertEquals(1, list.count);
        // Add two more nodes
        ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
        // Count should be 3
        assertEquals(3, list.count);
    }

    @Test
    public void showTheContentsOfTheLinkedListByTraversingIt() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        // Show the structure of the linked list
        list.showContentsOfTheLinkedList();
    }

    @Test
    public void showContentsOfEmptyLinkedList(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showContentsOfTheLinkedList();
    }

    @Test
    public void addingNodeToEmptyListShouldPointHeadAndTailToThatNode() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);

        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.head.next);
        assertNull(list.tail.next);

        assertEquals(CITY_MEXICO, list.head.key);
        assertEquals(ZIPCODE_MEXICO, list.head.value);
        assertEquals(CITY_MEXICO, list.tail.key);
        assertEquals(ZIPCODE_MEXICO, list.tail.value);
        assertEquals(1, list.count);
    }

    @Test
    public void addingNodeToNonEmptyLinkedListShouldMaintainHeadAndTail() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);

        assertEquals(2, list.count);
        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.tail.next);
        assertNull(list.head.next.next);

        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        assertEquals(CITY_LONDON, list.tail.key);
        assertEquals(ZIPCODE_LONDON, list.tail.value);
    }

    @Test
    public void addingNodeToLinkedListWithTwoNodesShouldMaintainHeadAndTail() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);

        assertEquals(3, list.count);
        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.tail.next);
        assertNull(list.head.next.next.next);

        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        assertEquals(CITY_NEWYORK, list.head.next.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.next.value);
        assertEquals(CITY_LONDON, list.tail.key);
        assertEquals(ZIPCODE_LONDON, list.tail.value);
    }

    @Test
    public void sizeOfTheLinkedListTest() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertEquals(0, list.size());
        // Add a node
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertEquals(1, list.size());
        // Add another node
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        assertEquals(2, list.size());
    }

    @Test
    public void retrieveNodeByIndexTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);

        String[] mexico = list.getNodeAtIndex(3);
        assertEquals(CITY_MEXICO, mexico[CITY]);
        assertEquals(ZIPCODE_MEXICO, mexico[ZIPCODE]);

        String[] newYork = list.getNodeAtIndex(1);
        assertEquals(CITY_NEWYORK, newYork[CITY]);
        assertEquals(ZIPCODE_NEWYORK, newYork[ZIPCODE]);

        String[] tokyo = list.getNodeAtIndex(4);
        assertEquals(CITY_TOKYO, tokyo[CITY]);
        assertEquals(ZIPCODE_TOKYO, tokyo[ZIPCODE]);

        String[] london = list.getNodeAtIndex(2);
        assertEquals(CITY_LONDON, london[CITY]);
        assertEquals(ZIPCODE_LONDON, london[ZIPCODE]);
    }

    @Test
    public void retrievingHeadByIndexTest() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        String[] retrievedHead = list.getNodeAtIndex(1);
        assertEquals(list.head.key, retrievedHead[CITY]);
        assertEquals(list.head.value, retrievedHead[ZIPCODE]);
    }

    @Test
    public void retrievingTailByIndexTest() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
        String[] retrievedTail = list.getNodeAtIndex(2);
        assertEquals(list.tail.key, retrievedTail[CITY]);
        assertEquals(list.tail.value, retrievedTail[ZIPCODE]);
    }

    @Test
    public void retrievingTailByIndexTest2() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        String[] retrievedTail = list.getNodeAtIndex(1);
        assertEquals(list.tail.key, retrievedTail[CITY]);
        assertEquals(list.tail.value, retrievedTail[ZIPCODE]);
    }

    @Test
    public void retrievingMiddleNodeByIndexTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        String[] retrievedNode = list.getNodeAtIndex(2);
        assertEquals(CITY_NEWYORK, retrievedNode[CITY]);
        assertEquals(ZIPCODE_NEWYORK, retrievedNode[ZIPCODE]);
    }

    @Test
    public void deletingLinkedListShouldRemoveAllNodes() throws EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        list.clearTheLinkedList();
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count);
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void showingNodesUnderValidIndicesTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        list.showNodeAtIndex(3);
        list.showNodeAtIndex(2);
        list.showNodeAtIndex(1);
    }

    @Test
    public void testShowingHeadByValidIndex() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(1);
    }

    @Test
    public void testShowingTailAtValidIndex()throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
        list.showNodeAtIndex(2);
    }

    @Test
    public void deletingHeadNodeShouldUpdateHead() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        list.deleteNodeAtIndex(1); // delete New York
        assertEquals(CITY_LONDON, list.head.key);
        assertEquals(ZIPCODE_LONDON, list.head.value);
        list.deleteNodeAtIndex(1); // delete London
        assertEquals(CITY_MEXICO, list.head.key);
        assertEquals(ZIPCODE_MEXICO, list.head.value);
        list.deleteNodeAtIndex(1); // delete Mexico
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        list.deleteNodeAtIndex(1); // delete Tokyo
        assertNull(list.head);
    }

    @Test
    public void deletingANodeBetweenOtherNodesShouldMaintainNodesOrder() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);
        assertEquals(CITY_LONDON, list.head.next.key);
        assertEquals(ZIPCODE_LONDON, list.head.next.value);

        list.deleteNodeAtIndex(2); // Delete London
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        assertEquals(CITY_MEXICO, list.head.next.key);
        assertEquals(ZIPCODE_MEXICO, list.head.next.value);
        assertEquals(CITY_TOKYO, list.head.next.next.key);
        assertEquals(ZIPCODE_TOKYO, list.head.next.next.value);
        assertEquals(CITY_TOKYO, list.tail.key);
        assertEquals(ZIPCODE_TOKYO, list.tail.value);

        list.deleteNodeAtIndex(2); // Delete Mexico
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        assertEquals(CITY_TOKYO, list.head.next.key);
        assertEquals(ZIPCODE_TOKYO, list.head.next.value);
        assertEquals(CITY_TOKYO, list.tail.key);
        assertEquals(ZIPCODE_TOKYO, list.tail.value);

        list.deleteNodeAtIndex(2); // Delete Tokyo
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        assertEquals(CITY_NEWYORK, list.tail.key);
        assertEquals(ZIPCODE_NEWYORK, list.tail.value);
        assertNull(list.head.next);
    }

    @Test
    public void deletingTailShouldRemapTail() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        assertEquals(CITY_LONDON, list.tail.key);
        assertEquals(ZIPCODE_LONDON, list.tail.value);

        list.deleteNodeAtIndex(3); // Delete London
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        assertEquals(CITY_NEWYORK, list.tail.key);
        assertEquals(ZIPCODE_NEWYORK, list.tail.value);

        list.deleteNodeAtIndex(2); // Delete New York
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        assertNull(list.head.next);
        assertEquals(CITY_TOKYO, list.tail.key);
        assertEquals(ZIPCODE_TOKYO, list.tail.value);
    }

    @Test
    public void deletingANodeShouldDecreaseCountByOne() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);

        assertEquals(4, list.count);
        assertFalse(list.isEmpty());
        list.deleteNodeAtIndex(4);
        assertEquals(3, list.count);
        assertFalse(list.isEmpty());

        list.deleteNodeAtIndex(1);
        assertEquals(2, list.count);
        assertFalse(list.isEmpty());

        list.deleteNodeAtIndex(2);
        assertEquals(1, list.count);
        assertFalse(list.isEmpty());

        list.deleteNodeAtIndex(1);
        assertEquals(0, list.count);
        assertTrue(list.isEmpty());
    }

    @Test
    public void deletingNodeByCityShouldMaintainHead() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);
        list.deleteCity(CITY_TOKYO);
        assertEquals(CITY_TORONTO, list.head.key);
        assertEquals(ZIPCODE_TORONTO, list.head.value);
        list.deleteCity(CITY_TORONTO);
        assertEquals(CITY_LONDON, list.head.key);
        assertEquals(ZIPCODE_LONDON, list.head.value);
    }

    @Test
    public void deletingANodeByCityShouldMaintainOrderOfNodes() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);

        list.deleteCity(CITY_TORONTO);
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        assertEquals(CITY_LONDON, list.head.next.key);
        assertEquals(ZIPCODE_LONDON, list.head.next.value);

        list.deleteCity(CITY_LONDON);
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        assertNull(list.head.next);

        list.deleteCity(CITY_TOKYO);
        assertNull(list.head);
    }

    @Test
    public void deletingNodeByCityShouldMaintainTail() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);

        assertEquals(CITY_LONDON, list.tail.key);
        assertEquals(ZIPCODE_LONDON, list.tail.value);

        list.deleteCity(CITY_LONDON);
        assertEquals(CITY_TORONTO, list.tail.key);
        assertEquals(ZIPCODE_TORONTO, list.tail.value);
        assertNull(list.tail.next);
        assertEquals(CITY_TORONTO, list.head.next.key);
        assertEquals(ZIPCODE_TORONTO, list.head.next.value);

        list.deleteCity(CITY_TORONTO);
        assertEquals(CITY_TOKYO, list.tail.key);
        assertEquals(ZIPCODE_TOKYO, list.tail.value);
        assertNull(list.tail.next);
        assertNull(list.head.next);
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
    }

    @Test
    public void deletingNodeByCityShouldDecreaseCountByOne() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);
        assertEquals(3, list.count);
        assertFalse(list.isEmpty());

        list.deleteCity(CITY_TOKYO);
        assertEquals(2, list.count);
        assertFalse(list.isEmpty());

        list.deleteCity(CITY_TORONTO);
        assertEquals(1, list.count);
        assertFalse(list.isEmpty());

        list.deleteCity(CITY_LONDON);
        assertEquals(0, list.count);
        assertTrue(list.isEmpty());
    }

    @Test
    public void gettingZipcodesOfCitiesFromLinkedListShouldRetrieveThoseZipcodes() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        String zipcodeLondon = list.getCityZipcode(CITY_LONDON);
        assertEquals(ZIPCODE_LONDON, zipcodeLondon);
        String zipcodeNewYork = list.getCityZipcode(CITY_NEWYORK);
        assertEquals(ZIPCODE_NEWYORK, zipcodeNewYork);
        String zipcodeTokyo = list.getCityZipcode(CITY_TOKYO);
        assertEquals(ZIPCODE_TOKYO, zipcodeTokyo);
    }

    @Test
    public void checkingForDuplicatesInEmptyLinkedListShouldReturnFalse() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertFalse(list.containsDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForDuplicatesAfterDeletingAllNodesShouldReturnFalse() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
        assertFalse(list.isEmpty());
        list.deleteCity(CITY_TOKYO);
        list.deleteCity(CITY_LONDON);
        assertTrue(list.isEmpty());
        assertFalse(list.containsDuplicatesOf(CITY_TOKYO));
        assertFalse(list.containsDuplicatesOf(CITY_LONDON));
    }

    @Test
    public void checkingForDuplicatesInClearedLinkedListShouldReturnFalse() throws EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
        list.clearTheLinkedList();
        assertTrue(list.isEmpty());
        assertFalse(list.containsDuplicatesOf(CITY_LONDON));
        assertFalse(list.containsDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForExistingDuplicateShouldReturnTrue() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
        assertTrue(list.containsDuplicatesOf(CITY_LONDON));
        assertTrue(list.containsDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForNonExistingDuplicateShouldReturnFalse() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
        assertFalse(list.containsDuplicatesOf(CITY_TORONTO));
        assertFalse(list.containsDuplicatesOf(CITY_NEWYORK));
    }

    @Test
    public void checkingForDuplicatesWhileDeletingNodesTest() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
        assertTrue(list.containsDuplicatesOf(CITY_NEWYORK));
        assertTrue(list.containsDuplicatesOf(CITY_LONDON));
        assertTrue(list.containsDuplicatesOf(CITY_TOKYO));
        list.deleteCity(CITY_TOKYO);
        assertFalse(list.containsDuplicatesOf(CITY_TOKYO));
        list.deleteCity(CITY_LONDON);
        assertFalse(list.containsDuplicatesOf(CITY_LONDON));
        list.deleteCity(CITY_NEWYORK);
        assertFalse(list.containsDuplicatesOf(CITY_NEWYORK));
    }

    /* Tested via clearTheLinkedList() */
    @Test
    public void addingAnItemToClearedLinkedListShouldMakeLinkedListNotEmpty() throws EmptyStringException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
        assertFalse(list.isEmpty());
        // Clear the linked list
        list.clearTheLinkedList();
        assertTrue(list.isEmpty());
        // Add a city
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertFalse(list.isEmpty());
    }

    /* Tested via deleteCity() */
    @Test
    public void deletingAllItemsOneByOneAndThenAddingAnItemShouldMakeLinkedListNotEmpty() throws EmptyStringException,
            EmptyLinkedListException, ItemNotFoundException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
        assertFalse(list.isEmpty());
        // Delete cities one by one
        list.deleteCity(CITY_LONDON);
        list.deleteCity(CITY_NEWYORK);
        assertTrue(list.isEmpty());
        // Add a city
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertFalse(list.isEmpty());
    }

    /* Tested via deleteCity() */
    @Test
    public void testDeletingNodesInTheOrderTheyWereInsertedAndThenAddingNewNode() throws EmptyStringException, EmptyLinkedListException, ItemNotFoundException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
        assertFalse(list.isEmpty());
        // Delete cities one by one
        list.deleteCity(CITY_NEWYORK);
        list.deleteCity(CITY_LONDON);
        assertTrue(list.isEmpty());
        // Add a city
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertFalse(list.isEmpty());
    }

    /* Tested via deleteNodeAtIndex() */
    @Test
    public void testDeletionAndAdditionOfNodesViaDeleteNodeAtIndexMethod() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
        assertFalse(list.isEmpty());
        // Delete cities one by one
        list.deleteNodeAtIndex(1);
        list.deleteNodeAtIndex(1);
        assertTrue(list.isEmpty());
        // Add a city
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertFalse(list.isEmpty());
    }

    /* Tested via deleteNodeAtIndex() */
    @Test
    public void testAdditionOfNodesAfterApplyingDeleteNodeAtIndexMethod() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
        assertFalse(list.isEmpty());
        // Delete cities one by one
        list.deleteNodeAtIndex(2);
        list.deleteNodeAtIndex(1);
        assertTrue(list.isEmpty());
        // Add a city
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertFalse(list.isEmpty());
    }
}
