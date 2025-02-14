package deque;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int first; //（数组的第一个数字的index）

    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 3;
        size = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, first, a, first + capacity / 4, size);
        first += capacity / 4;
        items = a;
    }

    public void addLast(T adder) {
        if (size + first >= items.length) {
            resize(items.length * 2);
        }
        items[size + first] = adder;
        size += 1;
    }

    public void addFirst(T adder) {
        if (first == 0) {
            resize(items.length * 2);
        }

        if (items[first] != null) {
            first -= 1;
        }
        items[first] = adder;
        size += 1;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T r = items[first + size - 1];
        items[first + size - 1] = null;
        size -= 1;
        return r;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T r = items[first];
        items[first] = null;
        first += 1;
        size -= 1;
        return r;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[first + index];
    }

    public void printDeque() {
        for (int i = first; i < size + first; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        testBasicOperations();
        testCircularStructure();
        testResize();
        testMixedOperations();
        testEdgeCases();
    }

    // Test 1: Basic Operations
    public static void testBasicOperations() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assert deque.isEmpty() : "Deque should be empty initially";

        // Add elements to the deque
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);

        // Check size and order
        assert deque.size() == 3 : "Size should be 3 after adding 3 elements";
        assert deque.get(0) == 0 : "First element should be 0";
        assert deque.get(1) == 1 : "Second element should be 1";
        assert deque.get(2) == 2 : "Third element should be 2";

        // Remove elements
        assert deque.removeFirst() == 0 : "Removed first element should be 0";
        assert deque.removeLast() == 2 : "Removed last element should be 2";
        assert deque.size() == 1 : "Size should be 1 after removing 2 elements";
    }

    // Test 2: Circular Structure
    public static void testCircularStructure() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }

        // Remove a few elements to simulate circular indexing
        deque.removeFirst();
        deque.removeFirst();
        deque.addLast(8);
        deque.addLast(9);

        // Verify order and circular structure
        assert deque.get(0) == 2 : "First element should be 2 after removals";
        assert deque.get(deque.size() - 1) == 9 : "Last element should be 9 after additions";
    }

    // Test 3: Resize (Expand and Shrink)
    public static void testResize() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Expand
        for (int i = 0; i < 16; i++) {
            deque.addLast(i);
        }
        assert deque.size() == 16 : "Size should be 16 after adding 16 elements";
        for (int i = 0; i < 16; i++) {
            assert deque.get(i) == i : "Element at index " + i + " should be " + i;
        }

        // Shrink
        for (int i = 0; i < 12; i++) {
            deque.removeFirst();
        }
        assert deque.size() == 4 : "Size should be 4 after removing 12 elements";
        assert deque.get(0) == 12 : "First element should be 12 after removals";
    }

    // Test 4: Mixed Operations
    public static void testMixedOperations() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addFirst("A");
        deque.addLast("B");
        deque.addFirst("C");
        deque.addLast("D");

        // Verify order after mixed operations
        assert deque.removeFirst().equals("C") : "First element removed should be C";
        assert deque.removeLast().equals("D") : "Last element removed should be D";
        assert deque.get(0).equals("A") : "Remaining first element should be A";
        assert deque.size() == 2 : "Size should be 2 after mixed operations";
    }

    // Test 5: Edge Cases
    public static void testEdgeCases() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Removing from an empty deque
        assert deque.removeFirst() == null : "Removing from empty deque should return null";
        assert deque.removeLast() == null : "Removing from empty deque should return null";

        // Large number of elements
        for (int i = 0; i < 100000; i++) {
            deque.addLast(i);
        }
        assert deque.size() == 100000 : "Deque size should be 100000 after large additions";

        for (int i = 0; i < 100000; i++) {
            assert deque.removeFirst() == i : "Elements should be removed in order";
        }
        assert deque.isEmpty() : "Deque should be empty after removing all elements";
    }
}
