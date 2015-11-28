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
        if(isEmpty() == true){
            System.out.println("This linked list is empty.");
            return;
        }
        Node pointer = head;
        System.out.println("This linked list contains:");
        while(pointer != null){
            System.out.println("City: " + pointer.key + ", Zipcode: " + pointer.value);
            pointer = pointer.next;
        }
        System.out.println("The end.");
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
            throw new EmptyLinkedListException("There are no nodes in an empty linked list.");
        }
        else if(index <= 0 || index > count){
            throw new InvalidIndexException("Requested index "+index+" is invalid.");
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
            throw new EmptyLinkedListException("Cannot delete an empty linked list.");
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
            throw new EmptyLinkedListException("Cannot delete a node from an empty linked list.");
        }
        else if(index <= 0 || index > count){
            throw new InvalidIndexException("Requested index "+index+" is invalid");
        }
        else if(1 == index){
            System.out.println("Deleting (head): " + head.key + " " + head.value);
            head = head.next;
            count--;
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
            previous.next = pointer.next;
            count--;
            if(pointer.next == null){
                tail = previous;
                System.out.println("Updating the tail. The new tail is " + tail.key + " " + tail.value);
            }
        }

    }

    /**
     * Show content of a node at the given index by printing it to console
     * @param index an index of the node to be shown, where 1 is an index of the first node
     */
    public void showNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException{
        if( isEmpty() == true){
            throw new EmptyLinkedListException("Cannot display a node from an empty list.");
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
            System.out.println("City: " + pointer.key + ", Zipcode: " + pointer.value);
        }
    }

    /**
     * Delete a node with the given city name
     * @param targetCity name of the city whose node will be deleted from the linked
    list
     */
    public void deleteCity(String targetCity) throws EmptyLinkedListException, ItemNotFoundException{
        if(isEmpty() == true){
            throw new EmptyLinkedListException("Cannot delete a node from an empty list.");
        }

        Node pointer = head;
        Node previousNode = null;
        while(pointer != null){
            if(pointer.key == targetCity){

                if(pointer.key == head.key){
                    System.out.println("Deleting (head) " + pointer.key + " " + pointer.value);
                    head = head.next;
                    count--;
                    return;
                }

                System.out.println("Deleting " + pointer.key + " " + pointer.value);
                previousNode.next = pointer.next;
                count--;

                if(pointer.next == null) {
                    tail = previousNode;
                    System.out.println("Updating the tail. The new tail is " + tail.key + " " + tail.value);
                }
                return;
            }
            previousNode = pointer;
            pointer = pointer.next;
        }
        throw new ItemNotFoundException(targetCity + " is not found, and cannot be deleted.");
    }

    /**
     * Get an index of a given city
     * @param city the city whose index is retrieved
     * @return index of the city
     */
    public String getCityIndex(String city) throws EmptyLinkedListException, ItemNotFoundException{
        if(isEmpty() == true){
            throw new EmptyLinkedListException("There are no cities in an empty linked list.");
        }
        Node pointer = head;
        while(pointer != null){
            if(pointer.key == city){
                System.out.println("Found the city of " + pointer.key + " with zipcode: " + pointer.value);
                return pointer.value;
            }
            pointer = pointer.next;
        }
        throw new ItemNotFoundException(city + " is not found in the linked list.");
    }

    /**
     * Check if the given city is already in the linked list.
     * @param city the name of the city
     * @return True if the city is in the list (any first occurence in the list). Otherwise, false.
     * Return false if the linked list is empty.
     */
    public boolean checkForDuplicatesOf(String city) throws NullPointerException, EmptyStringException{
        if(city == null){
            throw new NullPointerException("The name of the city cannot be null.");
        }
        if(city == ""){
            throw new EmptyStringException("Invalid argument: empty string");
        }
        if(isEmpty()){
            System.out.println("There are no duplicates in an empty linked list.");
            return false;
        }
        else{
            Node pointer = head;
            while(pointer != null){
                if(pointer.key == city){
                    System.out.println("Found a duplicate: " + pointer.key);
                    return true;
                }
                pointer = pointer.next;
            }
            System.out.println("No duplicates of " + city + " are found.");
            return false;
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
