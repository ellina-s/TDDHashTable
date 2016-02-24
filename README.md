# Zipcodes

Zipcodes is a Java project implemented in a Test-Driven Development (TDD) fashion. The project is focused on gaining hands-on experience in TDD.
The name comes from an idea to use zipcodes of various cities of the world as a data source for the application.
The application can be easily generalized to process other data sources (e.g. areas of national parks, phone numbers, passwords).

The project uses and implements hash tables and chaining (with linked lists) as a mechanism for handling collisions. Testing is implemented with JUnit.

### Project Structure

- **/src**
	- **/main/java**
		- HashTable.java - source code of the HashTable class
		- ZipcodeLinkedList.java - source code of the ZipcodeLinkedList class
		- **/exceptions**
			- **/hashtable**
				- DuplicateItemException.java
				- EmptyHashTableException.java
				- HashTableException.java
			- **/linkedlist**
				- EmptyLinkedListException.java
				- EmptyStringException.java
				- InvalidIndexException.java
				- ItemNotFoundException.java
				- LinkedListException.java
	- **/test/java**
		- **/HashTableTests**
			- HashTableExceptionsTest.java - units tests for exceptions thrown by the HashTable methods
			- HashTableTest.java - unit tests for the rest of the HashTable class
			- HashTableTestUtilities.java - helper methods for the HashTable unit tests
		- **/ZipcodeLinkedListTests**
			- ZipcodeLinkedListExceptionsTest.java - unit tests for exceptions thrown by the ZipcodeLinkedList class
			- ZipcodeLinkedListTest.java - unit tests for ZipcodeLinkedList methods
			- ZipcodeLinkedListTestUtilities.java - helper methods used by the ZipcodeLinkedList unit tests
