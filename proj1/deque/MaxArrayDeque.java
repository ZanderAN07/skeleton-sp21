package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;

    public static class IntComparator implements Comparator<Integer>{
        public int compare(Integer s1, Integer s2){
            return s1 - s2;
        }
    }
    private class StringComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            return s1.compareTo(s2);
        }
    }
    private class FloatComparator implements Comparator<Float>{
        public int compare(Float f1, Float f2){
            return (int)(f1- f2);
        }
    }
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maximum = get(0);
        for (int i = 1; i < size(); i++) {
            T val = get(i);
            if (comparator.compare(val, maximum) > 0) {
                maximum = val;
            }
        }
        return maximum;
    }

    public T max(java.util.Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maximum = get(0);
        for (int i = 1; i < size(); i++) {
            T val = get(i);
            if (c.compare(val, maximum) > 0) {
                maximum = val;
            }
        }
        return maximum;
    }
}
