package test.java.HashTableTests;

import main.java.HashTable;
import main.java.exceptions.hashtable.DuplicateItemException;
import main.java.exceptions.linkedlist.EmptyStringException;

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
}
