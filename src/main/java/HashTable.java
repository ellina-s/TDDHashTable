package main.java;

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

    /**
     * Insert an item in a hash table
     * @param key key of the item
     * @param value value of the item
     * @throws EmptyStringException
     * @throws NullPointerException
     */
    public void insert(String key, String value) throws EmptyStringException, NullPointerException, DuplicateItemException{
        if(key == "" || value == ""){
            throw new EmptyStringException("Key and value cannot be empty.");
        }
        if(key == null || value == null){
            System.out.println("Key and value cannot be null.");
            throw new NullPointerException("Key and value cannot be null.");
        }
        int hashKey = hash(key);
        if(hashTable[hashKey] == null){
            System.out.println("This slot is null. Initializing a linked list and adding a node...");
            hashTable[hashKey] = new ZipcodeLinkedList();
            hashTable[hashKey].addNode(key, value);
            if(isEmpty == true){
                isEmpty = false;
            }
        }
        else{
            System.out.println("This slot is not empty. Checking for duplicates...");
            if( hashTable[hashKey].checkForDuplicatesOf(key) == false){
                hashTable[hashKey].addNode(key, value);
            }
            else{
                throw new DuplicateItemException("Duplicate item is detected. Cannot insert a duplicate.");
            }
        }
        return;
    }

    /**
     * Search for a given city and return its zipcode.
     * @param city name of the city
     * @return a zipcode of the given city
     * @throws EmptyHashTableException is thrown if a hash table is empty.
     * @throws ItemNotFoundException is thrown if the city is not found.
     * @throws EmptyStringException is thrown is the city parameter is empty.
     */
    public String search(String city) throws EmptyHashTableException, ItemNotFoundException, EmptyStringException, EmptyLinkedListException{
        if(city == ""){
            throw new EmptyStringException("City name cannot be empty.");
        }
        if(city == null){
            System.out.println("City name cannot be null.");
            throw new NullPointerException("City name cannot be null.");
        }
        if(isEmpty){
            throw new EmptyHashTableException("There are no items in an empty hash table.");
        }
        int hashKey = hash(city);
        if(hashTable[hashKey] == null){
            System.out.println("Slot of " + city + " is null.");
            throw new ItemNotFoundException(city + " is not found.");
        }
        else{
            System.out.println("This slot is not empty. Searching for the given city...");
            try{
                return hashTable[hashKey].getCityZipcode(city);
            }
            catch(ItemNotFoundException e){
                throw new ItemNotFoundException(city + " is not found in the hash table.");
            }
            catch (Exception e){
                System.out.println("HashTable caught " + e);
                throw e;
            }
        }
    }

    /**
     * Compute a hash key of the provided key
     * by (1) computing an ASCII value of the key,
     * and (2) computing a reminder of the division by the size of a hash table.
     * A hash key is used as an index into a hash table.
     * @param key key of an item
     * @return hash key corresponding to the given key
     */
    public int hash(String key){
        return convertToAscii(key) % size;
    }
}
