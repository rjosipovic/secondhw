package home.playground.list.performance;

public class MeasurementResult implements Comparable<MeasurementResult>{

    private String name;
    private long[] measurements;
    private double average;

    public MeasurementResult(String name, long[] measurements) {

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Measurement name can not be empty");
        }

        if(measurements == null || measurements.length == 0) {
            throw new IllegalArgumentException("No measurements defined.");
        }
        this.name = name;
        this.measurements = measurements;
        calculateAverage();
    }

    public String getName() {
        return name;
    }

    public String prettyResult() {
        String row = "";
        row += String.format("%-15s", this.name);
        row += String.format("%.7f", this.average);
        row += "\n";
        return row;
    }

    private void calculateAverage() {
        double sum = 0.0;
        for(int i = 0; i < measurements.length; i++) {
            sum += measurements[i];
        }
        this.average = sum / measurements.length;
    }

    public int getRepetitions() {
        return this.measurements.length;
    }

    public long[] getMeasurements() {
        long[] tmp = new long[this.measurements.length];
        copy(this.measurements, tmp);
        return tmp;
    }

    private void copy(long[] src, long[] dst) {
        if(src == null || dst == null) {
            throw new IllegalArgumentException("Arrays must not be null");
        }
        if(src.length != dst.length) {
            throw new IllegalStateException("Arrays must be of the same length");
        }

        for(int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public double getAverage() {
        return this.average;
    }

    @Override
    public int compareTo(MeasurementResult o) {
        return Double.compare(this.average, o.average);
    }
}
