package test.java;

import main.java.EmptyStringException;
import main.java.HashTable;
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
        String key = "Mexico";
        int asciiEquivalent = ASCII_UPPERCASE_M + ASCII_LOWERCASE_E +ASCII_LOWERCASE_X +
                ASCII_LOWERCASE_I + ASCII_LOWERCASE_C + ASCII_LOWERCASE_O;
        assertEquals(asciiEquivalent, hashTable.convertToAscii(key));
    }

    @Test (expected = EmptyStringException.class)
    public void insertingEmptyKeyAndValueShouldThrowException() throws EmptyStringException{
        HashTable hashTable = new HashTable();
        hashTable.insert("", "");
    }

    @Test (expected = EmptyStringException.class)
    public void insertingEmptyKeyShouldThrowException() throws EmptyStringException{
        HashTable hashTable = new HashTable();
        hashTable.insert("", "zipcode");
    }

    @Test (expected = EmptyStringException.class)
    public void insertingEmptyValueShouldThrowException() throws EmptyStringException{
        HashTable hashTable = new HashTable();
        hashTable.insert("key", "");
    }

    @Test (expected = NullPointerException.class)
    public void insertingNullKeyAndValueShouldThrowException() throws EmptyStringException{
        HashTable hashTable = new HashTable();
        hashTable.insert(null, null);
    }

    @Test (expected = NullPointerException.class)
    public void insertingNullKeyShouldThrowException() throws EmptyStringException{
        HashTable hashTable = new HashTable();
        hashTable.insert(null, "zipcode");
    }

    @Test (expected = NullPointerException.class)
    public void insertingNullValueShouldThrowException() throws EmptyStringException{
        HashTable hashTable = new HashTable();
        hashTable.insert("key", null);
    }

    @Test
    public void hashFunctionTest(){
        HashTable hashTable = new HashTable();
        String key = "Mexico";
        int asciiEquivalent = ASCII_UPPERCASE_M + ASCII_LOWERCASE_E +ASCII_LOWERCASE_X +
                ASCII_LOWERCASE_I + ASCII_LOWERCASE_C + ASCII_LOWERCASE_O;
        int hashKey = asciiEquivalent % NUM_SLOTS;
        assertEquals(hashKey, hashTable.hash(key));
    }
}
