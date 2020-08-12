package home.playground.list.performance;

import home.playground.collection.IndexedCollection;
import home.playground.list.ArrayList;
import home.playground.list.LinkedList;

public class ListPerformanceTest {

    public static void main(String[] args) {
        System.out.println("List comparison test");
        measureAddOperation();
        measureGetOperation();
        measureAddOneMoreOperation();
        System.out.println("DONE!");
    }

    private static void measureAddOperation() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Measure add operation performance");
        final int repetitions = 10;
        final int elementsToAdd = 1_000_000;
        IndexedCollection arrayList = new ArrayList();
        IndexedCollection linkedList = new LinkedList();

        TestExecutor arrayListTestExecutor = new AddToListTestExecutor(arrayList, repetitions, elementsToAdd);
        TestExecutor linkedListTestExecutor = new AddToListTestExecutor(linkedList, repetitions, elementsToAdd);

        MeasurementResult arrayListMeasurementResult = arrayListTestExecutor.execute();
        MeasurementResult linkedListMeasurementResult = linkedListTestExecutor.execute();

        System.out.println(arrayListMeasurementResult.prettyResult());
        System.out.println(linkedListMeasurementResult.prettyResult());
        compareMeasurementResults(arrayListMeasurementResult, linkedListMeasurementResult);
    }
    private static void measureGetOperation() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Measure get operation performance");
        final int repetitions = 10;
        final int initialElements = 10_000;
        IndexedCollection arrayList = new ArrayList();
        IndexedCollection linkedList = new LinkedList();

        ListInitializer.initialize(arrayList, initialElements);
        ListInitializer.initialize(linkedList, initialElements);

        TestExecutor arrayListTestExecutor = new GetFromListTestExecutor(arrayList, repetitions);
        TestExecutor linkedListTestExecutor = new GetFromListTestExecutor(linkedList, repetitions);

        MeasurementResult arrayListMeasurementResult = arrayListTestExecutor.execute();
        MeasurementResult linkedListMeasurementResult = linkedListTestExecutor.execute();

        System.out.println(arrayListMeasurementResult.prettyResult());
        System.out.println(linkedListMeasurementResult.prettyResult());
        compareMeasurementResults(arrayListMeasurementResult, linkedListMeasurementResult);
    }

    private static void measureAddOneMoreOperation() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Measure add one operation performance");
        final int repetitions = 10_000_000;
        final int initialElementsArrayList = 1_000;
        final int initialElementsLinkedList = 1_000_000;
        IndexedCollection arrayList = new ArrayList();
        IndexedCollection linkedList = new LinkedList();

        System.out.println("Initializing lists");

        ListInitializer.initialize(arrayList, initialElementsArrayList);
        ListInitializer.initialize(linkedList, initialElementsLinkedList);

        TestExecutor arrayListTestExecutor = new AddOneToListTestExecutor(arrayList, repetitions);
        TestExecutor linkedListTestExecutor = new AddOneToListTestExecutor(linkedList, repetitions);

        System.out.println("Running tests");

        MeasurementResult arrayListMeasurementResult = arrayListTestExecutor.execute();
        MeasurementResult linkedListMeasurementResult = linkedListTestExecutor.execute();
        System.out.println(arrayListMeasurementResult.prettyResult());
        System.out.println(linkedListMeasurementResult.prettyResult());
        System.out.println(String.format("Array list expanded for %d times", ((ArrayList)arrayList).getExpandions()));
        compareMeasurementResults(arrayListMeasurementResult, linkedListMeasurementResult);
    }

    private static void compareMeasurementResults(MeasurementResult r1, MeasurementResult r2) {
        double difference = r1.compareTo(r2);
        String name1 = r1.getName();
        String name2 = r2.getName();

        if(difference < 0) {
            System.out.println(String.format("%s performed better than %s. %s[%.5f], %s[%.5f]", name1, name2, name1, r1.getAverage(), name2, r2.getAverage()));
        } else if(difference > 0) {
            System.out.println(String.format("%s performed better than %s. %s[%.5f], %s[%.5f]", name2, name1, name2, r2.getAverage(), name1, r1.getAverage()));
        } else {
            System.out.println(String.format("%s and %s performed the same", name1, name2));
        }
    }
}
