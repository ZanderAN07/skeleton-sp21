package deque;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
    @Test
    public void testAddFirstAndRemoveFirst() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        assertEquals(3, deque.size());
        assertEquals((Integer) 30, deque.removeFirst());  // should remove 30
        assertEquals((Integer) 20, deque.removeFirst());  // should remove 20
        assertEquals((Integer) 10, deque.removeFirst());  // should remove 10

        // Deque should now be empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddLastAndRemoveLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        assertEquals(3, deque.size());
        assertEquals((Integer) 30, deque.removeLast());  // should remove 30
        assertEquals((Integer) 20, deque.removeLast());  // should remove 20
        assertEquals((Integer) 10, deque.removeLast());  // should remove 10

        // Deque should now be empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddFirstAndAddLastMixed() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(25);

        // Current deque: [5, 10, 20, 25]
        assertEquals(4, deque.size());
        assertEquals((Integer) 5, deque.removeFirst());  // should remove 5
        assertEquals((Integer) 25, deque.removeLast());  // should remove 25
        assertEquals((Integer) 10, deque.removeFirst()); // should remove 10
        assertEquals((Integer) 20, deque.removeLast());  // should remove 20

        // Deque should now be empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testRemoveFirstEmptyDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        // Remove from empty deque should return null
        assertNull(deque.removeFirst());
    }

    @Test
    public void testRemoveLastEmptyDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        // Remove from empty deque should return null
        assertNull(deque.removeLast());
    }

    @Test
    public void testPrintDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addFirst(20);
        deque.addLast(30);

        // Print should show 20 10 30
        deque.printDeque();  // Expect output: 20 10 30
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addFirst(20);
        deque.addLast(30);

        // Test recursive get method
        assertEquals((Integer) 20, deque.getRecursive(0));  // first element
        assertEquals((Integer) 10, deque.getRecursive(1));  // second element
        assertEquals((Integer) 30, deque.getRecursive(2));  // third element
        assertNull(deque.getRecursive(3));  // out of bounds
    }

    @Test
    public void testEquals() {
        LinkedListDeque<Integer> deque1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();

        deque1.addFirst(10);
        deque1.addLast(20);

        deque2.addFirst(10);
        deque2.addLast(20);

        // Should be equal
        assertTrue(deque1.equals(deque2));

        deque2.addLast(30);

        // Should not be equal because the sizes differ
        assertFalse(deque1.equals(deque2));

        // Test with null
        assertFalse(deque1.equals(null));

        // Test with non-deque object
        assertFalse(deque1.equals(new Object()));
    }

    @Test
    public void testAddFirstNull() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addFirst(10);
        deque.addFirst(null);  // Adding null

        // Check if null was added properly
        assertEquals(2, deque.size());
        assertNull(deque.get(1));  // Should return null
    }

    @Test
    public void testAddLastNull() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        deque.addLast(10);
        deque.addLast(null);  // Adding null

        // Check if null was added properly
        assertEquals(2, deque.size());
        assertNull(deque.get(1));  // Should return null
    }
}

