package test.java;

import main.java.EmptyLinkedListException;
import main.java.InvalidIndexException;
import main.java.ZipcodeLinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the ZipcodeLinkedList class
 * Testing Framework: JUnit
 */
public class ZipcodeLinkedListTest {

    static final int CITY = 0;
    static final int ZIPCODE = 1;

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
    public void addingOneElementShouldIncreaseCountByOne(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Count should be zero for a new empty linked list
        assertEquals(0, list.count);
        list.addNode("Paris", "P456Q57");
        // Count should be 1 after adding a node to the linked list
        assertEquals(1, list.count);
        // Add another two nodes
        list.addNode("Atlanta", "158FL86");
        list.addNode("Madrid", "LKJ6375");
        // Count should be 3
        assertEquals(3, list.count);
    }

    @Test
    public void showTheContentsOfTheLinkedListByTraversingIt(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add a few nodes
        list.addNode("Paris", "P456Q57");
        list.addNode("Atlanta", "158FL86");
        list.addNode("Madrid", "LKJ6375");
        // Show the structure of the linked list
        list.showContentsOfTheLinkedList();
    }

    @Test
    public void addingNodeToEmptyListShouldPointHeadAndTailToThatNode(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String city = "Bangkok";
        String zipcode = "BK78621";
        list.addNode(city, zipcode);

        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.head.next);
        assertNull(list.tail.next);

