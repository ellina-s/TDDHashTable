package test.java.HashTableTests;

/**
 * Custom exception to indicate issues thrown by helper methods from the HashTableTestUtilities class.
 */
public class HashTableUtilException extends Exception {

    public HashTableUtilException(){}

    public HashTableUtilException(String message){
        super(message);
        System.out.println(message);
    }
}
