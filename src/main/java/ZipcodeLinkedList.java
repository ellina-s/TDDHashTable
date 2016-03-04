package main.java;

import main.java.exceptions.linkedlist.EmptyLinkedListException;
import main.java.exceptions.linkedlist.EmptyStringException;
import main.java.exceptions.linkedlist.InvalidIndexException;
import main.java.exceptions.linkedlist.ItemNotFoundException;

/**
 * An implementation of a linked list class that holds zipcodes.
 * The class is implemented via TDD.
 * See ZipcodeLinkedListTest.java for corresponding tests.
 */
public class ZipcodeLinkedList {

    public Node head;
    public Node tail;
    private int count;

    private static final String MESSAGE_EMPTY_LINKED_LIST = "There are no nodes in an empty linked list.";
    private static final String MESSAGE_CANNOT_DELETE_EMPTY_LINKED_LIST = "Cannot delete an empty linked list.";
    private static final String MESSAGE_CANNOT_DELETE_NODE = "Cannot delete a node from an empty linked list.";
    private static final String MESSAGE_CANNOT_DISPLAY_NODE = "Cannot display a node from an empty linked list.";
    private static final String MESSAGE_NO_CITIES = "There are no cities in an empty linked list.";

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
     * @return True, if it's empty. Otherwise, return False.
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Get the value of the count field.
     */
    public int getCount(){
        return count;
    }

    /**
     * Show to the contents of the linked list starting from the head
     */
    public void showContentsOfTheLinkedList(){
        if(isEmpty()){
            System.out.println("This linked list is empty.");
            return;
        }
        showNodesOfTheLinkedList();
    }

    /**
     * A helper method to show all nodes of a linked list by iterating over it.
     * Reference: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/linked%20lists.html
     */
    private void showNodesOfTheLinkedList(){
        Node pointer = head;
        System.out.println("This linked list contains:");
        while(pointer != null){
            System.out.println("City: " + pointer.key + ", Zipcode: " + pointer.value);
            pointer = pointer.next;
        }
        System.out.println("The end.");
    }

    /**
     * Show contents of a linked list in a mute mode.
     * Similar to showContentsOfTheLinkedList(), but without extra print out statements.
     */
    public void mutelyShowContentsOfTheLinkedList(){
        if(isEmpty()){
            return;
        }
        mutelyShowNodesOfTheLinkedList();
    }

