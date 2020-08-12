package home.playground.list.performance;

import home.playground.collection.IndexedCollection;

public class GetFromListTestExecutor extends TestExecutor {

    public GetFromListTestExecutor(IndexedCollection list, int repetitions) {
        super(list, repetitions);
    }

    @Override
    protected long performOperation() {
        long start = System.nanoTime();
        for(int i = 0; i < this.list.size(); i++) {
            this.list.get(i);
        }
        long end = System.nanoTime();
        return Duration.inMilis(start, end);
    }
}
