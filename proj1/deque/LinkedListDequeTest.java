package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
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

        assertNull(deque.removeFirst()); // 空队列应返回 null
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

        assertNull(deque.removeLast()); // 空队列应返回 null
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

        assertNull(deque.get(3)); // 越界测试
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

        assertNull(deque.getRecursive(3)); // 越界测试
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
        assertFalse(deque1.equals(deque3));
    }

    @Test
    public void testPrintDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        deque.printDeque(); // 预期输出: 1 2 3
    }
}
