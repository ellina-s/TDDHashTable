package main.java;

/**
 * DataNodes are meant to hold linked list's node data - key and value -
 * without exposing a pointer to the next node in a linked list.
 */
public class DataNode {

    private String key;
    private String value;

    /* Constructor */
    public DataNode(){}

    /**
     * Populate a DataNode with the given key and value.
     */
    public void populateWith(String key, String value){
        this.key = key;
        this.value = value;
    }

    /* Getters */

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