    /**
     * A helper method to mutely show contents of all nodes in a linked list.
     */
    private void mutelyShowNodesOfTheLinkedList(){
        Node pointer = head;
        while(pointer != null){
            System.out.println("City: " + pointer.key + ", Zipcode: " + pointer.value);
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
    public void addNode(String key, String value) throws EmptyStringException {
        validateArguments(key, value);
        if(addingFirstNodeToEmptyLinkedList()){
            appendNewNodeToEmptyLinkedList(key, value);
        }
        else{
            appendNewNodeToTail(key, value);
        }
        count++;
    }

    private boolean addingFirstNodeToEmptyLinkedList(){
        return count == 0 && tail == null && head == null;
    }

    /**
     * Add a new node to the empty linked list by adjusting head and tail to that new node.
     */
    private void appendNewNodeToEmptyLinkedList(String key, String value){
        Node newNode = new Node(key, value, null);
        tail = newNode;
        head = newNode;
        System.out.println("Added " + key);
    }

    private void appendNewNodeToTail(String key, String value){
        Node newNode = new Node(key, value, null);
        tail.next = newNode;
        tail = newNode;
        System.out.println("Added " + key);
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
    public DataNode getNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException {
        validateLinkedListIsNotEmpty(MESSAGE_EMPTY_LINKED_LIST);
        validateRequestedIndex(index);
        return nodeAtIndex(index);
    }

    /**
     * A helper method to get a node at the specified index.
     * @param index of the node to be retrieved
     * @return a DataNode that contains key and value of a linked list's node specified by the index.
     * If index = 1, return the head's data. If index = count, return the tail's data.
     * Otherwise, return data of the node at the given index.
     */
    private DataNode nodeAtIndex(int index) {
        DataNode data = new DataNode();
        if(index == 1){
            data.populateWith(head.key, head.value);
        }
        else if(index == count){
            data.populateWith(tail.key, tail.value);
        }
        else{
            Node requestedNode = getRequestedNodeAtIndex(index);
            data.populateWith(requestedNode.key, requestedNode.value);
        }
        return data;
    }

    /**
     * A helper method to get the node at the requested index.
     */
    private Node getRequestedNodeAtIndex (int index){
        Node pointer = head;
        int stop = index - 1;
        for(int i = 0; i<stop; i++){
            if(pointer.next != null){
                pointer = pointer.next;
            }
        }
        return pointer;
    }

    /**
     * Delete all nodes in the linked list (by setting its head and tail to zero.
     * Also, the counter is set to zero to reflect the fact that there are no nodes any more).
     * @throws EmptyLinkedListException
     */
    public void clearTheLinkedList() throws EmptyLinkedListException{
        validateLinkedListIsNotEmpty(MESSAGE_CANNOT_DELETE_EMPTY_LINKED_LIST);
        head = null;
        tail = null;
        count = 0;
        System.out.println("The linked list is cleared.");
    }

    /**
     * Delete a node at the given index
     * @param index index of the node to be deleted, where 1 is the first node
     * @throws EmptyLinkedListException
     */
    public void deleteNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException{
        validateLinkedListIsNotEmpty(MESSAGE_CANNOT_DELETE_NODE);
        validateRequestedIndex(index);
        if(1 == index){
            deleteHead();
        }
        else{
            deleteRequestedNodeAt(index);
        }
    }

    /**
     * Delete the head of a linked list. Update the head and tail accordingly.
     */
    private void deleteHead(){
        System.out.println("Deleting (head): " + head.key + " " + head.value);
        updateTailIfDeletingTheOnlyNode();
        head = head.next;
        decreaseCount();
    }

    /**
     * Update tail if needed (i.e. if deleting the head, which happens to be the only node
     * in a linked list. In that case, tail should be updated as well).
     */
    private void updateTailIfDeletingTheOnlyNode(){
        if(tailNeedsToBeUpdated()){
            tail = tail.next;
        }
    }

    /**
     * Decrease count by 1.
     */
    private void decreaseCount(){
        count--;
    }

    /**
     * Check if the tail needs to be updated if the node being deleted is the only node in the list.
     */
    private boolean tailNeedsToBeUpdated(){
        return head.key.equals(tail.key) && count == 1;
    }

    /**
     * A helper method to delete a node at the requested index.
     * @param index index of the node to be deleted.
     * Index should be >= 2. The case when Index = 1 is covered by deleteHead().
     */
    private void deleteRequestedNodeAt(int index){
        Node previousNode = getNodePriorToTargetNode(index);
        Node targetNode = previousNode.next;
        System.out.println("Deleting " + targetNode.key + " " + targetNode.value);
        previousNode.next = targetNode.next;
        decreaseCount();
        updateTailIfNeeded(targetNode, previousNode);
    }

    /**
     * A helper method to get a node that precedes the node to be deleted.
     * @param index index of the node to be deleted
     */
    private Node getNodePriorToTargetNode(int index) {
        int stop = index - 1;
        Node priorNode = head;
        for(int i = 1; i<stop; i++){
            priorNode = priorNode.next;
        }
        return priorNode;
    }

    /**
     * A helper method to update the tail of the linked list, if the tail has been deleted.
     * A node prior to the deleted node becomes a new tail.
     */
    private void updateTailIfNeeded(Node deletedNode, Node previousToDeletedNode){
        if(deletedNode.next == null){
            tail = previousToDeletedNode;
            System.out.println("Updating the tail. The new tail is " + tail.key + " " + tail.value);
        }
    }

    /**
     * Show content of a node at the given index by printing it to console
     * @param index an index of the node to be shown, where 1 is an index of the first node
     */
    public void showNodeAtIndex(int index) throws EmptyLinkedListException, InvalidIndexException{
        validateLinkedListIsNotEmpty(MESSAGE_CANNOT_DISPLAY_NODE);
        validateRequestedIndex(index);
        if(index == 1){
            System.out.println("City: " + head.key + ", Zipcode: " + head.value);
            return;
        }
        else if(index == count){
            System.out.println("City: " + tail.key + ", Zipcode: " + tail.value);
            return;
        }
        Node requestedNode = getRequestedNodeAtIndex(index);
        System.out.println("City: " + requestedNode.key + ", Zipcode: " + requestedNode.value);
    }

    /**
     * Delete a node with the given city name
     * @param targetCity name of the city whose node will be deleted from the linked
    list
     */
    public void deleteCity(String targetCity) throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        validateCityArgument(targetCity);
        validateLinkedListIsNotEmpty(MESSAGE_CANNOT_DELETE_NODE);
        if(targetCity.equals(head.key)){
            deleteHead();
            return;
        }
        deleteRequestedCityIfFound(targetCity);
    }

    /**
     * A helper method to delete a requested city if it's found.
     * @throws ItemNotFoundException is thrown if the city is not found.
     */
    private void deleteRequestedCityIfFound(String city) throws ItemNotFoundException{
        Node targetNode = head;
        Node previousNode = null;
        while(targetNode != null){
            if(targetNode.key.equals(city)){
                deleteCorrespondingCityNode(targetNode, previousNode);
                return;
            }
            previousNode = targetNode;
            targetNode = targetNode.next;
        }
        throw new ItemNotFoundException(city + " is not found, and cannot be deleted.");
    }

    /**
     * A helper method to delete a target node that corresponds to a city identified by deleteRequestedCityIfFound().
     * @param targetNode a node to be deleted
     * @param previousNode a node prior to the target node
     */
    private void deleteCorrespondingCityNode(Node targetNode, Node previousNode){
        System.out.println("Deleting " + targetNode.key + " " + targetNode.value);
        previousNode.next = targetNode.next;
        decreaseCount();
        updateTailIfNeeded(targetNode, previousNode);
    }

    /**
     * Get an zipcode of a given city
     * @param city the city whose zipcode is retrieved
     * @return zipcode of the city
     */
    public String getCityZipcode(String city) throws EmptyLinkedListException, ItemNotFoundException, EmptyStringException{
        validateCityArgument(city);
        validateLinkedListIsNotEmpty(MESSAGE_NO_CITIES);
        return zipcodeOfCityIfFound(city);
    }

    /**
     * A helper method to get a zipcode of a given city
     * @param city name of the city
     * @return zipcode of the city
     * @throws ItemNotFoundException is thrown if the city is not found
     */
    private String zipcodeOfCityIfFound(String city) throws ItemNotFoundException {
        Node pointer = head;
        while(pointer != null){
            if(pointer.key.equals(city)){
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
     * @return True if the city is in the linked list (any first occurence in the list). Otherwise, false.
     * Return false if the linked list is empty.
     */
    public boolean containsDuplicatesOf(String city) throws NullPointerException, EmptyStringException{
        validateCityArgument(city);
        if(isEmpty()){
            System.out.println("There are no duplicates in an empty linked list.");
            return false;
        }
        if(foundDuplicateOf(city)){
            return true;
        }
        else{
            System.out.println("No duplicates of " + city + " are found.");
            return false;
        }
    }

    /**
     * A helper method to find a duplicate of the given city.
     * @param city name of the city
     * @return True, if a duplicate of the city is found. False, if no duplicates are found.
     */
    private boolean foundDuplicateOf(String city){
        Node pointer = head;
        while(pointer != null){
            if(pointer.key.equals(city)){
                System.out.println("Found a duplicate: " + pointer.key);
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    /**
     * Check if a linked list is empty.
     * @param message a message to be passed to an EmptyLinkedListException exception.
     * @throws EmptyLinkedListException is thrown if the linked list is empty.
     */
    private void validateLinkedListIsNotEmpty(String message) throws EmptyLinkedListException{
        if(isEmpty()){
            throw new EmptyLinkedListException(message);
        }
    }

    /**
     * Validate the index.
     * @param index an index to be validate
     * @throws InvalidIndexException is thrown if the index is less than or equal to zero
     * or greater than count.
     */
    private void validateRequestedIndex(int index) throws InvalidIndexException{
        if(index <= 0 || index > count){
            throw new InvalidIndexException("Requested index "+index+" is invalid.");
        }
    }

    /**
     * Validate the city argument to see if it is null or empty.
     * @throws EmptyStringException if the city string is empty.
     */
    private void validateCityArgument(String city) throws EmptyStringException{
        validateCityIsNotNull(city);
        validateCityIsNotEmpty(city);
    }

    private void validateCityIsNotNull(String city){
        if(city == null){
            System.out.println("The name of the city cannot be null.");
            throw new NullPointerException("The name of the city cannot be null.");
        }
    }

    private void validateCityIsNotEmpty(String city) throws EmptyStringException{
        if(city == ""){
            throw new EmptyStringException("Invalid argument: empty string");
        }
    }

    /**
     * Validate the key and value arguments to see if they meet the requirements:
     * key and value of a node cannot be null or empty.
     */
    private void validateArguments(String key, String value) throws EmptyStringException{
        validateArgumentsAreNotNull(key, value);
        validateArgumentsAreNotEmpty(key, value);
    }

    private void validateArgumentsAreNotNull(String key, String value){
        if(key == null && value == null){
            System.out.println("Key and value cannot be null.");
            throw new NullPointerException("Key and value of a node cannot be null.");
        }
        if(key == null){
            System.out.println("Key of a node cannot be null.");
            throw new NullPointerException("Key of a node cannot be null.");
        }
        if(value == null){
            System.out.println("Value of a node cannot be null.");
            throw new NullPointerException("Value of a node cannot be null.");
        }
    }

    private void validateArgumentsAreNotEmpty(String key, String value) throws EmptyStringException{
        if(key == "" && value == ""){
            throw new EmptyStringException("Key and value of a node cannot be empty.");
        }
        if(key == ""){
            throw new EmptyStringException("Key of a node cannot be empty.");
        }
        if(value == ""){
            throw new EmptyStringException("Value of a node cannot be empty.");
        }
    }

    /**
     * A class for a node of a linked list
     */
    private class Node{

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
