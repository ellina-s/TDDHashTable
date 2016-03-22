package test.java.ZipcodeLinkedListTests;

import main.java.*;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.InvalidIndexException;
import main.java.exceptions.linkedlist.ItemNotFoundException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the ZipcodeLinkedList class
 * Testing Framework: JUnit
 *
 * Note: Tests are organized in classes by methods they test.
 */
@RunWith(Enclosed.class)
public class ZipcodeLinkedListTest {

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

    public static class GeneralLinkedListTest {

        @Test
        public void emptyListShouldHaveNullHead(){
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            assertNull(list.head);
        }

        @Test
        public void emptyListShouldHaveZeroCount(){
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            assertEquals(0, list.getCount());
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
        public void deletingLinkedListShouldRemoveAllNodes() throws EmptyLinkedListException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
            list.clearTheLinkedList();
            assertNull(list.head);
            assertNull(list.tail);
            assertEquals(0, list.getCount());
            assertEquals(true, list.isEmpty());
        }
    }

    public static class ShowContentsTest {

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
    }

    public static class AddNodeTest {

        @Test
        public void addingOneElementShouldIncreaseCountByOne() throws EmptyStringException {
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            // Count should be zero for a new empty linked list
            assertEquals(0, list.getCount());
            list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
            // Count should be 1 after adding a node to the linked list
            assertEquals(1, list.getCount());
            // Add two more nodes
            ZipcodeLinkedListTestUtilities.insertNewYorkLondon(list);
            // Count should be 3
            assertEquals(3, list.getCount());
        }

        @Test
        public void addingNodeToEmptyListShouldPointHeadAndTailToThatNode() throws EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);

            assertNotNull(list.head);
            assertNotNull(list.tail);
            assertNull(list.head.getNext());
            assertNull(list.tail.getNext());

