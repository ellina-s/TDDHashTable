package main.java;

/**
 * Custom exception for the ZipcodeLinkedList class.
 * Thrown when a string is empty.
 */
public class EmptyStringException extends LinkedListException{
    public EmptyStringException(){}

    public EmptyStringException(String message){
        super(message);
        System.out.println(message);
    }
}
