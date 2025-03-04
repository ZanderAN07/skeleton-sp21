package deque;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct, */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());
        lld1.addLast("middle");
        assertEquals(2, lld1.size());
        lld1.addLast("back");
        assertEquals(3, lld1.size());
    }

    @Test
    /** Adds an item, then removes an item, and ensures that the deque is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());
        lld1.addFirst(10);
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());
        lld1.removeFirst();
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /** Tests removing from an empty deque and checking proper size. */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast(); // Should not throw error
        lld1.removeFirst(); // Should not throw error
        assertEquals("Size should be 0 after all removes.", 0, lld1.size());
    }

    @Test
    /** Check for null return when removing from an empty deque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        assertNull("Should return null when removeFirst is called on an empty Deque.", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque.", lld1.removeLast());
    }

    @Test
    /** Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }
        for (int i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (int) lld1.removeFirst());
        }
        for (int i = 999999; i >= 500000; i--) {
            assertEquals("Should have the same value", i, (int) lld1.removeLast());
        }
    }

    @Test
    public void testAddFirst() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(10);
        assertEquals(1, deque.size());
        assertEquals((Integer) 10, deque.get(0));
        deque.addFirst(20);
        assertEquals(2, deque.size());
        assertEquals((Integer) 20, deque.get(0));
        assertEquals((Integer) 10, deque.get(1));
    }

    @Test
    public void testAddLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(30);
        assertEquals(1, deque.size());
        assertEquals((Integer) 30, deque.get(0));
        deque.addLast(40);
        assertEquals(2, deque.size());
        assertEquals((Integer) 30, deque.get(0));
        assertEquals((Integer) 40, deque.get(1));
    }

    @Test
    public void testRemoveFirst() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(50);
        deque.addFirst(60);
        deque.addFirst(70);
        assertEquals((Integer) 70, deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals((Integer) 60, deque.get(0));
        assertEquals((Integer) 60, deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals((Integer) 50, deque.removeFirst());
        assertEquals(0, deque.size());
        assertNull(deque.removeFirst()); // Should return null when empty
    }

    @Test
    public void testRemoveLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(80);
        deque.addLast(90);
        deque.addLast(100);
        assertEquals((Integer) 100, deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals((Integer) 90, deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals((Integer) 80, deque.removeLast());
        assertEquals(0, deque.size());
        assertNull(deque.removeLast()); // Should return null when empty
    }

    @Test
    public void testSize() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        assertEquals(0, deque.size());
        deque.addFirst("A");
        assertEquals(1, deque.size());
        deque.addLast("B");
        assertEquals(2, deque.size());
        deque.removeFirst();
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void testGet() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("X");
        deque.addLast("Y");
        deque.addLast("Z");
        assertEquals("X", deque.get(0));
        assertEquals("Y", deque.get(1));
        assertEquals("Z", deque.get(2));
        assertNull(deque.get(3)); // Out of bounds
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(5);
        deque.addLast(15);
        deque.addLast(25);
        assertEquals((Integer) 5, deque.getRecursive(0));
        assertEquals((Integer) 15, deque.getRecursive(1));
        assertEquals((Integer) 25, deque.getRecursive(2));
        assertNull(deque.getRecursive(3)); // Out of bounds
    }

    @Test
    public void testIterator() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> iter = deque.iterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer) 1, iter.next());
        assertEquals((Integer) 2, iter.next());
        assertEquals((Integer) 3, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testEquals() {
        LinkedListDeque<Integer> deque1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();
        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);
        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addLast(3);
        assertTrue(deque1.equals(deque2));
        deque2.addLast(4);
        assertFalse(deque1.equals(deque2));
        LinkedListDeque<String> deque3 = new LinkedListDeque<>();
        assertFalse(deque1.equals(deque3)); // Different types
    }

    @Test
    public void testPrintDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.printDeque(); // Expected output: 1 2 3
    }
}

