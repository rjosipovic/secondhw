package home.playground.list;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;

import home.playground.collection.Collection;
import org.hamcrest.collection.IsArrayContainingInOrder;
import org.junit.Test;

public class LinkedListTest {

    @Test
    public void noArgsConstructor() {
        LinkedList list = new LinkedList();
        assertThat(list.isEmpty(), is(Boolean.TRUE));
        assertThat(list.size(), is(0));
    }

    @Test
    public void collectionConstructor() {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        LinkedList list = new LinkedList(collection);
        assertThat(list.isEmpty(), is(Boolean.FALSE));
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test(expected = NullPointerException.class)
    public void collectionNullConstructor() {
        Collection collection = null;
        LinkedList list = new LinkedList(collection);
    }

    @Test(expected = NullPointerException.class)
    public void addNullObject() {
        LinkedList list = new LinkedList();
        Object obj = null;
        list.add(obj);
    }

    @Test(expected = NullPointerException.class)
    public void addNullCollection() {
        LinkedList list = new LinkedList();
        Collection collection = null;
        list.add(collection);
    }

    @Test
    public void add() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collection collection = new ArrayList();
        list.addAll(collection);
        assertThat(list.size(), is(10));
    }

    @Test
    public void addCollection() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOnInvalidIndexEmptyList() {
        LinkedList list = new LinkedList();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getOnInvalidIndexNonEmptyList() {
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.get(10);
    }

    @Test
    public void testGet() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Object obj = null;
        list.insert(obj, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertOnNegativeIndex() {
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.insert(12, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertOnInvalidIndex() {
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.insert(12, 11);
    }

    @Test
    public void insertInEmptyList() {
        LinkedList list = new LinkedList();
        list.insert(1, 0);
        assertThat(list.size(), is(1));
        assertThat(list.isEmpty(), is(false));
        assertThat(list.get(0), is(1));
    }

    @Test
    public void insertAtEndOfNonEmptyList() {
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.insert(99, 9);
        assertThat(list.get(9), is(99));
        list.insert(12, 11);
        assertThat(list.get(11), is(12));
    }

    @Test
    public void insertAtBeginningOfNonEmptyList() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        Object obj = null;
        list.indexOf(obj);
    }

    @Test(expected = NullPointerException.class)
    public void constainsOfNUllObject() {
        LinkedList list = new LinkedList();
        Object obj = null;
        list.contains(obj);
    }

    @Test
    public void indexInEmptyList() {
        LinkedList list = new LinkedList();
        assertThat(list.indexOf(1), is(-1));
    }

    @Test
    public void containsInEmptyList() {
        LinkedList list = new LinkedList();
        assertThat(list.contains(1), is(false));
    }

    @Test
    public void indexInNonEmptyList() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        list.add(1);
        list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeOnInvalidIndex() {
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(10);
    }

    @Test
    public void removeOnListBeggining() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        Object obj = null;
        list.remove(obj);
    }

    @Test
    public void clear() {
        LinkedList list = new LinkedList();
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
        LinkedList list = new LinkedList();
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Object[] a = list.toArray();
        Object[] expected = {0,1,2,3,4,5,6,7,8,9};
        assertThat(expected, IsArrayContainingInOrder.arrayContaining(a));
    }
}
