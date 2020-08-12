package home.playground.collection;

public abstract class IndexedCollection implements Collection {

    public static Collection EMPTY_COLLECTION = new Collection() {
        @Override
        public boolean isEmpty() {
            return Boolean.TRUE;
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(Object object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean contains(Object object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }
    };

    protected int size;

    public abstract int indexOf(Object object);
    public abstract Object get(int index);
    public abstract void insert(Object object, int position);
    public abstract void remove(int index);

    /**
     * Adds the given object at the end of IndexedCollection
     * @param object
     * @throws NullPointerException if the given object is null
     */
    @Override
    public void add(Object object) {
        if(object == null) {
            throw new NullPointerException("Object can not be null");
        }
        insert(object, size());
    }

    /**
     * Returns <b>true</b> if the IndexedCollection contains the given object, <b>false</b> otherwise.
     * Equality is checked with equals method, not the == operator.
     * @param object
     * @return <b>true</b> if contains the object, <b>false</b> otherwise.
     * @throws NullPointerException if given object is null
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * Removes the given object from the IndexedCollection. If there are multiple duplicates of object in the IndexedCollection, only the first one is removed.
     * Returns <b>true</b> if the object was successfully removed, <b>false</b> otherwise.
     *
     * @param object
     * @return <b>true</b> if the object was removes, <b>false</b> otherwise
     * @throws NullPointerException if the given object is null
     */
    @Override
    public boolean remove(Object object) {
        if(object == null) {
            throw new NullPointerException("Object can not be null");
        }
        int index = indexOf(object);
        if(index == -1) {
            return Boolean.FALSE;
        } else {
            remove(index);
            return Boolean.TRUE;
        }
    }

    /**
     * Takes every object from given Collection and adds it into this IndexedCollection
     * @param collection
     * @throws NullPointerException if the given collection is null
     */
    @Override
    public void addAll(Collection collection) throws NullPointerException {
        if(collection == null) {
            throw new NullPointerException("Given Collection can not be null");
        }
        for(Object o : collection.toArray()) {
            this.add(o);
        }
    }

    /**
     * Returns the number of objects in this LinkedList
     * @return number of objects
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <b>true</b> in case LinkedList contains no objects, <b>false</b> otherwise
     * @return <b>true</b> if empty, <b>false</b> otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
