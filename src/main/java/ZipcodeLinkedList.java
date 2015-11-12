package main.java;

/**
 * An implementation of a linked list class that holds zipcodes.
 * The class is implemented via TDD.
 * See ZipcodeLinkedListTest.java for corresponding tests.
 */
public class ZipcodeLinkedList {

    public Node head;
    public Node tail;
    public int count;

    private static final int CITY = 0;
    private static final int ZIPCODE = 1;

    /**
     * Constructor
     * Note: Initializing field variables is a good programming style,
     * as it avoids relying on default values assigned by a compiler to field variables.
     * Reference: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
     */
    public ZipcodeLinkedList(){
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Check if the linked list is empty.
     * @return If it's empty, return True. Otherwise, return False.
     */
    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        else{
            return false;
        }
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
     * Add a node to the end of the linked list.
     * Increase the count of the nodes by one.
     * Adjust the head to point to the first element in the list.
     * Adjust the tail on every element added.
     * @param key key of the node
     * @param value value of the node
     */
    public void addNode(String key, String value){
        Node newNode = new Node(key, value, null);
        if(count == 0 && tail == null && head == null){
            // Adjust head and tail to the first node added to the linked list.
            tail = newNode;
            head = newNode;
        }
        else{
            if(tail.next == null){
                tail.next = newNode;
                tail = newNode;
            }
        }
        count++;
    }

    /**
     * Return the size of the linked list
     * @return size, i.e. number of nodes in the linked list
     */
    public int size(){
        return count;
    }

    /**
     * Retrieve contents of a node at the given index
     * @param index index (from 1 to the size of the Linked List) of the node to be retrieved
     * @return city name and zipcode in a String array, at indexes 0 and 1 respectively
     */
    public String[] getNodeAtIndex(int index) throws EmptyLinkedListException{
        if(isEmpty() == true){
            throw new EmptyLinkedListException("The linked list is empty. Index " + index);
        }
        else{
            String[] data = new String[2];
            Node pointer = head;
            int stop = index - 1;
            for(int i = 0; i<stop; i++){
                if(pointer.next != null){
                    pointer = pointer.next;
                }
            }
            data[CITY] = pointer.key;
            data[ZIPCODE] = pointer.value;
            return data;
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