        assertEquals(city, list.head.key);
        assertEquals(zipcode, list.head.value);
        assertEquals(city, list.tail.key);
        assertEquals(zipcode, list.tail.value);
        assertEquals(1, list.count);
    }

    @Test
    public void addingNodeToNonEmptyLinkedListShouldMaintainHeadAndTail(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add the first node
        String city1 = "Bangkok";
        String zipcode1 = "BK78621";
        list.addNode(city1, zipcode1);
        // Add the second node
        String city2 = "Manila";
        String zipcode2 = "M845E63";
        list.addNode(city2, zipcode2);

        assertEquals(2, list.count);
        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.tail.next);
        assertNull(list.head.next.next);

        assertEquals(city1, list.head.key);
        assertEquals(zipcode1, list.head.value);
        assertEquals(city2, list.tail.key);
        assertEquals(zipcode2, list.tail.value);
    }

    @Test
    public void addingNodeToLinkedListWithTwoNodesShouldMaintainHeadAndTail(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add the first node
        String city1 = "Bangkok";
        String zipcode1 = "BK78621";
        list.addNode(city1, zipcode1);
        // Add the second node
        String city2 = "Manila";
        String zipcode2 = "M845E63";
        list.addNode(city2, zipcode2);
        // Add the third node
        String city3 = "Taipei";
        String zipcode3 = "YH682F9";
        list.addNode(city3, zipcode3);

        assertEquals(3, list.count);
        assertNotNull(list.head);
        assertNotNull(list.tail);
        assertNull(list.tail.next);
        assertNull(list.head.next.next.next);

        assertEquals(city1, list.head.key);
        assertEquals(zipcode1, list.head.value);
        assertEquals(city2, list.head.next.key);
        assertEquals(zipcode2, list.head.next.value);
        assertEquals(city3, list.tail.key);
        assertEquals(zipcode3, list.tail.value);

    }

    @Test
    public void sizeOfTheLinkedListTest(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        assertEquals(0, list.size());

        // Add a node
        String city1 = "Bangkok";
        String zipcode1 = "BK78621";
        list.addNode(city1, zipcode1);
        assertEquals(1, list.size());

        // Add another node
        String city2 = "Manila";
        String zipcode2 = "M845E63";
        list.addNode(city2, zipcode2);
        assertEquals(2, list.size());
    }

    @Test
    public void retrieveNodeByIndexTest() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add a few nodes
        list.addNode("Paris", "P456Q57");
        list.addNode("Atlanta", "158FL86");
        list.addNode("Madrid", "LKJ6375");
        list.addNode("Rio", "5478135");

        String[] locationA = list.getNodeAtIndex(4);
        assertEquals("Rio", locationA[CITY]);
        assertEquals("5478135", locationA[ZIPCODE]);

        String[] locationB = list.getNodeAtIndex(2);
        assertEquals("Atlanta", locationB[CITY]);
        assertEquals("158FL86", locationB[ZIPCODE]);

        String[] locationC = list.getNodeAtIndex(1);
        assertEquals("Paris", locationC[CITY]);
        assertEquals("P456Q57", locationC[ZIPCODE]);

        String[] locationD = list.getNodeAtIndex(3);
        assertEquals("Madrid", locationD[CITY]);
        assertEquals("LKJ6375", locationD[ZIPCODE]);
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
    public void zeroIndexShouldThrowException() throws InvalidIndexException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Mexico", "M58RP8D");
        String[] data = list.getNodeAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void indexLargerThanSizeOfLinkedListShouldThrowException() throws InvalidIndexException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Mexico", "M58RP8D");
        String[] data = list.getNodeAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void indexMuchLargerThanSizeOfLinkedListShouldThrowException() throws InvalidIndexException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Mexico", "M58RP8D");
        String[] data = list.getNodeAtIndex(574169820);
    }

    @Test (expected = InvalidIndexException.class)
    public void negativeIndexShouldThrowException() throws InvalidIndexException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Deli", "5783Z6D");
        String[] data = list.getNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void largeNegativeIndexShouldThrowException() throws InvalidIndexException, EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Deli", "5783Z6D");
        String[] data = list.getNodeAtIndex(-9846858);
    }

    @Test
    public void deletingLinkedListShouldRemoveAllNodes() throws EmptyLinkedListException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Rio", "5478135");
        list.addNode("Madrid", "LKJ6375");
        list.addNode("Mexico", "M58RP8D");
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
    public void deletingNodeAtIndexZeroShouldThrowException() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("London", "L84765F2");
        list.deleteNodeAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingNegativeIndexShouldThrowException() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("London", "L84765F2");
        list.deleteNodeAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingLargeNegativeIndexShouldThrowException() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Tokyo", "Y6E88941");
        list.deleteNodeAtIndex(-99135483);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingIndexLargerThanSizeOfLinkedListShouldThrowException() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Tokyo", "Y6E88941");
        list.deleteNodeAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void deletingIndexMuchLargerThanSizeOfLinkedListShouldThrowException() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Tokyo", "Y6E88941");
        list.addNode("Sydney", "E16V832R");
        list.deleteNodeAtIndex(998746889);
    }

    @Test
    public void showingANodeUnderValidIndex() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("Tokyo", "Y6E88941");
        list.addNode("Sydney", "E16V832R");
        list.addNode("Manila", "VK58F285");
        list.showElementAtIndex(3);
        list.showElementAtIndex(2);
        list.showElementAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showNodeInEmptyLinkedListShouldThrowExceptionCaseA() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showElementAtIndex(1);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showNodeInEmptyLinkedListShouldThrowExceptionCaseB() throws EmptyLinkedListException,InvalidIndexException {
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showElementAtIndex(0);
    }

    @Test (expected = EmptyLinkedListException.class)
    public void showNodeInEmptyLinkedListShouldThrowExceptionCaseC() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.showElementAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseA() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("New York", "NY16462");
        list.showElementAtIndex(2);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseB() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("New York", "NY16462");
        list.showElementAtIndex(0);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseC() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("New York", "NY16462");
        list.showElementAtIndex(-1);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseD() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("New York", "NY16462");
        list.showElementAtIndex(-999787465);
    }

    @Test (expected = InvalidIndexException.class)
    public void showNodeUnderInvalidIndexCaseE() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("New York", "NY16462");
        list.showElementAtIndex(999787485);
    }

    @Test
    public void showNodeUnderValidIndex() throws EmptyLinkedListException, InvalidIndexException{
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        list.addNode("New York", "NY16462");
        list.showElementAtIndex(1);
    }
}
