package home.playground.list;

import home.playground.collection.Collection;
import home.playground.collection.IndexedCollection;

/**
 * Linked list implementation
 */
public class LinkedList extends IndexedCollection {

    private Node first;
    private Node last;

    public LinkedList() {
        this(EMPTY_COLLECTION);
    }

    public LinkedList(Collection collection) {
        if(collection == null) {
            throw new NullPointerException("Collection can not be null");
        }
        size = 0;
        first = null;
        last = null;
        if(!collection.isEmpty()) {
            addAll(collection);
        }
    }

    /**
     * Retrieves the Object stored at the given index
     * @param index
     * @return Object stored at the given index
     * @throws IllegalArgumentException in case index is negative (<0) or greater or equal than size of the ArrayList
     */
    public Object get(int index) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index should be between [%d, %d] inclusive", 0, size() - 1));
        }
        Object[] tmp = toArray();
        return tmp[index];
    }

    /**
     * This method returns the data contained within this LinkedList in a form of an Array.
     * Do note that this array is a copy,
     * @return array containing all the object stored in this Collection
     */
    @Override
    public Object[] toArray() {
        Object[] tmp = new Object[size()];
        Node currentNode = first;
        int index = 0;
        while(currentNode != null) {
            tmp[index++] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        return tmp;
    }

    /**
     * Returns the index to insert the next object in the ArrayList
     * @return the index to insert object in the ArrayList
     */
    private int indexToInsert() {
        return size();
    }

    /**
     * Inserts a given object into the LinkedLilst at the given position The object already present in the collection is not overridden, rather it is shifted to the right to ma place for the new object.
     *
     * @param object
     * @param position
     * @throws NullPointerException in case the given object is null
     * @throws IndexOutOfBoundsException in case the position is less than 0 or greater than size
     */
    public void insert(Object object, int position) {
        if(object == null) {
            throw new NullPointerException("Object can not be null");
        }

        if(position < 0 || position > size()) {
            throw new IndexOutOfBoundsException(String.format("Index should be between [%d, %d] inclusive", 0, size() - 1));
        }

        Node node = new Node(object);
        if(size() == 0) {//position guaranteed to be 0
            first = node;
            last = node;
        } else {
            if(position == 0) { // beginning of list
                first.setPrevious(node);
                node.setNext(first);
                first = node;
            } else if(position == size()) { // end of the list
                last.setNext(node);
                node.setPrevious(last);
                last = node;
            } else { //middle of the list
                Node currentNode = first;
                for(int i = 0; i < position; i++) {//move for position number of times to reach note to be replaced
                    currentNode = currentNode.getNext();
                }
                currentNode.getPrevious().setNext(node);
                node.setPrevious(currentNode.getPrevious());
                currentNode.setPrevious(node);
                node.setNext(currentNode);
            }
        }
        size++;
    }

    /**
     * Finds the first occurrence of the object in this LinkedList and returns its index. If object was not found in the LinkedList returns -1
     * @param object
     * @return the index of the first occurrence of the object, -1 if object was not found
     * @throws NullPointerException in case given object is null
     */
    public int indexOf(Object object) {

        if(object == null) {
            throw new NullPointerException("Object can not be null");
        }
        Node currentNode = first;
        int index = 0;
        while(currentNode != null) {
            if(currentNode.getValue().equals(object)) {
                return index;
            }
            index++;
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    /**
     * Removes the object at the given index in the LinkedList.
     * @param index
     * @throws IndexOutOfBoundsException in case of invalid index
     */
    public void remove(int index) {

        if(size() == 0) {
            throw new IllegalStateException("List is empty");
        }

        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index should be between [%d, %d] inclusive", 0, size() - 1));
        }

        if(size() == 1) { //index guaranteed to be 0
            first = null;
            last = null;
            size = 0;
        } else {
            if(index == 0) { //remove first node
                first.getNext().setPrevious(null);
                first = first.getNext();
            } else if(index == (size() - 1)) { //remove last node
                last.getPrevious().setNext(null);
                last = last.getPrevious();
            } else { //remove node in the middle of the list
                Node toRemove = first;
                for(int i = 0; i < index; i++) {
                    toRemove = toRemove.getNext();
                }
                toRemove.getPrevious().setNext(toRemove.getNext());
                toRemove.getNext().setPrevious(toRemove.getPrevious());
            }
            size--;
        }
    }

    /**
     * Clears the LinkedList, removing every object from it.
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        private Object value;
        private Node next;
        private Node previous;

        public Node(Object object) {
            this.value = object;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" + value + '}';
        }
    }
}
