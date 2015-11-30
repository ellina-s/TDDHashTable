package test.java;

import main.java.HashTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test-Driven Development (TDD) style unit tests for the HashTable class.
 * Testing Framework: JUnit
 */
public class HashTableTest {

    static final int NUM_SLOTS = 47;

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
}
