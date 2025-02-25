package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
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

    private class ArrayDequeIterator implements Iterator {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return get(index);
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            int s = size;
            for (int i = 0; i < s; i++) {
                if (!(get(i).equals(((Deque<?>) o).get(i)))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}