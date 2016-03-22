# TDD Hash Table

TDD Hash Table is a Java project that implements hash tables from scratch using **Test-Driven Development** (TDD). The purpose of the project is to gain hands-on experience in TDD, as well as to review some data structures such as hash tables and linked lists.
For this project, I chose cities and their zipcodes as a type of data to be handled by the application. Other examples of data that could be used are phone numbers, passwords, unique website visitors, etc.

The project uses and implements hash tables and chaining (with linked lists) as a mechanism for handling collisions. Testing is implemented with **JUnit**.

### Project Structure

- **/src**
	- **/main/java**
		- HashTable.java - source code of the HashTable class
		- ZipcodeLinkedList.java - source code of the ZipcodeLinkedList class
		- DataNode.java - a class for the data contained in a linked list's node
		- **/exceptions**
			- **/hashtable**
				- Exceptions thrown by the HashTable class
			- **/linkedlist**
				- Exceptions thrown by the ZipcodeLinkedList class
	- **/test/java**
		- See the README file in the /test/java directory.
