package test.java;

import main.java.ZipcodeLinkedList;
import main.java.exceptions.linkedlist.EmptyStringException;

/**
 * Helper methods for ZipcodeLinkedList unit tests.
 */
public class ZipcodeLinkedListTestUtilities {

    private static final String CITY_MEXICO = "Mexico";
    private static final String ZIPCODE_MEXICO = "M58RP8D";
    private static final String CITY_TOKYO = "Tokyo";
    private static final String ZIPCODE_TOKYO = "Y6E88941";
    private static final String CITY_LONDON = "London";
    private static final String ZIPCODE_LONDON = "L84765F2";
    private static final String CITY_NEWYORK = "New York";
    private static final String ZIPCODE_NEWYORK = "NY16462";
    private static final String CITY_TORONTO = "Toronto";
    private static final String ZIPCODE_TORONTO = "T4G5W6";

    public static void insertTokyoNewYorkLondon(ZipcodeLinkedList list) throws EmptyStringException {
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }

    public static void insertNewYorkLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }

    public static void insertTokyoLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }

    public static void insertNewYorkLondonMexicoTokyo(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_NEWYORK, ZIPCODE_NEWYORK);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
        list.addNode(CITY_MEXICO, ZIPCODE_MEXICO);
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
    }

    public static void insertTokyoTorontoLondon(ZipcodeLinkedList list) throws EmptyStringException{
        list.addNode(CITY_TOKYO, ZIPCODE_TOKYO);
        list.addNode(CITY_TORONTO, ZIPCODE_TORONTO);
        list.addNode(CITY_LONDON, ZIPCODE_LONDON);
    }
}
