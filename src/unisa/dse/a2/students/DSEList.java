package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;
	private int size;

	
	// Blank constructor
    public DSEList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //Constructor accepting node containing a string object
	public DSEList(Node head_) {
		this.head = head_;
        this.tail = head_;
        this.size = 1;
	}
	
	//deep copy constructor
	//Takes a list then adds each element into a new list
    public DSEList(DSEList other) {
        if (other == null || other.head == null) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }

        Node current = other.head;
        Node prevNewNode = null;

        while (current != null) {
            Node newNode = new Node(null, prevNewNode, current.getString());
            if (prevNewNode == null) {
                this.head = newNode;
            } else {
                prevNewNode.next = newNode;
            }
            prevNewNode = newNode;
            current = current.next;
        }

        this.tail = prevNewNode;
        this.size = other.size;
    }
	
	
	//remove the String at the parameter's index
	public String remove(int index) {

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
	}
	
	//returns String at parameter's index
	public String get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
	}

	//return the size of the list
	public int size() {
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
