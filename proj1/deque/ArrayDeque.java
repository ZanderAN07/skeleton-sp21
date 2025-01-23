package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first; //（数组的第一个数字的index）
    public ArrayDeque(){
        items = (T[]) new Object[8];
        first = 3;
        size = 0;
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, first, a, first+capacity/2 , size);
        first += capacity/2;
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
        if(first == 0){
            resize(items.length *2);
        }
        items[first] = adder;
        first -= 1;
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T r = items[size - 1];
        items[size - 1] = null;
        size -= 1;
        return r;
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T r = items[first];
        items[first] = null;
        first += 1;
        size -= 1;
        return r;
    }
    public int size(){
        return size;
    }
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        return items[first+ index];
    }
    public void printDeque(){
        for(int i = first; i < size; i ++){
            System.out.print(items[i]);
        }
        System.out.println();
    }
    public boolean isEmpty(){
        return size == 0;
    }

}
