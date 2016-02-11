package test.java;

import main.java.ZipcodeLinkedList;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.InvalidIndexException;
import main.java.exceptions.linkedlist.ItemNotFoundException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TDD unit tests related to exceptions thrown by the ZipcodeLinkedList class.
 * Testing Framework: JUnit
 * Note: These unit tests were factored out from ZipcodeLinkedListTest to keep tests more organized.
 * Tests are further organized by methods they test.
 */
@RunWith(Enclosed.class)
public class ZipcodeLinkedListExceptionsTest {

    static final String CITY_LONDON = "London";
    static final String ZIPCODE_LONDON = "L84765F2";
    static final String CITY_MEXICO = "Mexico";
    static final String ZIPCODE_MEXICO = "M58RP8D";
    static final String CITY_TOKYO = "Tokyo";
    static final String ZIPCODE_TOKYO = "Y6E88941";
    static final String CITY_NEWYORK = "New York";
    static final String ZIPCODE_NEWYORK = "NY16462";
    static final String CITY_TORONTO = "Toronto";
    static final String ZIPCODE_TORONTO = "T4G5W6";

    public static class GetNodeAtIndexTest {

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
    }

    public static class DeleteNodeAtIndexTest {

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
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            list.deleteNodeAtIndex(998746889);
        }
    }

    public static class ShowNodeAtIndexTest {

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
    }

    public static class DeleteCityTest {

        @Test (expected = EmptyLinkedListException.class)
        public void deletingNodeByCityFromEmptyLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            list.deleteCity("City");
        }

        /* Test by deleting nodes one by one via deleteCity(). */
        @Test (expected = EmptyLinkedListException.class)
        public void deletingNodeByCityFromEmptiedLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            list.deleteCity(CITY_TOKYO);
            list.deleteCity(CITY_LONDON);
            // Attempt to delete a node from the linked list that is empty now
            list.deleteCity(CITY_MEXICO);
        }

        /* Test by clearing the linked list via clearTheLinkedList(). */
        @Test (expected = EmptyLinkedListException.class)
        public void deletingNodeByCityFromClearedLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            list.clearTheLinkedList();
            // Attempt to delete a node from the linked list that is empty now
            list.deleteCity(CITY_MEXICO);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingCityThatIsNotFoundShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);
            list.deleteCity(CITY_MEXICO);
        }

        @Test (expected = ItemNotFoundException.class)
        public void deletingCityThatWasDeletedShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoTorontoLondon(list);
            list.deleteCity(CITY_TORONTO);
            // Attempt to delete the same city again
            list.deleteCity(CITY_TORONTO);
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
    }

    public static class GetCityZipcodeTest {

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

        @Test (expected = ItemNotFoundException.class)
        public void gettingZipcodeOfCityNotInTheLinkedListShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoNewYorkLondon(list);
            String zipcode = list.getCityZipcode(CITY_TORONTO);
        }

        @Test (expected = ItemNotFoundException.class)
        public void gettingZipcodeOfDeletedCityShouldThrowException() throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            list.deleteCity(CITY_LONDON);
            String zipcode = list.getCityZipcode(CITY_LONDON);
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
    }

    public static class ContainsDuplicatesOfTest {

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
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            boolean status = list.containsDuplicatesOf("");
        }

        @Test (expected = NullPointerException.class)
        public void passingNullStringToDuplicatesCheckInNonEmptyLinkedListShouldThrowException() throws EmptyStringException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            ZipcodeLinkedListTestUtilities.insertTokyoLondon(list);
            boolean status = list.containsDuplicatesOf(null);
        }
    }

    public static class AddNodeTest {

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

    public static class ClearTheLinkedListTest {

        @Test (expected = EmptyLinkedListException.class)
        public void deletingEmptyListShouldThrowException() throws EmptyLinkedListException{
            ZipcodeLinkedList list = new ZipcodeLinkedList();
            list.clearTheLinkedList();
        }
    }
}
