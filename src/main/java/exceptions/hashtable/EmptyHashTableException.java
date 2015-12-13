package main.java.exceptions.hashtable;

import main.java.exceptions.hashtable.HashTableException;

/**
 * Custom exception for the HashTable class.
 * This exception indicates that a hash table is empty.
 */
public class EmptyHashTableException extends HashTableException {
    public EmptyHashTableException(){}

    public EmptyHashTableException(String message){
        super(message);
        System.out.println(message);
    }
}
