package test.java.HashTableTests;

import main.java.HashTable;
import main.java.exceptions.hashtable.DuplicateItemException;
import main.java.exceptions.linkedlist.EmptyStringException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Helper methods for the HashTable class unit tests.
 */
public class HashTableTestUtilities {

    /*
    Reference: ASCII printable code chart, Wikipedia, 2015.
    URL: https://en.wikipedia.org/wiki/ASCII#ASCII_printable_code_chart
     */
    private static final int ASCII_UPPERCASE_M = 77;
    private static final int ASCII_LOWERCASE_E = 101;
    private static final int ASCII_LOWERCASE_X = 120;
    private static final int ASCII_LOWERCASE_I = 105;
    private static final int ASCII_LOWERCASE_C = 99;
    private static final int ASCII_LOWERCASE_O = 111;

    private static final String CITY_ALMATY = "Almaty";
    private static final String ZIPCODE_ALMATY = "A506791";
    private static final String CITY_TORONTO = "Toronto";
    private static final String ZIPCODE_TORONTO = "T3K8V1";
    private static final String CITY_TOKYO = "Tokyo";
    private static final String ZIPCODE_TOKYO = "6748J45";
    private static final String CITY_SINGAPORE = "Singapore";
    private static final String ZIPCODE_SINGAPORE = "S573824";

    private static final String HASH_KEY_METHOD_NAME = "hashKeyOf";

    public static void insertTorontoAndAlmaty(HashTable hashTable) throws EmptyStringException, DuplicateItemException {
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
    }

    public static void insertAlmatyAndTokyo(HashTable hashTable) throws EmptyStringException, DuplicateItemException {
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
    }

    public static void insertFourCities(HashTable hashTable) throws EmptyStringException, DuplicateItemException {
        hashTable.insert(CITY_ALMATY, ZIPCODE_ALMATY);
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
        hashTable.insert(CITY_TORONTO, ZIPCODE_TORONTO);
        hashTable.insert(CITY_SINGAPORE, ZIPCODE_SINGAPORE);
    }

    public static int getAsciiEquivalentOfMexico(){
        return ASCII_UPPERCASE_M + ASCII_LOWERCASE_E +ASCII_LOWERCASE_X +
                ASCII_LOWERCASE_I + ASCII_LOWERCASE_C + ASCII_LOWERCASE_O;
    }

    public static HashTable insertTokyoInHashTable() throws EmptyStringException, DuplicateItemException {
        HashTable hashTable = new HashTable();
        hashTable.insert(CITY_TOKYO, ZIPCODE_TOKYO);
        return hashTable;
    }

    /**
     * A helper method to execute a reflected private method and return its return value.
     *
     * @param methodName name of a method to be reflected and executed
     * @param parameter a String parameter that will be passed to the method
     * @param instance an instance of the class on which the method will be invoked
     * @return an Object that contains data returned by the executed method
     * @throws HashTableUtilException is thrown when execution failed
     * Reference: JUnit testing private fields and methods
     * URL: http://www.jroller.com/CoBraLorD/entry/junit_testing_private_fields_and
     */
    public static Object execute(String methodName, String parameter, HashTable instance) throws HashTableUtilException {

        try{
            Method method = getReflectedMethod(methodName, instance);
            method.setAccessible(true);
            Object callParameters[] = new Object[] { parameter };
            return method.invoke(instance, callParameters);
        }
        catch(IllegalAccessException | InvocationTargetException e) {
            System.out.println(e);
            throw new HashTableUtilException("Failed to execute " + methodName);
        }
    }

    /**
     * Return a reflection of a specified method that is declared in a specified HashTable instance.
     * Assumed that a specified method has 1 parameter - a String (as in hashKeyOf and convertToAscii methods)
     * @param methodName name of the method to be reflected and returned
     * @param instance an instance of the class where method is declared
     * @return a Method object that contains a reflected method
     * @throws HashTableUtilException is thrown when reflection failed.
     */
    public static Method getReflectedMethod(String methodName, HashTable instance) throws HashTableUtilException {

        Class hashTableClass = instance.getClass();
        // Prepare method parameters that contain 1 String (as in hashKeyOf and convertToAscii methods)
        Class methodParameter[] = new Class[1];
        methodParameter[0] = String.class;

        try {
            return hashTableClass.getDeclaredMethod(methodName, methodParameter);
        }
        catch(NoSuchMethodException e){
            System.out.println(e);
            throw new HashTableUtilException("Failed to reflect " + methodName);
        }
    }

    /**
     * Retrieve a hash key of a given city through reflection.
     * @param city name of the city
     * @param instance an instance of the HashTable class needed for reflection
     * @return a hash key of the city
     * @throws HashTableUtilException is thrown if failed to retrieve a hash key.
     */
    public static int getHashKeyOf(String city, HashTable instance) throws HashTableUtilException {
        try{
            Integer hashValue = (Integer) execute(HASH_KEY_METHOD_NAME, city, instance);
            return hashValue.intValue();
        }
        catch(HashTableUtilException e) {
            System.out.println(e);
            throw new HashTableUtilException("Failed to retrieve a hash key for city: " + city);
        }
    }
}
