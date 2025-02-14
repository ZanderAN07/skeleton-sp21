package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class TestMaxArrayDeque {
    public void testMaxWithIntComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new MaxArrayDeque.IntComparator());

        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(1);
        deque.addLast(5);

        Integer max = deque.max();
        assertEquals(5, max);
    }
}
