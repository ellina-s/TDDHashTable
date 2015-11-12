package test.java;

import main.java.ZipcodeLinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the ZipcodeLinkedList class
 * Testing Framework: JUnit
 */
public class ZipcodeLinkedListTest {

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
    public void shouldBeAbleToAddANodeToTheHead(){
        // make Head point to a new node, and a new node point to where
        // used to point at (aka null)
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        String city = "Tokyo";
        String zipcode = "54JK75";

        list.addToHead(city, zipcode);

        // Check that head no longer points to null
        assertNotNull(list.head);
        // Retrieve a node where the head is pointing to
        ZipcodeLinkedList.Node newNode = list.head;
        // Check the contents of the newNode
        assertEquals(city, newNode.key);
        assertEquals(zipcode, newNode.value);
        // Check that newNode.next points to null
        assertNull(newNode.next);
        // Check that newNode should not be null
        assertNotNull(newNode);
    }

    @Test
    public void addingOneElementShouldIncreaseCountByOne(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Count should be zero for a new empty linked list
        assertEquals(0, list.count);
        list.addToHead("Paris", "P456Q57");
        // Count should be 1 after adding a node to the linked list
        assertEquals(1, list.count);
        // Add another two nodes
        list.addToHead("Atlanta", "158FL86");
        list.addToHead("Madrid", "LKJ6375");
        // Count should be 3
        assertEquals(3, list.count);
    }

    @Test
    public void showTheContentsOfTheLinkedListByTraversingIt(){
        ZipcodeLinkedList list = new ZipcodeLinkedList();
        // Add a few nodes
        list.addToHead("Paris", "P456Q57");
        list.addToHead("Atlanta", "158FL86");
        list.addToHead("Madrid", "LKJ6375");
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
}
