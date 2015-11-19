package main.java;

/**
 * Custom exception for the ZipcodeLinkedList class.
 * Thrown when an item is not found in the linked list.
 */
public class ItemNotFoundException extends LinkedListException {

    public ItemNotFoundException(){}

    public ItemNotFoundException(String message){
        super(message);
        System.out.println(message);
    }
}
