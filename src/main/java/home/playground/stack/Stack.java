package home.playground.stack;

import home.playground.list.LinkedList;
import home.playground.stack.exception.EmptyStackException;

public class Stack {

    private LinkedList stack;

    public Stack() {
        this.stack = new LinkedList();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(Object obj) {
        this.stack.add(obj);
    }

    public Object pop() {
        Object obj = peek();
        stack.remove(obj);
        return obj;
    }

    public Object peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        int lastElementIndex = this.stack.size() - 1;
        Object obj = stack.get(lastElementIndex);
        return obj;
    }

    public void clear() {
        this.stack.clear();
    }
}
