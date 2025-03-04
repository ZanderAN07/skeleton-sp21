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

        // Ensure the size is correct
        assertEquals(3, deque.size());

        // Ensure elements are returned in the correct order (LIFO for addFirst)
        assertEquals((Integer) 30, deque.removeFirst());
        assertEquals((Integer) 20, deque.removeFirst());
        assertEquals((Integer) 10, deque.removeFirst());

        // Ensure the deque is empty after removals
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddLastAndRemoveLast() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("A");
        deque.addLast("B");
        deque.addLast("C");

        // Ensure the size is correct
        assertEquals(3, deque.size());

        // Ensure elements are returned in the correct order (FIFO for addLast)
        assertEquals("C", deque.removeLast());
        assertEquals("B", deque.removeLast());
        assertEquals("A", deque.removeLast());

        // Ensure the deque is empty after removals
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Test resizing on addLast when capacity is exceeded
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        // Check the size after adding elements
        assertEquals(20, deque.size());

        // Test removing elements and check if deque resizes properly
        for (int i = 0; i < 10; i++) {
            deque.removeFirst();
        }

        // After removing 10 elements, size should be 10
        assertEquals(10, deque.size());
    }

    @Test
    public void testGet() {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        deque.addLast('a');
        deque.addLast('b');
        deque.addLast('c');
        deque.addLast('d');

        // Ensure correct elements are retrieved at each index
        assertEquals((Character) 'a', deque.get(0));
        assertEquals((Character) 'b', deque.get(1));
        assertEquals((Character) 'c', deque.get(2));
        assertEquals((Character) 'd', deque.get(3));

        // Ensure out-of-bounds indices return null
        assertNull(deque.get(4));
        assertNull(deque.get(-1));
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // Ensure the iterator works as expected
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

        // Add same elements to both deques
        deque1.addLast(5);
        deque1.addLast(10);
        deque1.addLast(15);
        deque2.addLast(5);
        deque2.addLast(10);
        deque2.addLast(15);

        // Ensure they are equal
        assertEquals(deque1, deque2);

        // Modify one deque and ensure they are not equal
        deque2.addLast(20);
        assertNotEquals(deque1, deque2);
    }

    @Test
    public void testEmptyDeque() {
        ArrayDeque<Double> deque = new ArrayDeque<>();

        // Ensure the deque is empty initially
        assertTrue(deque.size() == 0);

        // Ensure removing from an empty deque returns null
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
    }

    @Test
    public void testAddFirstResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Test resizing when adding elements to the front
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        // Check size after adding elements
        assertEquals(3, deque.size());

        // Ensure elements are correctly removed from the front in LIFO order
        assertEquals((Integer) 3, deque.removeFirst());
        assertEquals((Integer) 2, deque.removeFirst());
        assertEquals((Integer) 1, deque.removeFirst());

        // Ensure deque is empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddLastResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Test resizing when adding elements to the back
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        // Check size after adding elements
        assertEquals(3, deque.size());

        // Ensure elements are correctly removed in FIFO order
        assertEquals((Integer) 10, deque.removeLast());
        assertEquals((Integer) 20, deque.removeLast());
        assertEquals((Integer) 30, deque.removeLast());

        // Ensure deque is empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testEdgeCaseAddFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        // Add elements at the beginning and test overflow behavior
        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }

        // Ensure deque size is correct
        assertEquals(20, deque.size());
    }

    @Test
    public void testEdgeCaseAddLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        // Add elements at the end and test overflow behavior
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        // Ensure deque size is correct
        assertEquals(20, deque.size());
    }
}
