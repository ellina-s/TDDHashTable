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
}
