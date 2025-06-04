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
	    Node current = head_;
	    this.size = 0;
	    //Count nodes and set the tail
	    while (current != null) {
	        this.tail = current;
	        this.size++;
	        current = current.next;
	    }
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
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException("Index out of bounds");
	    }
	    Node current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.next;
	    }
	    if (current.prev != null) {
	        current.prev.next = current.next;
	    } else {
	    	// removing the head
	        head = current.next;
	    }
	    if (current.next != null) {
	        current.next.prev = current.prev;
	    } else {
	    	// removing the tail
	        tail = current.prev;
	    }
	    size--;
	    return current.getString();
	}


	//returns the index of the String parameter 
	public int indexOf(String obj) {
	    Node current = head;
	    int index = 0;
	    while (current != null) {
	        if (current.getString().equals(obj)) {
	            return index;
	        }
	        current = current.next;
	        index++;
	    }
	    // When not found
	    return -1; 
	}
	
	//returns String at parameter's index
	public String get(int index) {
	    if (index < 0 || index >= size) {
	        return null;
	    }
	    Node current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.next;
	    }
	    return current.getString();
	}

	//checks if there is a list
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	 }

	//return the size of the list
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	 @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        Node current = head;
	        while (current != null) {
	            sb.append(current.getString());
	            if (current.next != null) sb.append(" ");
	            current = current.next;
	        }
	        return sb.toString();
	    }

	// Appends to end of list
	@Override
    public boolean add(String obj) {
        Node newNode = new Node(null, tail, obj);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

	//add String at parameter's index
	public boolean add(int index, String obj) {
		if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Invalid Index");
	    }

	    Node newNode;
	    if (index == size) {
	    	// Append if at the end
	        return add(obj); 
	    } else if (index == 0) {
	        newNode = new Node(head, null, obj);
	        if (head != null) {
	            head.prev = newNode;
	        }
	        head = newNode;
	        if (tail == null) {
	            tail = newNode;
	        }
	    } else {
	        Node current = head;
	        for (int i = 0; i < index; i++) {
	            current = current.next;
	        }
	        newNode = new Node(current, current.prev, obj);
	        if (current.prev != null) {
	            current.prev.next = newNode;
	        }
	        current.prev = newNode;
	        // Connect new node to the current node
	        newNode.next = current;
	    }

	    size++;
	    return true;
	}

	
	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		return indexOf(obj) != -1;
	}
	
	//removes the parameter's String form the list
	public boolean remove(String obj) {
		Node current = head;

	    while (current != null) {
	        if (current.getString().equals(obj)) {
	            if (current.prev != null) {
	                current.prev.next = current.next;
	            } else {
	                head = current.next;
	            }

	            if (current.next != null) {
	                current.next.prev = current.prev;
	            } else {
	                tail = current.prev;
	            }

	            size--;
	            return true;
	        }
	        current = current.next;
	    }
	    // not found
	    return false; 
	}
	
	
	@Override
	public int hashCode() {
	    int hash = 1;
	    Node current = head;
	    while (current != null) {
	        hash = 31 * hash + (current.getString() == null ? 0 : current.getString().hashCode());
	        current = current.next;
	    }
	    return hash;
	}


	//check if 2 lists are identical
	@Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof DSEList)) return false;

        DSEList otherList = (DSEList) other;
        if (this.size != otherList.size) return false;

        Node current1 = this.head;
        Node current2 = otherList.head;

        while (current1 != null && current2 != null) {
            if (!current1.getString().equals(current2.getString())) return false;
            current1 = current1.next;
            current2 = current2.next;
        }

        return true;
    }
	
}
