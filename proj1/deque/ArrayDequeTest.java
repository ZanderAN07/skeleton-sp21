package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.Random;

public class ArrayDequeTest {
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
    public void testRandomAddFirstRemoveFirstIsEmpty() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Random rand = new Random();

        // 进行1000次随机操作
        for (int i = 0; i < 1000; i++) {
            int operation = rand.nextInt(3);  // 随机选择操作：0 - addFirst, 1 - removeFirst, 2 - isEmpty

            if (operation == 0) {
                // addFirst操作
                int valueToAdd = rand.nextInt(100);  // 随机值
                deque.addFirst(valueToAdd);
                // 增加一个元素后，队列不为空
                assertFalse(deque.isEmpty());
            }
            else if (operation == 1 && !deque.isEmpty()) {
                // removeFirst操作，只有在队列非空时才进行
                Integer removedValue = deque.removeFirst();
                // 确保删除操作会减少队列大小
                if (deque.size() == 0) {
                    assertTrue(deque.isEmpty());
                }
            }
        }
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
