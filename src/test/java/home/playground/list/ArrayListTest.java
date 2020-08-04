package home.playground.list;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;

import home.playground.collection.Collection;
import org.hamcrest.collection.IsArrayContainingInOrder;
import org.junit.Test;

public class ArrayListTest {

    @Test
    public void noArgsConstructor() {
        ArrayList list = new ArrayList();
        assertThat(list.isEmpty(), is(Boolean.TRUE));
        assertThat(list.size(), is(0));
    }

    @Test
    public void capacityConstructor() {
        ArrayList list = new ArrayList(20);
        assertThat(list.isEmpty(), is(Boolean.TRUE));
        assertThat(list.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCapacityConstructor() {
        ArrayList list = new ArrayList(0);
    }

    @Test
    public void collectionConstructor() {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        ArrayList list = new ArrayList(collection);
        assertThat(list.isEmpty(), is(Boolean.FALSE));
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void collectionNullConstructor() {
        Collection collection = null;
        ArrayList list = new ArrayList(collection);
    }

    @Test
    public void collectionAndSmallerCapacityConstructor() {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        ArrayList list = new ArrayList(collection, 1);
        assertThat(list.isEmpty(), is(Boolean.FALSE));
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void collectionAndBiggerCapacityContructor() {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        ArrayList list = new ArrayList(collection, 20);
        assertThat(list.isEmpty(), is(Boolean.FALSE));
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void addNullObject() {
        ArrayList list = new ArrayList();
        Object obj = null;
        list.add(obj);
    }

    @Test(expected = NullPointerException.class)
    public void addNullCollection() {
        ArrayList list = new ArrayList();
        Collection collection = null;
        list.add(collection);
    }

    @Test
    public void add() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
            assertThat(list.size(), is(i+1));
        }
        assertThat(list.isEmpty(), is(Boolean.FALSE));
        list.add(11);
        assertThat(list.size(), is(11));
    }

    @Test
    public void addEmptyCollection() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collection collection = new ArrayList();
        list.addAll(collection);
        assertThat(list.size(), is(10));
    }

    @Test
    public void addCollection() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collection collection = new ArrayList();
        collection.add(100);
        collection.add(200);
        collection.add(300);

        list.addAll(collection);
        assertThat(list.size(), is(13));
        assertThat(list.get(10), is(100));
        assertThat(list.get(11), is(200));
        assertThat(list.get(12), is(300));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOnNegativeIndex() {
        ArrayList list = new ArrayList();
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOnInvalidIndexEmptyList() {
        ArrayList list = new ArrayList();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOnInvalidIndexNonEmptyList() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.get(10);
    }

    @Test
    public void testGet() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
            assertThat(list.size(), is(i+1));
        }
        for(int i = 0; i < 10; i++) {
            assertThat(list.get(i), is(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testInsertNullObject() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Object obj = null;
        list.insert(obj, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertOnNegativeIndex() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.insert(12, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertOnInvalidIndex() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.insert(12, 11);
    }

    @Test
    public void insertInEmptyList() {
        ArrayList list = new ArrayList();
        list.insert(1, 0);
        assertThat(list.size(), is(1));
        assertThat(list.isEmpty(), is(false));
        assertThat(list.get(0), is(1));
    }

    @Test
    public void insertAtEndOfNonEmptyList() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.insert(12, 10);
        assertThat(list.get(10), is(12));
    }

    @Test
    public void insertAtBeginningOfNonEmptyList() {
        ArrayList list = new ArrayList();
        for(int i = 1; i < 10; i++) {
            list.add(i);
        }
        assertThat(list.size(), is(9));
        list.insert(0, 0);
        for(int i = 0; i < 10; i++) {
            assertThat(list.get(i), is(i));
        }
        assertThat(list.size(), is(10));
    }

    @Test
    public void insertInMiddleOfNonEmptyList() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertThat(list.size(), is(10));
        list.insert(44, 4);
        assertThat(list.size(), is(11));
        assertThat(list.get(4), is(44));
    }

    @Test(expected = NullPointerException.class)
    public void indexOfNullObject() {
        ArrayList list = new ArrayList();
        Object obj = null;
        list.indexOf(obj);
    }

    @Test(expected = NullPointerException.class)
    public void constainsOfNUllObject() {
        ArrayList list = new ArrayList();
        Object obj = null;
        list.contains(obj);
    }

    @Test
    public void indexInEmptyList() {
        ArrayList list = new ArrayList();
        assertThat(list.indexOf(1), is(-1));
    }

    @Test
    public void containsInEmptyList() {
        ArrayList list = new ArrayList();
        assertThat(list.contains(1), is(false));
    }

    @Test
    public void indexInNonEmptyList() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }

        for(int i = 0; i < 10; i++) {
            assertThat(list.indexOf(i), is(i));
        }
        assertThat(list.indexOf(21342), is(-1));
    }

    @Test
    public void containsInNonEmptyList() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }

        for(int i = 0; i < 10; i++) {
            assertThat(list.contains(i), is(true));
        }
        assertThat(list.contains(21342), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeOnNegativeIndex() {
        ArrayList list = new ArrayList();
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeOnInvalidIndex() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(10);
    }

    @Test
    public void removeOnListBeggining() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertThat(list.size(), is(10));
        list.remove(0);
        assertThat(list.size(), is(9));
        for(int i = 0; i < 9; i++) {
            assertThat(list.get(i), is(i+1));
        }
    }

    @Test
    public void removeOnListEnd() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertThat(list.size(), is(10));
        list.remove(9);
        assertThat(list.size(), is(9));
        for(int i = 0; i < 9; i++) {
            assertThat(list.get(i), is(i));
        }
    }

    @Test
    public void removeInListMiddle() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
        list.remove(1);
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void removeNullObject() {
        ArrayList list = new ArrayList();
        Object obj = null;
        list.remove(obj);
    }

    @Test
    public void clear() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.size(), is(3));
        list.clear();
        assertThat(list.size(), is(0));
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void toArray() {
        ArrayList list = new ArrayList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Object[] a = list.toArray();
        Object[] expected = {0,1,2,3,4,5,6,7,8,9};
        assertThat(expected, IsArrayContainingInOrder.arrayContaining(a));
    }
}