            assertEquals(CITY_MEXICO, list.head.getKey());
            assertEquals(ZIPCODE_MEXICO, list.head.getValue());
            assertEquals(CITY_MEXICO, list.tail.getKey());
            assertEquals(ZIPCODE_MEXICO, list.tail.getValue());
            assertEquals(1, list.getCount());
        }

        @Test
        public void addingNodeToNonEmptyLinkedListShouldMaintainHeadAndTail() throws EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);

            assertEquals(2, list.getCount());
            assertNotNull(list.head);
            assertNotNull(list.tail);
            assertNull(list.tail.getNext());
            assertNull(list.head.getNext().getNext());

            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            assertEquals(CITY_LONDON, list.tail.getKey());
            assertEquals(ZIPCODE_LONDON, list.tail.getValue());
        }

        @Test
        public void addingNodeToLinkedListWithTwoNodesShouldMaintainHeadAndTail() throws EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);

            assertEquals(3, list.getCount());
            assertNotNull(list.head);
            assertNotNull(list.tail);
            assertNull(list.tail.getNext());
            assertNull(list.head.getNext().getNext().getNext());

            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            assertEquals(CITY_NEWYORK, list.head.getNext().getKey());
            assertEquals(ZIPCODE_NEWYORK, list.head.getNext().getValue());
            assertEquals(CITY_LONDON, list.tail.getKey());
            assertEquals(ZIPCODE_LONDON, list.tail.getValue());
        }
    }

    public static class GetNodeAtIndexTest {

        @Test
        public void retrieveNodeByIndexTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);

            DataNode mexico = list.getNodeAtIndex(3);
            assertEquals(CITY_MEXICO, mexico.getKey());
            assertEquals(ZIPCODE_MEXICO, mexico.getValue());

            DataNode newYork = list.getNodeAtIndex(1);
            assertEquals(CITY_NEWYORK, newYork.getKey());
            assertEquals(ZIPCODE_NEWYORK, newYork.getValue());

            DataNode tokyo = list.getNodeAtIndex(4);
            assertEquals(CITY_TOKYO, tokyo.getKey());
            assertEquals(ZIPCODE_TOKYO, tokyo.getValue());

            DataNode london = list.getNodeAtIndex(2);
            assertEquals(CITY_LONDON, london.getKey());
            assertEquals(ZIPCODE_LONDON, london.getValue());
        }

        @Test
        public void retrievingHeadByIndexTest() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
            DataNode retrievedHead = list.getNodeAtIndex(1);
            assertEquals(list.head.getKey(), retrievedHead.getKey());
            assertEquals(list.head.getValue(), retrievedHead.getValue());
        }

        @Test
        public void retrievingTailByIndexTest() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            DataNode retrievedTail = list.getNodeAtIndex(2);
            assertEquals(list.tail.getKey(), retrievedTail.getKey());
            assertEquals(list.tail.getValue(), retrievedTail.getValue());
        }

        @Test
        public void retrievingTailByIndexTest2() throws EmptyStringException, EmptyLinkedListException, InvalidIndexException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            list.addNode(CITY_LONDON, ZIPCODE_LONDON);
            DataNode retrievedTail = list.getNodeAtIndex(1);
            assertEquals(list.tail.getKey(), retrievedTail.getKey());
            assertEquals(list.tail.getValue(), retrievedTail.getValue());
        }

        @Test
        public void retrievingMiddleNodeByIndexTest() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
            DataNode retrievedNode = list.getNodeAtIndex(2);
            assertEquals(CITY_NEWYORK, retrievedNode.getKey());
            assertEquals(ZIPCODE_NEWYORK, retrievedNode.getValue());
        }
    }

    public static class DeleteNodeAtIndexTest {

        @Test
        public void deletingHeadNodeShouldUpdateHead() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);
            assertEquals(CITY_NEWYORK, list.head.getKey());
            assertEquals(ZIPCODE_NEWYORK, list.head.getValue());
            list.deleteNodeAtIndex(1); // delete New York
            assertEquals(CITY_LONDON, list.head.getKey());
            assertEquals(ZIPCODE_LONDON, list.head.getValue());
            list.deleteNodeAtIndex(1); // delete London
            assertEquals(CITY_MEXICO, list.head.getKey());
            assertEquals(ZIPCODE_MEXICO, list.head.getValue());
            list.deleteNodeAtIndex(1); // delete Mexico
            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            list.deleteNodeAtIndex(1); // delete Tokyo
            assertNull(list.head);
        }

        @Test
        public void deletingANodeBetweenOtherNodesShouldMaintainNodesOrder() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);
            assertEquals(CITY_LONDON, list.head.getNext().getKey());
            assertEquals(ZIPCODE_LONDON, list.head.getNext().getValue());

            list.deleteNodeAtIndex(2); // Delete London
            assertEquals(CITY_NEWYORK, list.head.getKey());
            assertEquals(ZIPCODE_NEWYORK, list.head.getValue());
            assertEquals(CITY_MEXICO, list.head.getNext().getKey());
            assertEquals(ZIPCODE_MEXICO, list.head.getNext().getValue());
            assertEquals(CITY_TOKYO, list.head.getNext().getNext().getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getNext().getNext().getValue());
            assertEquals(CITY_TOKYO, list.tail.getKey());
            assertEquals(ZIPCODE_TOKYO, list.tail.getValue());

            list.deleteNodeAtIndex(2); // Delete Mexico
            assertEquals(CITY_NEWYORK, list.head.getKey());
            assertEquals(ZIPCODE_NEWYORK, list.head.getValue());
            assertEquals(CITY_TOKYO, list.head.getNext().getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getNext().getValue());
            assertEquals(CITY_TOKYO, list.tail.getKey());
            assertEquals(ZIPCODE_TOKYO, list.tail.getValue());

            list.deleteNodeAtIndex(2); // Delete Tokyo
            assertEquals(CITY_NEWYORK, list.head.getKey());
            assertEquals(ZIPCODE_NEWYORK, list.head.getValue());
            assertEquals(CITY_NEWYORK, list.tail.getKey());
            assertEquals(ZIPCODE_NEWYORK, list.tail.getValue());
            assertNull(list.head.getNext());
        }

        @Test
        public void deletingTailShouldRemapTail() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
            assertEquals(CITY_LONDON, list.tail.getKey());
            assertEquals(ZIPCODE_LONDON, list.tail.getValue());

            list.deleteNodeAtIndex(3); // Delete London
            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            assertEquals(CITY_NEWYORK, list.tail.getKey());
            assertEquals(ZIPCODE_NEWYORK, list.tail.getValue());

            list.deleteNodeAtIndex(2); // Delete New York
            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            assertNull(list.head.getNext());
            assertEquals(CITY_TOKYO, list.tail.getKey());
            assertEquals(ZIPCODE_TOKYO, list.tail.getValue());
        }

        @Test
        public void deletingANodeShouldDecreaseCountByOne() throws EmptyLinkedListException, InvalidIndexException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertNewYorkLondonMexicoTokyo(list);

            assertEquals(4, list.getCount());
            assertFalse(list.isEmpty());
            list.deleteNodeAtIndex(4);
            assertEquals(3, list.getCount());
            assertFalse(list.isEmpty());

            list.deleteNodeAtIndex(1);
            assertEquals(2, list.getCount());
            assertFalse(list.isEmpty());

            list.deleteNodeAtIndex(2);
            assertEquals(1, list.getCount());
            assertFalse(list.isEmpty());

            list.deleteNodeAtIndex(1);
            assertEquals(0, list.getCount());
            assertTrue(list.isEmpty());
        }
    }

    public static class DeleteCityTest {

        @Test
        public void deletingNodeByCityShouldMaintainHead() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);
            list.deleteCity(CITY_TOKYO);
            assertEquals(CITY_TORONTO, list.head.getKey());
            assertEquals(ZIPCODE_TORONTO, list.head.getValue());
            list.deleteCity(CITY_TORONTO);
            assertEquals(CITY_LONDON, list.head.getKey());
            assertEquals(ZIPCODE_LONDON, list.head.getValue());
        }

        @Test
        public void deletingANodeByCityShouldMaintainOrderOfNodes() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);

            list.deleteCity(CITY_TORONTO);
            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            assertEquals(CITY_LONDON, list.head.getNext().getKey());
            assertEquals(ZIPCODE_LONDON, list.head.getNext().getValue());

            list.deleteCity(CITY_LONDON);
            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
            assertNull(list.head.getNext());

            list.deleteCity(CITY_TOKYO);
            assertNull(list.head);
        }

        @Test
        public void deletingNodeByCityShouldMaintainTail() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);

            assertEquals(CITY_LONDON, list.tail.getKey());
            assertEquals(ZIPCODE_LONDON, list.tail.getValue());

            list.deleteCity(CITY_LONDON);
            assertEquals(CITY_TORONTO, list.tail.getKey());
            assertEquals(ZIPCODE_TORONTO, list.tail.getValue());
            assertNull(list.tail.getNext());
            assertEquals(CITY_TORONTO, list.head.getNext().getKey());
            assertEquals(ZIPCODE_TORONTO, list.head.getNext().getValue());

            list.deleteCity(CITY_TORONTO);
            assertEquals(CITY_TOKYO, list.tail.getKey());
            assertEquals(ZIPCODE_TOKYO, list.tail.getValue());
            assertNull(list.tail.getNext());
            assertNull(list.head.getNext());
            assertEquals(CITY_TOKYO, list.head.getKey());
            assertEquals(ZIPCODE_TOKYO, list.head.getValue());
        }

        @Test
        public void deletingNodeByCityShouldDecreaseCountByOne() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);
            assertEquals(3, list.getCount());
            assertFalse(list.isEmpty());

            list.deleteCity(CITY_TOKYO);
            assertEquals(2, list.getCount());
            assertFalse(list.isEmpty());

            list.deleteCity(CITY_TORONTO);
            assertEquals(1, list.getCount());
            assertFalse(list.isEmpty());

            list.deleteCity(CITY_LONDON);
            assertEquals(0, list.getCount());
            assertTrue(list.isEmpty());
        }
    }

    public static class GetCityZipcodeTest {

        @Test
        public void gettingZipcodesOfCitiesFromLinkedListShouldRetrieveThoseZipcodes() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
            String londonZipcode = list.getCityZipcode(CITY_LONDON);
            assertEquals(ZIPCODE_LONDON, londonZipcode);
            String newYorkZipcode = list.getCityZipcode(CITY_NEWYORK);
            assertEquals(ZIPCODE_NEWYORK, newYorkZipcode);
            String tokyoZipcode = list.getCityZipcode(CITY_TOKYO);
            assertEquals(ZIPCODE_TOKYO, tokyoZipcode);
        }
    }

    public static class ContainsDuplicatesTest {

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
    }

    public static class ProperLinkedListDeletionTest {

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
}
