package home.playground.list;

import home.playground.collection.Collection;
import home.playground.collection.IndexedCollection;

/**
 * ArrayList implementation
 */
public class ArrayList extends IndexedCollection {

    private static final int INITIAL_ARRAY_CAPACITY = 10;
    private static final int CAPACITY_EXTEND_FACTOR = 2;

    private Object[] objects;
    private int expandions;

    public ArrayList() {
        this(INITIAL_ARRAY_CAPACITY);
    }

    public ArrayList(int capacity) {
        this(EMPTY_COLLECTION, capacity);
    }

    public ArrayList(Collection collection) {
        this(collection, collection.size());
    }

    public ArrayList(Collection collection, int capacity) {

        if(collection == null) {
            throw new NullPointerException("Collection can not be null");
        }

        if(capacity < 1) {
            throw new IllegalArgumentException("Capacity should not be less than 1");
        }

        size = 0;

        if(collection.isEmpty()) {
            objects = new Object[capacity];
        } else {
            if(capacity > collection.size()) {
                objects = new Object[capacity];
            } else {
                objects = new Object[collection.size()];
            }
            addAll(collection);
        }
        this.expandions = 0;
    }

    /**
     * Checks if it is possible to add one more object in the ArrayList
     * @return <b>true</b> if it is possible to add one more element to the ArrayList, <b>false</b> otherwise
     */
    private boolean possibleToAddObject() {
        return size() < this.objects.length;
    }

    /**
     * Increases the capacity of array data structure, with the CAPACITY_EXTEND_FACTOR, that holds ArrayList objects
     */
    private void expand() {
        Object[] expandedArray = new Object[this.objects.length * CAPACITY_EXTEND_FACTOR];
        copyArray(this.objects, expandedArray);
        this.objects = expandedArray;
        expandions++;
    }

    public int getExpandions() {
        return this.expandions;
    }

    /**
     * Copies members of src array into dst array. Src and desr arrays are expected not to be null
     *
     * @param src
     * @param dst
     * @throws IllegalArgumentException in case dst is smaller in length than src
     * @throws NullPointerException in case src or dst are null
     */
    private void copyArray(Object[] src, Object[] dst) throws IllegalArgumentException {
        if(src == null || dst == null) {
            throw new NullPointerException("Object array may not be null");
        }

        if(dst.length < src.length) {
            throw new IllegalArgumentException("Destination array may not be smaller in length than the source array");
        }

        for(int i = 0; i < src.length; i++) {
            dst[i] = src[i];
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
        return this.objects[index];
    }

    /**
     * Returns the index to insert the next object in the ArrayList
     * @return the index to insert object in the ArrayList
     */
    private int indexToInsert() {
        return size();
    }

    /**
     * Inserts a given object into the ArrayList at the given position The object already present in the collection is not overridden, rather it is shifted to the right to ma place for the new object.
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
        //if the object is being added at the end of the list
        if(isPositionOnEnd(position)) {
            if(!possibleToAddObject()) {
                expand();
            }
            this.objects[indexToInsert()] = object;
        } else {
            //create new array that will replace the current array with size increased by one for one inserted object
            Object[] tmp = new Object[size() + 1];

            //copy objects from current array to the new array up to position index to insert new object to
            for(int i = 0; i < position; i++) {
                tmp[i] = this.objects[i];
            }
            //insert new object to position
            tmp[position] = object;

            //copy the rest of the current array
            for(int i = position; i < size(); i++) {
                tmp[i + 1] = this.objects[i];
            }
            this.objects = tmp;
        }
        size++;
    }

    /**
     * Checks if the position is at the end of the ArrayList
     * @param position
     * @return <b>true</b> if the postition is at the end of the ArrayList, <b>false</b> otherwise
     */
    private boolean isPositionOnEnd(int position) {
        return position == size();
    }

    /**
     * Finds the first occurrence of the object in this ArrayList and returns its index. If object was not found in the ArrayList returns -1
     * @param object
     * @return the index of the first occurrence of the object, -1 if object was not found
     * @throws NullPointerException in case given object is null
     */
    public int indexOf(Object object) {

        if(object == null) {
            throw new NullPointerException("Object can not be null");
        }

        for(int i = 0; i < this.objects.length; i++) {
            if(object.equals(this.objects[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes the object at the given index in the ArrayList.
     * @param index
     * @throws IndexOutOfBoundsException in case of invalid index
     */
    public void remove(int index) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index should be between [%d, %d] inclusive", 0, size() - 1));
        }
        Object[] tmp = new Object[this.objects.length];
        int i;
        for(i = 0; i < index; i++) {
            tmp[i] = this.objects[i];
        }
        for(int j = index + 1; j < this.objects.length; j++) {
            tmp[i++] = this.objects[j];
        }
        this.objects = tmp;
        size--;
    }

    /**
     * Clears the ArrayList, removing every object from it.
     */
    @Override
    public void clear() {
        objects = new Object[INITIAL_ARRAY_CAPACITY];
        this.size = 0;
        this.expandions = 0;
    }

    /**
     * This method returns the data contained within this ArrayList in a form of an Array.
     * Do note that this array is a copy, i.e. if the Collection stores its data in an array this method won't return that array,
     * rather the method will copy that array into a new array, and the return this new array.
     * The same applies it the Collection stores the data in some other structure.
     * @return array containing all the object stored in this ArrayList
     */
    @Override
    public Object[] toArray() {
        Object[] tmp = new Object[size()];
        for(int i = 0; i < size(); i++) {
            tmp[i] = objects[i];
        }
        return tmp;
    }
}
