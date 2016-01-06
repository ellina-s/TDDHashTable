package main.java;

import main.java.exceptions.hashtable.DuplicateItemException;
import main.java.exceptions.hashtable.EmptyHashTableException;
import main.java.exceptions.hashtable.HashTableException;
import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.ItemNotFoundException;

/**
 * An implementation of a hash table class that holds city zipcodes.
 * The class is implemented via Test-Driven Development.
 * See HashTableTest.java for corresponding tests.
 */
public class HashTable {

    private int size;
    private boolean isEmpty;
    public ZipcodeLinkedList[] hashTable;

    private static final int NUM_SLOTS = 47;

    /**
     * Hash table constructor
     */
    public HashTable(){
        size = NUM_SLOTS;
        isEmpty = true;
        hashTable = new ZipcodeLinkedList[size];
    }

    /**
     * Check if a hash table is empty.
     * @return True if a hash table is empty. False, otherwise.
     */
    public boolean isEmpty(){
        for(int i = 0; i < size; i++){
            if(hashTable[i] != null && hashTable[i].isEmpty() == false){
                isEmpty = false;
                return false;
            }
        }
        return true;
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

    /**
     * Insert an item in a hash table
     * @param key key of the item
     * @param value value of the item
     * @throws EmptyStringException
     * @throws NullPointerException
     */
    public void insert(String key, String value) throws EmptyStringException, NullPointerException, DuplicateItemException {
        validateArguments(key, value);
        insertNewItem(key, value);
        return;
    }

    /**
     * Search for a given city and return its zipcode.
     * @param city name of the city
     * @return a zipcode of the given city
     * @throws EmptyHashTableException is thrown if a hash table is empty.
     * @throws ItemNotFoundException is thrown if the city is not found.
     * @throws EmptyStringException is thrown if the city parameter is empty.
     */
    public String search(String city) throws EmptyHashTableException, ItemNotFoundException, EmptyStringException, EmptyLinkedListException {
        validateCityArgument(city);
        checkThatHashTableIsNotEmpty();
        return searchForTheCity(city);
    }

    /**
     * Compute a hash key of the provided key
     * by (1) computing an ASCII value of the key,
     * and (2) computing a reminder of dividing the ASCII value by the size of a hash table.
     * A hash key is used as an index into a hash table.
     * @param key key of an item
     * @return hash key corresponding to the given key
     */
    public int hash(String key){
        return convertToAscii(key) % size;
    }

    /**
     * Delete a given city from a hash table, if the city exists in the hash table.
     * If such city is not found in the hash table, then throw ItemNotFound exception.
     * @param city is a name of the city to be deleted.
     * @throws EmptyHashTableException is thrown if a hash table is empty.
     * @throws EmptyStringException is thrown is the city parameter is empty.
     * @throws ItemNotFoundException is thrown if the city is not found.
     * @throws EmptyLinkedListException is caught and re-thrown as ItemNotFoundException.
     */
    public void delete (String city) throws EmptyHashTableException, EmptyStringException, ItemNotFoundException, EmptyLinkedListException{
        if(city == ""){
            throw new EmptyStringException("City name cannot be empty.");
        }
        if(city == null){
            System.out.println("City name cannot be null.");
            throw new NullPointerException("City name cannot be null.");
        }
        if(isEmpty){
            throw new EmptyHashTableException("Cannot delete an item from an empty hash table.");
        }
        // Compute hash key of the given city:
        int hashKey = hash(city);
        if(hashTable[hashKey] == null){
            throw new ItemNotFoundException(city + " is not found.");
        }
        else{
            try{
                hashTable[hashKey].deleteCity(city);
            }
            catch(ItemNotFoundException e){
                throw new ItemNotFoundException(city + " is not found and cannot be deleted.");
            }
            catch (EmptyLinkedListException e){
                /* Re-throw this exception as ItemNotFoundException to hide hash table's implementation
                and to emphasize the fact the city is not found. */
                throw new ItemNotFoundException(city + " is not found and cannot be deleted.");
            }
            catch (Exception e){
                throw e;
            }
        }
    }

    /**
     * Insert an item that doesn't collide with other items in a hash table.
     * This item goes into an empty slot of a hash table.
     * @param key key of the item
     * @param value value of the item
     * @param hashKey hash key corresponding to the item
     * @throws EmptyStringException
     */
    private void insertNonCollidingItem(String key, String value, int hashKey) throws EmptyStringException{
        System.out.println("This slot has never been occupied. Adding an item to it...");
        hashTable[hashKey] = new ZipcodeLinkedList();
        hashTable[hashKey].addNode(key, value);
        if(isEmpty == true){
            isEmpty = false;
        }
    }

    /**
     * Insert a colliding item in the hash table if there are no duplicates of the given item.
     * This item goes in a slot that is (or was) occupied by other items colliding with the given item.
     * @param key key of the item
     * @param value value of the item
     * @param hashKey hash key corresponding to the item
     * @throws EmptyStringException
     * @throws DuplicateItemException
     */
    private void insertCollidingItem(String key, String value, int hashKey) throws EmptyStringException, DuplicateItemException{
        System.out.println("This slot is or was previously occupied. Checking for duplicates...");
        if( hashTable[hashKey].checkForDuplicatesOf(key) == false){
            hashTable[hashKey].addNode(key, value);
        }
        else{
            throw new DuplicateItemException("Duplicate item is detected. Cannot insert a duplicate.");
        }
    }

    /**
     * A helper method to insert a new item in the hash table
     * @param key key of the item
     * @param value value of the item
     * @throws EmptyStringException
     * @throws DuplicateItemException
     */
    private void insertNewItem(String key, String value) throws EmptyStringException, DuplicateItemException{
        int hashKey = hash(key);
        if(hashTable[hashKey] == null){
            insertNonCollidingItem(key, value, hashKey);
        }
        else{
            insertCollidingItem(key, value, hashKey);
        }
    }

    /**
     * Validate arguments to make sure they are not null or empty strings.
     * @param key key of the item
     * @param value value of the item
     * @throws EmptyStringException is thrown if key or value arguments are empty.
     */
    private void validateArguments(String key, String value) throws EmptyStringException{
        if(key == "" || value == ""){
            throw new EmptyStringException("Key and value cannot be empty.");
        }
        if(key == null || value == null){
            System.out.println("Key and value cannot be null.");
            throw new NullPointerException("Key and value cannot be null.");
        }
    }

    /**
     * Validate the city argument to make sure it is not empty or null.
     * @param city name of the city
     * @throws EmptyStringException is thrown if the city string is empty.
     */
    private void validateCityArgument(String city) throws EmptyStringException{
        if(city == ""){
            throw new EmptyStringException("City name cannot be empty.");
        }
        if(city == null){
            System.out.println("City name cannot be null.");
            throw new NullPointerException("City name cannot be null.");
        }
    }

    /**
     * A helper method to check if the hash table is not empty.
     * @throws EmptyHashTableException is thrown if the hash table is empty.
     */
    private void checkThatHashTableIsNotEmpty() throws EmptyHashTableException{
        if(isEmpty){
            throw new EmptyHashTableException("There are no items in an empty hash table.");
        }
    }

    /**
     * A helper method to search for the city.
     * @param city name of the city
     * @return zipcode of the city
     * @throws ItemNotFoundException is thrown if the city is not found.
     * @throws EmptyStringException
     */
    private String searchForTheCity(String city) throws ItemNotFoundException, EmptyStringException{
        int hashKey = hash(city);
        if(hashTable[hashKey] == null){
            throw new ItemNotFoundException(city + " is not found.");
        }
        else{
            return searchForCityZipcode(hashKey, city);
        }
    }

    /**
     * Search for and return the city's zipcode.
     * @param hashKey a hash key corresponding to the city
     * @param city name of the city
     * @return a zipcode of the city
     * @throws ItemNotFoundException is thrown if the city is not found.
     * @throws EmptyStringException
     */
    private String searchForCityZipcode(int hashKey, String city) throws ItemNotFoundException, EmptyStringException{
        try{
            return hashTable[hashKey].getCityZipcode(city);
        }
        catch(ItemNotFoundException | EmptyLinkedListException e){
            throw new ItemNotFoundException(city + " is not found in the hash table.");
        }
        catch (Exception e){
            System.out.println("HashTable caught " + e);
            throw e;
        }
    }
}
