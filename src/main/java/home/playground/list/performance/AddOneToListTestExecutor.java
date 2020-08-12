package home.playground.list.performance;

import home.playground.collection.IndexedCollection;

public class AddOneToListTestExecutor extends TestExecutor{

    public AddOneToListTestExecutor(IndexedCollection list, int repetitions) {
        super(list, repetitions);
    }

    @Override
    protected long performOperation() {
        long start = System.nanoTime();
        list.add(Integer.valueOf(1000));
        long end = System.nanoTime();
        return Duration.inMilis(start, end);
    }
}
