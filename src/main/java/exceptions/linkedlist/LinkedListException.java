package main.java.exceptions.linkedlist;

/**
 * Custom parent Exception class for the ZipcodeLinkedList class
 */
public class LinkedListException extends Exception {

    public LinkedListException(){}

    public LinkedListException(String message){
        super(message);
    }
}
