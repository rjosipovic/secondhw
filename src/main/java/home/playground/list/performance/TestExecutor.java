package home.playground.list.performance;

import home.playground.collection.IndexedCollection;

public abstract class TestExecutor {

    protected IndexedCollection list;
    private int repetitions;

    public TestExecutor(IndexedCollection list, int repetitions) {
        this.list = list;
        this.repetitions = repetitions;
    }

    protected abstract long performOperation();

    public MeasurementResult execute() {
        long[] tmp = new long[repetitions];
        for(int i = 0; i < repetitions; i++) {
            tmp[i] = performOperation();
        }
        return new MeasurementResult(this.list.getClass().getSimpleName(), tmp);
    }
}
