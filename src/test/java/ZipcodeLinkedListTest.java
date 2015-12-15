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

    static final String CITY_BANGKOK = "Bangkok";
    static final String ZIPCODE_BANGKOK = "BK78621";
    static final String CITY_MEXICO = "Mexico";
    static final String ZIPCODE_MEXICO = "M58RP8D";
    static final String CITY_SYDNEY = "Sydney";
    static final String ZIPCODE_SYDNEY = "E16V832R";
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
        // Add another two nodes
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        // Count should be 3
        assertEquals(3, list.count);
    }

    @Test
    public void showTheContentsOfTheLinkedListByTraversingIt() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add a few nodes
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
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
        list.addNode(CITY_BANGKOK, ZIPCODE_BANGKOK);

        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.head.next);
        assertNull(list.tail.next);

        assertEquals(CITY_BANGKOK, list.head.key);
        assertEquals(ZIPCODE_BANGKOK, list.head.value);
        assertEquals(CITY_BANGKOK, list.tail.key);
        assertEquals(ZIPCODE_BANGKOK, list.tail.value);
        assertEquals(1, list.count);
    }

    @Test
    public void addingNodeToNonEmptyLinkedListShouldMaintainHeadAndTail() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add the first node
        list.addNode(CITY_BANGKOK, ZIPCODE_BANGKOK);
        // Add the second node
        list.addNode(CITY_SYDNEY, ZIPCODE_SYDNEY);

        assertEquals(2, list.count);
        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.tail.next);
        assertNull(list.head.next.next);

        assertEquals(CITY_BANGKOK, list.head.key);
        assertEquals(ZIPCODE_BANGKOK, list.head.value);
        assertEquals(CITY_SYDNEY, list.tail.key);
        assertEquals(ZIPCODE_SYDNEY, list.tail.value);
    }

    @Test
    public void addingNodeToLinkedListWithTwoNodesShouldMaintainHeadAndTail() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add the first node
        list.addNode(CITY_BANGKOK, ZIPCODE_BANGKOK);
        // Add the second node
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        // Add the third node
        list.addNode(CITY_SYDNEY, ZIPCODE_SYDNEY);

        assertEquals(3, list.count);
        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.tail.next);
        assertNull(list.head.next.next.next);

        assertEquals(CITY_BANGKOK, list.head.key);
        assertEquals(ZIPCODE_BANGKOK, list.head.value);
        assertEquals(CITY_TOKYO, list.head.next.key);
        assertEquals(ZIPCODE_TOKYO, list.head.next.value);
        assertEquals(CITY_SYDNEY, list.tail.key);
        assertEquals(ZIPCODE_SYDNEY, list.tail.value);
    }

    @Test
    public void sizeOfTheLinkedListTest() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertEquals(0, list.size());
        // Add a node
        list.addNode(CITY_BANGKOK, ZIPCODE_BANGKOK);
        assertEquals(1, list.size());
        // Add another node
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        assertEquals(2, list.size());
    }

    @Test
    public void retrieveNodeByIndexTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add a few nodes
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);

        String[] locationA = list.getNodeAtIndex(4);
        assertEquals(CITY_TORONTO, locationA[CITY]);
        assertEquals(ZIPCODE_TORONTO, locationA[ZIPCODE]);

        String[] locationB = list.getNodeAtIndex(2);
        assertEquals(CITY_NEWYORK, locationB[CITY]);
        assertEquals(ZIPCODE_NEWYORK, locationB[ZIPCODE]);

        String[] locationC = list.getNodeAtIndex(1);
        assertEquals(CITY_TOKYO, locationC[CITY]);
        assertEquals(ZIPCODE_TOKYO, locationC[ZIPCODE]);

        String[] locationD = list.getNodeAtIndex(3);
        assertEquals(CITY_LONDON, locationD[CITY]);
        assertEquals(ZIPCODE_LONDON, locationD[ZIPCODE]);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingNodeFromEmptyListThrowsExceptionCaseA() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String[] location = list.getNodeAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingNodeFromEmptyListThrowsExceptionCaseB() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String[] location = list.getNodeAtIndex(0);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingNodeFromEmptyListThrowsExceptionCaseC() throws EmptyLinkedListException, InvalidIndexException{
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
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        list.deleteLinkedList();
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count);
        assertEquals(true, list.isEmpty());
    }

    @Test (expected = EmptyLinkedListException.class)
    public void deletingEmptyListShouldThrowException() throws EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.deleteLinkedList();
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
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_SYDNEY, ZIPCODE_SYDNEY);
        list.deleteNodeAtIndex(998746889);
    }

    @Test
    public void showingANodeUnderValidIndex() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_SYDNEY, ZIPCODE_SYDNEY);
        list.addNode(CITY_BANGKOK, ZIPCODE_BANGKOK);
        list.showNodeAtIndex(3);
        list.showNodeAtIndex(2);
        list.showNodeAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showNodeInEmptyLinkedListShouldThrowExceptionCaseA() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showNodeAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showNodeInEmptyLinkedListShouldThrowExceptionCaseB() throws EmptyLinkedListException,InvalidIndexException {
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showNodeAtIndex(0);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showNodeInEmptyLinkedListShouldThrowExceptionCaseC() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseA() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseB() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseC() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseD() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(-999787465);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseE() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(999787485);
    }

    @Test
    public void showNodeUnderValidIndex() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.showNodeAtIndex(1);
    }

    @Test
    public void deletingHeadNodeShouldUpdateHead() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        list.deleteNodeAtIndex(1); // delete Rome
        assertEquals(CITY_LONDON, list.head.key);
        assertEquals(ZIPCODE_LONDON, list.head.value);
        list.deleteNodeAtIndex(1); // delete Oslo
        assertEquals(CITY_MEXICO, list.head.key);
        assertEquals(ZIPCODE_MEXICO, list.head.value);
        list.deleteNodeAtIndex(1); // delete Mexico
        assertEquals(CITY_TOKYO, list.head.key);
        assertEquals(ZIPCODE_TOKYO, list.head.value);
        list.deleteNodeAtIndex(1); // delete Beijing
        assertNull(list.head);
    }

    @Test
    public void deletingANodeBetweenOtherNodesShouldMaintainNodesOrder() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
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
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        assertEquals(CITY_TOKYO, list.tail.key);
        assertEquals(ZIPCODE_TOKYO, list.tail.value);
        list.deleteNodeAtIndex(3); // Delete Tokyo
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        assertEquals(CITY_TORONTO, list.tail.key);
        assertEquals(ZIPCODE_TORONTO, list.tail.value);
        list.deleteNodeAtIndex(2); // Delete Toronto
        assertEquals(CITY_NEWYORK, list.head.key);
        assertEquals(ZIPCODE_NEWYORK, list.head.value);
        assertNull(list.head.next);
        assertEquals(CITY_NEWYORK, list.tail.key);
        assertEquals(ZIPCODE_NEWYORK, list.tail.value);
    }

    @Test
    public void deletingANodeShouldDecreaseCountByOne() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);

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
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
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
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);

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
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);

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

    @Test (expected = EmptyLinkedListException.class)
    public void deletingNodeByCityFromNowEmptyLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.deleteCity(CITY_TOKYO);
        list.deleteCity(CITY_TORONTO);
        // Attempt to delete a node from the linked list that is empty now
        list.deleteCity(CITY_SYDNEY);
    }

    @Test
    public void deletingNodeByCityShouldDecreaseCountByOne() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
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
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.deleteCity(CITY_SYDNEY);
    }

    @Test (expected = ItemNotFoundException.class)
    public void deletingCityThatWasDeletedShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.deleteCity(CITY_TORONTO);
        // Attempt to delete the same city again
        list.deleteCity(CITY_TORONTO);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingCityIndexFromEmptyListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertTrue(list.isEmpty());
        String zipcode = list.getCityZipcode("city");
    }

    @Test (expected = EmptyLinkedListException.class)
    public void gettingCityIndexFromNowEmptyListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        assertFalse(list.isEmpty());
        list.deleteCity(CITY_LONDON);
        assertTrue(list.isEmpty());
        String zipcode = list.getCityZipcode(CITY_LONDON);
    }

    @Test
    public void gettingZipcodesOfCitiesInTheListShouldRetrieveThoseZipcodes() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_SYDNEY, ZIPCODE_SYDNEY);
        String zipcodeA = list.getCityZipcode(CITY_LONDON);
        assertEquals(ZIPCODE_LONDON, zipcodeA);
        String zipcodeB = list.getCityZipcode(CITY_NEWYORK);
        assertEquals(ZIPCODE_NEWYORK, zipcodeB);
        String zipcodeC = list.getCityZipcode(CITY_SYDNEY);
        assertEquals(ZIPCODE_SYDNEY, zipcodeC);
    }

    @Test (expected = ItemNotFoundException.class)
    public void gettingZipcodeOfCityNotInTheListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        String zipcode = list.getCityZipcode(CITY_TORONTO);
    }

    @Test (expected = ItemNotFoundException.class)
    public void gettingZipcodeOfDeletedCityShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_SYDNEY, ZIPCODE_SYDNEY);
        list.deleteCity(CITY_SYDNEY);
        String zipcode = list.getCityZipcode(CITY_SYDNEY);
    }

    @Test
    public void checkingForDuplicatesInEmptyListShouldReturnFalse() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertFalse(list.checkForDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForDuplicatesAfterDeletingAllNodesShouldReturnFalse() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        assertFalse(list.isEmpty());
        list.deleteCity(CITY_TOKYO);
        list.deleteCity(CITY_LONDON);
        assertTrue(list.isEmpty());
        assertFalse(list.checkForDuplicatesOf(CITY_TOKYO));
        assertFalse(list.checkForDuplicatesOf(CITY_LONDON));
    }

    @Test
    public void checkingForDuplicatesInDeletedListShouldReturnFalse() throws EmptyLinkedListException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.deleteLinkedList();
        assertTrue(list.isEmpty());
        assertFalse(list.checkForDuplicatesOf(CITY_LONDON));
        assertFalse(list.checkForDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForExistingDuplicateShouldReturnTrue() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        assertTrue(list.checkForDuplicatesOf(CITY_LONDON));
        assertTrue(list.checkForDuplicatesOf(CITY_TOKYO));
    }

    @Test
    public void checkingForNonExistingDuplicateShouldReturnFalse() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        assertFalse(list.checkForDuplicatesOf(CITY_TORONTO));
        assertFalse(list.checkForDuplicatesOf(CITY_NEWYORK));
    }

    @Test
    public void checkingForDuplicatesTest() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        assertTrue(list.checkForDuplicatesOf(CITY_NEWYORK));
        assertTrue(list.checkForDuplicatesOf(CITY_LONDON));
        assertTrue(list.checkForDuplicatesOf(CITY_TOKYO));
        list.deleteCity(CITY_TOKYO);
        assertFalse(list.checkForDuplicatesOf(CITY_TOKYO));
        list.deleteCity(CITY_LONDON);
        assertFalse(list.checkForDuplicatesOf(CITY_LONDON));
        list.deleteCity(CITY_NEWYORK);
        assertFalse(list.checkForDuplicatesOf(CITY_NEWYORK));
    }

    @Test (expected = NullPointerException.class)
    public void passingNullToDuplicatesCheckShouldRaiseException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        boolean status = list.checkForDuplicatesOf(null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToDuplicatesCheckShouldRaiseException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        boolean status = list.checkForDuplicatesOf("");
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToDuplicatesCheckInNonEmptyListShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        boolean status = list.checkForDuplicatesOf("");
    }

    @Test (expected = NullPointerException.class)
    public void passingNullStringToDuplicatesCheckInNonEmptyListShouldThrowException() throws EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        boolean status = list.checkForDuplicatesOf(null);
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
    public void passingNullToGetCityIndexInNonEmptyListShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        String zipcode = list.getCityZipcode(null);
    }

    @Test (expected = EmptyStringException.class)
    public void passingEmptyStringToGetCityIndexInNonEmptyListShouldRaiseException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
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

}
