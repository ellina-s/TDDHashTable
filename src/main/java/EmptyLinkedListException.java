package main.java;

/**
 * Custom exception for the ZipcodeLinkedList class.
 * Thrown when a linked list is empty.
 */
public class EmptyLinkedListException extends LinkedListException {

    public EmptyLinkedListException(){}

    public EmptyLinkedListException(String message){
        super(message);
        System.out.println(message);
    }
}
