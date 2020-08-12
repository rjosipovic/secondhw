package home.playground.list.performance;

import home.playground.collection.IndexedCollection;

public class AddToListTestExecutor extends TestExecutor {

    private int elementsToAdd;

    public AddToListTestExecutor(IndexedCollection list, int repetitions, int elementsToAdd) {
        super(list, repetitions);
        this.elementsToAdd = elementsToAdd;
    }

    @Override
    protected long performOperation() {
        long start = System.nanoTime();
        for(int i = 0; i < this.elementsToAdd; i++) {
            this.list.add(Integer.valueOf(i));
        }
        long end = System.nanoTime();
        this.list.clear();
        return Duration.inMilis(start, end);
    }
}
