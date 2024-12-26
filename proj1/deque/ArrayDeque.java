package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        front = 2;
        size = 0;
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        front = capacity/4-1;
        System.arraycopy(items, front+1, a, capacity/4 , size);
        items = a;
    }
    public void addLast(T adder){
        if(size == items.length){
            resize(items.length * 2);
        }
        items[size] = adder;
        size += 1;
    }
    public void addFirst(T adder){
        if(front == 0){
            resize(items.length *2);
        }
        items[front] = adder;
        front -= 1;
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T r = items[size - 1];
        items[size - 1] = null;
        return r;
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T r = items[front + 1];
        items[front + 1] = null;
        front += 1;
        return r;
    }
    public int size(){
        return size - front;
    }
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front + 1 + index)];
    }
    public void printDeque(){
        for(int i = front+1; i < size; i ++){
            System.out.print(items[i]);
        }
        System.out.println();
    }
    public boolean isEmpty(){
        return size == 0;
    }

}
