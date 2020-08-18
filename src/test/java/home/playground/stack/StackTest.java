package home.playground.stack;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;

import home.playground.stack.exception.EmptyStackException;
import org.junit.Test;

public class StackTest {

    @Test
    public void initTest() {
        Stack stack = new Stack();
        assertThat(stack.isEmpty(), is(Boolean.TRUE));
        assertThat(stack.size(), is(0));
    }

    @Test(expected = EmptyStackException.class)
    public void peekOnEmpty() {
        Stack stack = new Stack();
        stack.peek();
    }

    @Test(expected = EmptyStackException.class)
    public void popOnEmpty() {
        Stack stack = new Stack();
        stack.peek();
    }

    @Test
    public void pushPeekPopTest() {
        Stack stack = new Stack();
        for(int i = 0; i < 10; i++) {
            stack.push(i);
            assertThat(stack.peek(), is(i));
        }
        assertThat(stack.isEmpty(), is(Boolean.FALSE));
        assertThat(stack.size(), is(10));

        for(int i = 9; i >= 0; i--) {
            assertThat(stack.pop(), is(i));
        }
    }

    @Test
    public void clearTest() {
        Stack stack = new Stack();
        for(int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertThat(stack.isEmpty(), is(Boolean.FALSE));
        assertThat(stack.size(), is(10));

        stack.clear();

        assertThat(stack.isEmpty(), is(Boolean.TRUE));
        assertThat(stack.size(), is(0));
    }
}
