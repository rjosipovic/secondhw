package home.playground.list.performance;

import home.playground.collection.IndexedCollection;

public class ListInitializer {

    public static void initialize(IndexedCollection list, int initialElements) {
        for(int i = 0; i < initialElements; i++) {
            list.add(Integer.valueOf(i));
        }
    }
}
