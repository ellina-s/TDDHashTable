package main.java.exceptions.linkedlist;

import main.java.exceptions.linkedlist.LinkedListException;

/**
 * Custom exception for the ZipcodeLinkedList class.
 * Thrown when an index is zero or negative,
 * or more than the number of nodes in the linked list.
 */
public class InvalidIndexException extends LinkedListException {

    public InvalidIndexException(){}

    public InvalidIndexException(String message){
        super(message);
        System.out.println(message);
    }
}
