package home.playground.list.measurement;

import home.playground.collection.IndexedCollection;

public class GetMeasurement extends Measurement {

    private int initialNumberOfElements;

    public GetMeasurement(IndexedCollection list, int initialNumberOfElements, int repetitions) {
        super(list, repetitions);
        if(initialNumberOfElements < 0) {
            throw new IllegalArgumentException("Initial number of elements can not be negative");
        }
        this.initialNumberOfElements = initialNumberOfElements;
    }

    @Override
    public void prepareList() {
        for(int i = 0; i < initialNumberOfElements; i++) {
            this.list.add(Integer.valueOf(i));
        }
    }

    @Override
    public long performOperation() {
        long start = System.currentTimeMillis();
        for(int i = 0; i < this.list.size(); i++) {
            this.list.get(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
