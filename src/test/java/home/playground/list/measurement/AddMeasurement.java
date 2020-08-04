package home.playground.list.measurement;

import home.playground.collection.IndexedCollection;

public class AddMeasurement extends Measurement {

    private int elementsToAdd;

    public AddMeasurement(IndexedCollection list, int elementsToAdd, int repetitions) {
        super(list, repetitions);
        if(elementsToAdd <= 0) {
            throw new IllegalArgumentException("Number of elements can not be negative or 0");
        }
        this.elementsToAdd = elementsToAdd;
    }

    @Override
    public void prepareList() {
        //nothing to prepare, leaving it empty
    }

    @Override
    public long performOperation() {
        long start = System.currentTimeMillis();
        for(int i = 0; i < this.elementsToAdd; i++) {
            this.list.add(Integer.valueOf(i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}

