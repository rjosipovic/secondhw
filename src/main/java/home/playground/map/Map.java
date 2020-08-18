package home.playground.map;

import home.playground.list.ArrayList;

public class Map {

    private ArrayList map;

    public Map() {
        this.map = new ArrayList();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Object get(Object key) {
        Entry entry = findEntry(key);
        if(entry != null) {
            return entry.getValue();
        } else {
            return null;
        }
    }

    private Entry findEntry(Object key) {
        if(key == null) {
            throw new NullPointerException("Key can not be null");
        }
        for(int i = 0; i < map.size(); i++) {
            Entry entry = (Entry) map.get(i);
            if(entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;
    }


    public void put(Object key, Object value) {
        Entry entry = findEntry(key);
        if(entry != null) {
            entry.setValue(value);
        } else {
            Entry newEntry = new Entry(key, value);
            map.add(newEntry);
        }
    }

    public void clear() {
        map.clear();
    }

    private class Entry {

        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            if(key == null) {
                throw new NullPointerException("Key can not be null");
            }
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
