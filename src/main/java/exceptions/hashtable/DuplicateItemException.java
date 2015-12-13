package main.java.exceptions.hashtable;

import main.java.exceptions.hashtable.HashTableException;

/**
 * Custom exception for the HashTable class.
 * The exception indicates a duplicate item in a hash table.
 */
public class DuplicateItemException extends HashTableException {

    public DuplicateItemException(){}

    public DuplicateItemException(String message){
        super(message);
        System.out.println(message);
    }
}
