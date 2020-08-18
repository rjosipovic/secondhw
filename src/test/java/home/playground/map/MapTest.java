package home.playground.map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class MapTest {

    @Test
    public void initTest() {
        Map map = new Map();
        assertThat(map.isEmpty(), is(Boolean.TRUE));
        assertThat(map.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void putNull() {
        Map map = new Map();
        Object key = null;
        Object value = new String("value");
        map.put(key, value);
    }

    @Test(expected = NullPointerException.class)
    public void getNull() {
        Map map = new Map();
        for(int i = 0; i < 10; i++) {
            map.put(i, Integer.toString(i));
        }
        Object key = null;
        map.get(key);
    }

    @Test
    public void putGetTest() {
        Map map = new Map();
        for(int i = 0; i < 10; i++) {
            Integer key = i;
            String value = Integer.toString(i);
            map.put(key, value);
            assertThat(map.get(key), is(value));
        }
        assertThat(map.isEmpty(), is(Boolean.FALSE));
        assertThat(map.size(), is(10));
    }

    @Test
    public void clearTest() {
        Map map = new Map();
        for(int i = 0; i < 10; i++) {
            Integer key = i;
            String value = Integer.toString(i);
            map.put(key, value);
        }
        assertThat(map.isEmpty(), is(Boolean.FALSE));
        assertThat(map.size(), is(10));

        map.clear();

        assertThat(map.isEmpty(), is(Boolean.TRUE));
        assertThat(map.size(), is(0));
    }
}
