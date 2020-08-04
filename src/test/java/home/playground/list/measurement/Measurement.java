package home.playground.list.measurement;

import home.playground.collection.Collection;
import home.playground.collection.IndexedCollection;

public abstract class Measurement implements Comparable<Measurement> {

    private double average;
    protected int repetitions;
    protected IndexedCollection list;
    protected long[] measurements;

    public Measurement(IndexedCollection list, int repetitions) {
        if(list == null) {
            throw new NullPointerException("List can not be null");
        }
        if(repetitions <= 0) {
            throw new IllegalArgumentException("Repetitions can not be negative or 0");
        }
        this.repetitions = repetitions;
        this.list = list;
    }

    public abstract long performOperation();
    public abstract void prepareList();

    public void measurePerformance() {
        this.measurements = new long[repetitions];
        for(int i = 0; i < repetitions; i++) {
            measurements[i] = performOperation();
        }
        this.average = calculateAverage();
    }

    private double calculateAverage() {
        double sum = 0.0;
        for(int i = 0; i < measurements.length; i++) {
            sum += measurements[i];
        }
        return sum / measurements.length;
    }

    public String listMeasurementsRowStr() {
        String row = "";
        row += String.format("%-10s", list.getClass().getSimpleName());
        for(int i = 0; i < this.repetitions; i++) {
            row += String.format("%5d", this.measurements[i]);
            row += " ";
        }
        row += String.format("%7.2f", this.average);
        row += "\n";
        return row;
    }

    public Collection getList() {
        return list;
    }

    @Override
    public int compareTo(Measurement anotherMeasurement) {
        return Double.compare(this.average, anotherMeasurement.average);
    }
}
