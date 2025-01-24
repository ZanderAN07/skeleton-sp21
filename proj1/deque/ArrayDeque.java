package deque;

public class ArrayDeque<T> {
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
            items[first] = adder;
        }

        if(items[first] != null){
            first -= 1;
        }
        items[first] = adder;
        size += 1;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T r = items[size - 1];
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
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        // 测试 addFirst 和 removeFirst
        for (int i = 0; i < 10; i++) {
            test.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(test.removeFirst());
        }

        // 测试 addLast 和 removeLast
        for (int i = 0; i < 10; i++) {
            test.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(test.removeLast());
        }

        // 测试 resize
        for (int i = 0; i < 100; i++) {
            test.addLast(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(test.removeFirst());
        }
    }
}
