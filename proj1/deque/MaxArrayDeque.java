package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

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
