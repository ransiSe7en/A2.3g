package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head;
	private NodeGeneric<T> tail;
	private int size;

	public DSEListGeneric() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//Constructor accepting node containing a generic object
    public DSEListGeneric(NodeGeneric<T> head_) {
	    this.head = head_;
	    NodeGeneric<T> current = head_;
	    this.size = 0;
	    //Count nodes and set the tail
	    while (current != null) {
	        this.tail = current;
	        this.size++;
	        current = current.next;
	    }
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor. 
		if (other == null || other.head == null) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }

        NodeGeneric<T> current = other.head;
        NodeGeneric<T> prevNewNode = null;

        while (current != null) {
            NodeGeneric<T> newNode = new NodeGeneric<>(null, prevNewNode, current.get());
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
		
	
	//remove and return the item at the parameter's index
	public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        NodeGeneric<T> current = head;
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
        return current.get();
    }

	//returns the index of the object parameter 
	public int indexOf(T obj) {
		NodeGeneric<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.get().equals(obj)) {
                return index;
            }
            current = current.next;
            index++;
        }
        // When not found
        return -1;
	}
	
	//returns item at parameter's index
	public T get(int index) {
        if (index < 0 || index >= size) {
        	return null;
        }
        NodeGeneric<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.get();
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
        NodeGeneric<T> current = head;
        while (current != null) {
            sb.append(current.get());
            if (current.next != null) sb.append(" ");
            current = current.next;
        }
        return sb.toString();
	}	

	//add the parameter item at of the end of the list
	@Override
	public boolean add(T obj) {
		NodeGeneric<T> newNode = new NodeGeneric<>(null, tail, obj);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
	}
	

	//add item at parameter's index
	public boolean add(int index, T obj) {
		if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Index out of bounds");
	    }

	    NodeGeneric<T> newNode;
	    if (index == size) {
	        return add(obj); // Append if at end
	    } else if (index == 0) {
	        newNode = new NodeGeneric<>(head, null, obj);
	        if (head != null) {
	            head.prev = newNode;
	        }
	        head = newNode;
	        if (tail == null) {
	            tail = newNode;
	        }
	    } else {
	        NodeGeneric<T> current = head;
	        for (int i = 0; i < index; i++) {
	            current = current.next;
	        }
	        newNode = new NodeGeneric<>(current, current.prev, obj);
	        if (current.prev != null) {
	            current.prev.next = newNode;
	        }
	        current.prev = newNode;
	    }
	    size++;
	    return true;
	}

	//searches list for parameter's object return true if found
	public boolean contains(T obj) {
		return indexOf(obj) != -1;
	}

	//removes the parameter's item form the list
	public boolean remove(T obj) {
		NodeGeneric<T> current = head;

	    while (current != null) {
	        if (current.get().equals(obj)) {
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
	    // Not found
	    return false; 
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
	    NodeGeneric<T> current = head;
	    while (current != null) {
	        hash = 31 * hash + (current.get() == null ? 0 : current.get().hashCode());
	        current = current.next;
	    }
	    return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
        if (other == null || !(other instanceof DSEListGeneric<?>)) return false;

        DSEListGeneric<?> otherList = (DSEListGeneric<?>) other;
        if (this.size != otherList.size) return false;

        NodeGeneric<T> current1 = this.head;
        NodeGeneric<?> current2 = otherList.head;

        while (current1 != null && current2 != null) {
            if (!current1.get().equals(current2.get())) return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        
		return true;
	}
	
}
