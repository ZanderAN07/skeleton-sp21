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
    @Test
    public void testRandomAddFirstAddLastRemoveFirstRemoveLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Random sequence of operations
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
    public void testAddFirstRemoveLastRandom() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Add first, remove last randomly
        deque.addFirst(15);
        deque.addFirst(30);
        deque.addLast(40);
        deque.addFirst(50);

        // Current deque: [50, 30, 15, 40]

        assertEquals(4, deque.size());
        assertEquals((Integer) 50, deque.removeFirst()); // should remove 50
        assertEquals((Integer) 40, deque.removeLast());  // should remove 40
        assertEquals((Integer) 30, deque.removeFirst()); // should remove 30

        // Deque should now have only 15
        assertEquals((Integer) 15, deque.removeLast());  // should remove 15
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddLastRemoveFirstRandom() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // Add last, remove first randomly
        deque.addLast("A");
        deque.addLast("B");
        deque.addLast("C");
        deque.addLast("D");

        // Current deque: ["A", "B", "C", "D"]

        assertEquals(4, deque.size());
        assertEquals("A", deque.removeFirst());  // should remove "A"
        assertEquals("B", deque.removeFirst());  // should remove "B"
        assertEquals("C", deque.removeFirst());  // should remove "C"
        assertEquals("D", deque.removeFirst());  // should remove "D"

        // Deque should now be empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddFirstAddLastRemoveFirstRemoveLastMixed() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Mixed operations
        deque.addFirst(100);   // deque: [100]
        deque.addLast(200);    // deque: [100, 200]
        deque.addFirst(50);    // deque: [50, 100, 200]
        deque.addLast(300);    // deque: [50, 100, 200, 300]
        deque.addFirst(25);    // deque: [25, 50, 100, 200, 300]

        // Current deque: [25, 50, 100, 200, 300]

        assertEquals(5, deque.size());
        assertEquals((Integer) 25, deque.removeFirst()); // should remove 25
        assertEquals((Integer) 300, deque.removeLast()); // should remove 300
        assertEquals((Integer) 50, deque.removeFirst()); // should remove 50
        assertEquals((Integer) 200, deque.removeLast()); // should remove 200
        assertEquals((Integer) 100, deque.removeFirst()); // should remove 100

        // Deque should now be empty
        assertTrue(deque.size() == 0);
    }

    @Test
    public void testAddFirstAddLastRemoveFirstRemoveLastEmptyDeque() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Add and remove in random order
        deque.addFirst(1);  // deque: [1]
        deque.addLast(2);   // deque: [1, 2]
        deque.addFirst(3);  // deque: [3, 1, 2]
        deque.addLast(4);   // deque: [3, 1, 2, 4]

        assertEquals(4, deque.size());
        assertEquals((Integer) 3, deque.removeFirst()); // deque: [1, 2, 4]
        assertEquals((Integer) 4, deque.removeLast());  // deque: [1, 2]
        assertEquals((Integer) 1, deque.removeFirst()); // deque: [2]
        assertEquals((Integer) 2, deque.removeLast());  // deque: []

        // Deque should now be empty
        assertTrue(deque.size() == 0);
        assertNull(deque.removeFirst());  // should return null because it's empty
        assertNull(deque.removeLast());   // should return null because it's empty
    }

    @Test
    public void testAddFirstAndRemoveFirstMultipleTimes() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Perform addFirst and removeFirst in a loop
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        assertEquals(3, deque.size());
        assertEquals((Integer) 30, deque.removeFirst());  // deque: [20, 10]
        assertEquals((Integer) 20, deque.removeFirst());  // deque: [10]
        assertEquals((Integer) 10, deque.removeFirst());  // deque: []

        // Deque should now be empty
        assertTrue(deque.size() == 0);
        assertNull(deque.removeFirst());  // should return null because it's empty
    }
}
