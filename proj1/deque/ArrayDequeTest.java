package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

public class ArrayDequeTest {

    @Test
    public void testAddFirstAndRemoveFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        assertEquals(3, deque.size());
        assertEquals((Integer) 30, deque.removeFirst());
        assertEquals((Integer) 20, deque.removeFirst());
        assertEquals((Integer) 10, deque.removeFirst());
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddLastAndRemoveLast() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("A");
        deque.addLast("B");
        deque.addLast("C");

        assertEquals(3, deque.size());
        assertEquals("C", deque.removeLast());
        assertEquals("B", deque.removeLast());
        assertEquals("A", deque.removeLast());
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        assertEquals(20, deque.size());
        for (int i = 0; i < 10; i++) {
            deque.removeFirst();
        }
        assertEquals(10, deque.size());
    }

    @Test
    public void testGet() {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        deque.addLast('a');
        deque.addLast('b');
        deque.addLast('c');
        deque.addLast('d');

        assertEquals((Character) 'a', deque.get(0));
        assertEquals((Character) 'b', deque.get(1));
        assertEquals((Character) 'c', deque.get(2));
        assertEquals((Character) 'd', deque.get(3));
        assertNull(deque.get(4));
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals((Integer) 1, iterator.next());
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque1.addLast(5);
        deque1.addLast(10);
        deque1.addLast(15);
        deque2.addLast(5);
        deque2.addLast(10);
        deque2.addLast(15);

        assertEquals(deque1, deque2);

        deque2.addLast(20);
        assertNotEquals(deque1, deque2);
    }

    @Test
    public void testEmptyDeque() {
        ArrayDeque<Double> deque = new ArrayDeque<>();
        assertTrue(deque.size() == 0);
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
    }
}

