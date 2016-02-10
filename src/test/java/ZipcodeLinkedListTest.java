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
        insertNewYorkLondon(list);
        // Count should be 3
        assertEquals(3, list.count);
    }

    @Test
    public void showTheContentsOfTheLinkedListByTraversingIt() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoNewYorkLondon(list);
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
        insertTokyoLondon(list);

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
        insertTokyoNewYorkLondon(list);

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
        insertNewYorkLondonMexicoTokyo(list);

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
        insertTokyoLondon(list);
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
        insertTokyoNewYorkLondon(list);
        String[] retrievedNode = list.getNodeAtIndex(2);
        assertEquals(CITY_NEWYORK, retrievedNode[CITY]);
        assertEquals(ZIPCODE_NEWYORK, retrievedNode[ZIPCODE]);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingNodeFromEmptyLinkedListThrowsExceptionCaseA() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String[] location = list.getNodeAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingNodeFromEmptyLinkedListThrowsExceptionCaseB() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String[] location = list.getNodeAtIndex(0);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingNodeFromEmptyLinkedListThrowsExceptionCaseC() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String[] location = list.getNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void zeroIndexShouldThrowException() throws InvalidIndexException, EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        String[] data = list.getNodeAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void indexLargerThanSizeOfLinkedListShouldThrowException() throws InvalidIndexException, EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        String[] data = list.getNodeAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void indexMuchLargerThanSizeOfLinkedListShouldThrowException() throws InvalidIndexException, EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        String[] data = list.getNodeAtIndex(574169820);
    }

    @Test (expected = InvalidIndexException.class)
    public void negativeIndexShouldThrowException() throws InvalidIndexException, EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        String[] data = list.getNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void largeNegativeIndexShouldThrowException() throws InvalidIndexException, EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        String[] data = list.getNodeAtIndex(-9846858);
    }

    @Test
    public void deletingLinkedListShouldRemoveAllNodes() throws EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoNewYorkLondon(list);
        list.clearTheLinkedList();
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count);
        assertEquals(true, list.isEmpty());
    }

    @Test (expected = EmptyLinkedListException.class)
    public void deletingEmptyListShouldThrowException() throws EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.clearTheLinkedList();
    }

    @Test (expected = EmptyLinkedListException.class)
    public void shouldNotBeAbleToDeleteANodeFromEmptyList() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.deleteNodeAtIndex(1);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingNodeAtIndexZeroShouldThrowException() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.deleteNodeAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingNegativeIndexShouldThrowException() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.deleteNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingLargeNegativeIndexShouldThrowException() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.deleteNodeAtIndex(-99135483);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingIndexLargerThanSizeOfLinkedListShouldThrowException() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.deleteNodeAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingIndexMuchLargerThanSizeOfLinkedListShouldThrowException() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        list.deleteNodeAtIndex(998746889);
    }

    @Test
    public void showingNodesUnderValidIndicesTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoNewYorkLondon(list);
        list.showNodeAtIndex(3);
        list.showNodeAtIndex(2);
        list.showNodeAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showingNodeInEmptyLinkedListShouldThrowExceptionCaseA() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showNodeAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showingNodeInEmptyLinkedListShouldThrowExceptionCaseB() throws EmptyLinkedListException,InvalidIndexException {
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showNodeAtIndex(0);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showingNodeInEmptyLinkedListShouldThrowExceptionCaseC() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void showingNodeUnderInvalidIndexCaseA() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void showingNodeUnderInvalidIndexCaseB() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void showingNodeUnderInvalidIndexCaseC() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void showingNodeUnderInvalidIndexCaseD() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(-999787465);
    }

    @Test (expected = InvalidIndexException.class)
    public void showingNodeUnderInvalidIndexCaseE() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(999787485);
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
        insertTokyoLondon(list);
        list.showNodeAtIndex(2);
    }

    @Test
    public void deletingHeadNodeShouldUpdateHead() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertNewYorkLondonMexicoTokyo(list);
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
        insertNewYorkLondonMexicoTokyo(list);
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
        insertTokyoNewYorkLondon(list);
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
        insertNewYorkLondonMexicoTokyo(list);

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
        insertTokyoTorontoLondon(list);
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
        insertTokyoTorontoLondon(list);

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
        insertTokyoTorontoLondon(list);

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

    @Test (expected = EmptyLinkedListException.class)
    public void deletingNodeByCityFromEmptyLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.deleteCity("City");
    }

    /* Test by deleting nodes one by one via deleteCity(). */
    @Test (expected = EmptyLinkedListException.class)
    public void deletingNodeByCityFromEmptiedLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        list.deleteCity(CITY_TOKYO);
        list.deleteCity(CITY_LONDON);
        // Attempt to delete a node from the linked list that is empty now
        list.deleteCity(CITY_MEXICO);
    }

    /* Test by clearing the linked list via clearTheLinkedList(). */
    @Test (expected = EmptyLinkedListException.class)
    public void deletingNodeByCityFromClearedLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        list.clearTheLinkedList();
        // Attempt to delete a node from the linked list that is empty now
        list.deleteCity(CITY_MEXICO);
    }

    @Test
    public void deletingNodeByCityShouldDecreaseCountByOne() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoTorontoLondon(list);
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

    @Test (expected = ItemNotFoundException.class)
    public void deletingCityThatIsNotFoundShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoTorontoLondon(list);
        list.deleteCity(CITY_MEXICO);
    }

    @Test (expected = ItemNotFoundException.class)
    public void deletingCityThatWasDeletedShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoTorontoLondon(list);
        list.deleteCity(CITY_TORONTO);
        // Attempt to delete the same city again
        list.deleteCity(CITY_TORONTO);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingCityIndexFromEmptyLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertTrue(list.isEmpty());
        String zipcode = list.getCityZipcode("city");
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingCityIndexFromEmptiedLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        assertFalse(list.isEmpty());
        list.deleteCity(CITY_LONDON);
        assertTrue(list.isEmpty());
        String zipcode = list.getCityZipcode(CITY_LONDON);
    }

    @Test
    public void gettingZipcodesOfCitiesFromLinkedListShouldRetrieveThoseZipcodes() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoNewYorkLondon(list);
        String zipcodeLondon = list.getCityZipcode(CITY_LONDON);
        assertEquals(ZIPCODE_LONDON, zipcodeLondon);
        String zipcodeNewYork = list.getCityZipcode(CITY_NEWYORK);
        assertEquals(ZIPCODE_NEWYORK, zipcodeNewYork);
        String zipcodeTokyo = list.getCityZipcode(CITY_TOKYO);
        assertEquals(ZIPCODE_TOKYO, zipcodeTokyo);
    }

    @Test (expected = ItemNotFoundException.class)
    public void gettingZipcodeOfCityNotInTheLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoNewYorkLondon(list);
        String zipcode = list.getCityZipcode(CITY_TORONTO);
    }

    @Test (expected = ItemNotFoundException.class)
    public void gettingZipcodeOfDeletedCityShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        list.deleteCity(CITY_LONDON);
        String zipcode = list.getCityZipcode(CITY_LONDON);
    }

    @Test
    public void checkingForDuplicatesInEmptyLinkedListShouldReturnFalse() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertFalse(list.containsDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForDuplicatesAfterDeletingAllNodesShouldReturnFalse() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
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
        insertTokyoLondon(list);
        list.clearTheLinkedList();
        assertTrue(list.isEmpty());
        assertFalse(list.containsDuplicatesOf(CITY_LONDON));
        assertFalse(list.containsDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForExistingDuplicateShouldReturnTrue() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        assertTrue(list.containsDuplicatesOf(CITY_LONDON));
        assertTrue(list.containsDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForNonExistingDuplicateShouldReturnFalse() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        assertFalse(list.containsDuplicatesOf(CITY_TORONTO));
        assertFalse(list.containsDuplicatesOf(CITY_NEWYORK));
    }

    @Test
    public void checkingForDuplicatesWhileDeletingNodesTest() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoNewYorkLondon(list);
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

    @Test (expected = NullPointerException.class)
    public void passingNullToDuplicatesCheckShouldRaiseException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        boolean status = list.containsDuplicatesOf(null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToDuplicatesCheckShouldRaiseException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        boolean status = list.containsDuplicatesOf("");
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToDuplicatesCheckInNonEmptyLinkedListShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        boolean status = list.containsDuplicatesOf("");
    }

    @Test (expected = NullPointerException.class)
    public void passingNullStringToDuplicatesCheckInNonEmptyLinkedListShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertTokyoLondon(list);
        boolean status = list.containsDuplicatesOf(null);
    }

    @Test (expected = NullPointerException.class)
    public void passingNullToGetCityIndexShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String zipcode = list.getCityZipcode(null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToGetCityIndexShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String zipcode = list.getCityZipcode("");
    }

    @Test (expected = NullPointerException.class)
    public void passingNullToGetCityIndexInNonEmptyLinkedListShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        String zipcode = list.getCityZipcode(null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToGetCityIndexInNonEmptyLinkedListShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        String zipcode = list.getCityZipcode("");
    }

    @Test (expected = NullPointerException.class)
    public void passingNullToDeleteCityShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.deleteCity(null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToDeleteCityShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.deleteCity("");
    }

    @Test (expected = NullPointerException.class)
    public void passingNullKeyToAddNodeShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(null, "value");
    }

    @Test (expected = NullPointerException.class)
    public void passingNullValueToAddNodeShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("key", null);
    }

    @Test (expected = NullPointerException.class)
    public void passingNullForBothKeyAndValueShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(null, null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyKeyToAddNodeShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("", "value");
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyValueToAddNodeShouldThrowException()throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("key", "");
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyValueAndKeyToAddNodeShouldThrowException()throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("", "");
    }

    /* Tested via clearTheLinkedList() */
    @Test
    public void addingAnItemToClearedLinkedListShouldMakeLinkedListNotEmpty() throws EmptyStringException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        insertNewYorkLondon(list);
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
        insertNewYorkLondon(list);
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
        insertNewYorkLondon(list);
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
        insertNewYorkLondon(list);
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
        insertNewYorkLondon(list);
        assertFalse(list.isEmpty());
        // Delete cities one by one
        list.deleteNodeAtIndex(2);
        list.deleteNodeAtIndex(1);
        assertTrue(list.isEmpty());
        // Add a city
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        assertFalse(list.isEmpty());
    }

    /*
    Helper methods for the units tests for the ZipcodeLinkedList class.
     */

    private void insertTokyoNewYorkLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }

    private void insertNewYorkLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }

    private void insertTokyoLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }

    private void insertNewYorkLondonMexicoTokyo(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
    }

    private void insertTokyoTorontoLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }
}
