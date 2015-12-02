package main.java;

/**
 * An implementation of a hash table class that holds city zipcodes.
 * The class is implemented via Test-Driven Development.
 * See HashTableTest.java for corresponding tests.
 */
public class HashTable {

    private int size;
    private boolean isEmpty;

    private static final int NUM_SLOTS = 47;

    /**
     * Hash table constructor
     */
    public HashTable(){
        size = NUM_SLOTS;
        isEmpty = true;
    }

    /**
     * Check if a hash table is empty.
     * @return True if a hash table is empty. False, otherwise.
     */
    public boolean isEmpty(){
        return isEmpty;
    }

    /**
     * Return the size of a hash table, i.e. the number of slots in a hash table.
     * @return the size of a hash table
     */
    public int size(){
        return size;
    }

    /**
     * Convert a string to a sum of ASCII values of its characters.
     * @param key an input string
     * @return an integer sum of ASCII values of all characters in the key
     */
    public int convertToAscii(String key){
        int keyIntegerEquivalent = 0;
        int length = key.length();
        for(int i = 0; i<length; i++){
            keyIntegerEquivalent += (int) key.charAt(i);
        }
        return keyIntegerEquivalent;
    }
}
