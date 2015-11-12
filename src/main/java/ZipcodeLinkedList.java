package main.java;

/**
 * An implementation of a linked list class that holds zipcodes.
 * The class is implemented via TDD.
 * See ZipcodeLinkedListTest.java for corresponding tests.
 */
public class ZipcodeLinkedList {

    public Node head;
    public int count;

    /**
     * Constructor
     * Note: Initializing field variables is a good programming style,
     * as it avoids relying on default values assigned by a compiler to field variables.
     * Reference: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
     */
    public ZipcodeLinkedList(){
        head = null;
        count = 0;
    }

    /**
     * Add a new node after the head with the given content to the linked list
     * @param key key of the node
     * @param value value of the node
     */
    public void addToHead(String key, String value){
        Node newNode = new Node (key, value, head);
        head = newNode;
        count++;
    }

    /**
     * Show to the contents of the linked list starting from the head
     * Reference: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/linked%20lists.html
     */
    public void showContentsOfTheLinkedList(){
        Node pointer = head;
        while(pointer != null){
            System.out.println(pointer.key + " " + pointer.value);
            pointer = pointer.next;
        }
    }

    /**
     * A class for a node of a linked list
     */
    public class Node{

        public String key;
        public String value;
        public Node next;

        /**
         * Constructor for the Node
         */
        public Node(String key, String value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
