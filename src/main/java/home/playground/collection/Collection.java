package home.playground.collection;

/**
 * This interface outlines the functionality of a simple Collection.
 *
 */
public interface Collection {

    /**
     * Returns <b>true</b> in case Collection contains no objects, <b>false</b> otherwise
     * @return <b>true</b> if empty, <b>false</b> otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of objects in this collection
     * @return number of objects
     */
    int size();

    /**
     * Adds the given object into the collection
     * @param object
     * @throws NullPointerException if the given object is null (default behaviour does not throw)
     */
    void add(Object object);

    /**
     * Returns <b>true</b> if the Collection contains the given object, <b>false</b> otherwise.
     * Equality is checked with equals method, not the == operator.
     * @param object
     * @return <b>true</b> if contains the object, <b>false</b> otherwise.
     * @throws NullPointerException if given object is null (default behaviour does not throw)
     */
    boolean contains(Object object);

    /**
     * Removes the given object from the Collection. If there are multiple duplicates of object in the Collection, only the first one is removed.
     * Returns <b>true</b> if the object was successfully removed, <b>false</b> otherwise.
     *
     * @param object
     * @return <b>true</b> if the object was removes, <b>false</b> otherwise
     * @throws NullPointerException if the given object is null (default behaviour does not throw)
     */
    boolean remove(Object object);

    /**
     * Takes every object from given Collection and adds it into this Collection
     * @param collection
     * @throws NullPointerException if the given collection is null (default behaviour does not throw)
     */
    void addAll(Collection collection);

    /**
     * Clears the Collection, removing every object from it.
     */
    void clear();

    /**
     * This method returns the data contained within this Collection in a form of an Array.
     * Do note that this array is a copy, i.e. if the Collection stores its data in an array this method won't return that array,
     * rather the method will copy that array into a new array, and the return this new array.
     * The same applies it the Collection stores the data in some other structure.

     * @return array containing all the object stored in this Collection
     */
    Object[] toArray();
}
