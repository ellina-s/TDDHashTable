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
    public String[] getNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException{
        if(isEmpty() == true){
            throw new EmptyLinkedListException("The linked list is empty. Requested ndex " + index);
        }
        else if(index <= 0 || index > count){
            throw new InvalidIndexException("Requested index "+index+" is invalid");
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
     * Delete all nodes in the linked list (by setting its head and tail to zero.
     * Also, the counter is set to zero to reflect the fact that there are no nodes any more).
     * @throws EmptyLinkedListException
     */
    public void deleteLinkedList() throws EmptyLinkedListException{
        if(isEmpty() == true){
            throw new EmptyLinkedListException("There are no node to delete in the linked list, because it is empty.");
        }
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Delete a node at the given index
     * @param index index of the node to be deleted, where 1 is the first node
     * @throws EmptyLinkedListException
     */
    public void deleteNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException{
        if( isEmpty() == true){
            throw new EmptyLinkedListException("Cannot delete a node in an empty list.");
        }
        else if(index <= 0 || index > count){
            throw new InvalidIndexException("Requested index "+index+" is invalid");
        }
        else if(1 == index){
            System.out.println("Deleting head: "+ head.key + " " + head.value);
            head = head.next;
            return;
        }
        else{
            Node pointer = head;
            Node previous = null;
            int stop = index - 1;
            for(int i = 0; i<stop; i++){
                if(pointer.next != null){
                    previous = pointer;
                    pointer = pointer.next;
                }
            }
            System.out.println("Deleting " + pointer.key + " " + pointer.value);
            System.out.println("Before deleting tail is " + tail.key + " " + tail.value);
            previous.next = pointer.next;
            if(pointer.next == null){
                System.out.println("This is a terminal node");
                tail = previous;
                System.out.println("After deleting tail is " + tail.key + " " + tail.value);
            }
        }

    }

    /**
     * Show content of a node at the given index by printing it to console
     * @param index an index of the node to be shown, where 1 is an index of the first node
     */
    public void showNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException{
        if( isEmpty() == true){
            throw new EmptyLinkedListException("Cannot show a node from an empty list.");
        }
        else if(index <= 0 || index > count){
            throw new InvalidIndexException("Requested index "+index+" is invalid");
        }
        else{
            Node pointer = head;
            int stop = index - 1;
            for(int i = 0; i<stop; i++){
                if(pointer.next != null){
                    pointer = pointer.next;
                }
            }
            System.out.println("Showing " + pointer.key + " " + pointer.value);
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
