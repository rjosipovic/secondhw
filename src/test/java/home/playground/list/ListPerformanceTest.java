package home.playground.list;

import home.playground.collection.IndexedCollection;
import home.playground.list.measurement.AddMeasurement;
import home.playground.list.measurement.GetMeasurement;
import home.playground.list.measurement.Measurement;

public class ListPerformanceTest {

    public static void main(String[] args) {
        System.out.println("List comparison test");
        measureAddOperation();
        measureGetOperation();
        System.out.println("DONE!");
    }

    /**
     * 1. create ArrayList and LinkedLIst
     * 2. add 1000000 elements in each list
     * 3. measure time required to add elements to each list
     * 4. repeat measure time 10 times and calculate average
     */
    private static void measureAddOperation() {
        final int repetitions = 10;
        final int elementsToAdd = 1000000;
        IndexedCollection arrayList = new ArrayList();
        IndexedCollection linkedList = new LinkedList();
        Measurement arrayListAddMeasurement = new AddMeasurement(arrayList, elementsToAdd, repetitions);
        Measurement linkedListAddMeasurement = new AddMeasurement(linkedList, elementsToAdd, repetitions);
        arrayListAddMeasurement.measurePerformance();
        linkedListAddMeasurement.measurePerformance();
        prettyPrint(new Measurement[]{arrayListAddMeasurement, linkedListAddMeasurement}, repetitions);
    }

    private static void measureGetOperation() {
        final int repetitions = 10;
        final int initialElements = 10000;
        IndexedCollection arrayList = new ArrayList();
        IndexedCollection linkedList = new LinkedList();
        Measurement arrayListAddMeasurement = new GetMeasurement(arrayList, initialElements, repetitions);
        arrayListAddMeasurement.prepareList();
        Measurement linkedListAddMeasurement = new GetMeasurement(linkedList, initialElements, repetitions);
        linkedListAddMeasurement.prepareList();
        arrayListAddMeasurement.measurePerformance();
        linkedListAddMeasurement.measurePerformance();
        prettyPrint(new Measurement[]{arrayListAddMeasurement, linkedListAddMeasurement}, repetitions);
    }

    private static void prettyPrint(Measurement[] measurements, int repetitions) {
        System.out.println(headerStr(repetitions));
        for(Measurement measurement : measurements) {
            System.out.println(measurement.listMeasurementsRowStr());
        }
        System.out.println(remark(measurements));
    }

    private static String headerStr(int measurements) {
        String row = "";
        row += String.format("%-10s", " ");
        for(int i = 1; i <= measurements; i++) {
            row += String.format("%5s", "M" + String.valueOf(i));
            row += " ";
        }
        row += String.format("%7s", "AVG");
        row += "\n";
        return row;
    }

    private static String remark(Measurement[] measurements) {
        Measurement best = null;
        for(Measurement m : measurements) {
            if(best == null) {
                best = m;
            } else {
                int difference = m.compareTo(best);
                if(difference < 0) {
                    best = m;
                }
            }
        }

        String row = "";
        row += String.format("%-10s", "Remark");
        row += " ";

        if(best == null) {
            row += "There were no measurements";
        } else {
            row += String.format("%s performed the best", best.getList().getClass().getSimpleName());
        }
        row += "\n";
        return row;
    }
}
