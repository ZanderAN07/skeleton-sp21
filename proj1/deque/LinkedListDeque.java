package deque;

public class LinkedListDeque<T> {
    private class TNode {
        public TNode prev;
        public T item;
        public TNode next;

        public TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
            // System.out.println(size);
        }
    }
    private TNode sentinel;
    private TNode last;
    private int size;
    public void addFirst(T adder){
        size += 1;
        if(sentinel == null){
            sentinel = new TNode(adder,null,null);
            last = sentinel;
        }
        sentinel = new TNode(adder, sentinel,null);
    }
    public void addLast(T adder){
        size += 1;
        if(sentinel == null){
            sentinel = new TNode(adder, null, null);
            last = sentinel;
        }
        else {
            TNode newNode = new TNode(adder, null, last);
            last.next = newNode;
            last = newNode;
        }
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public T removeFirst(){
        if(sentinel == null){
            return null;
        }
        size -= 1;
        T r = sentinel.item;
        TNode n = sentinel.next;
        if(n == null){
            sentinel = null;
            last = null;
            return r;
        }
        sentinel.item = n.item;
        sentinel.next = n.next;
        sentinel.prev = null;

        return r;
    }
    public T removeLast() {
        if (sentinel == null) {
            return null;
        }

        if (last == null) {
            return null;
        }
        T r = last.item;
        size -= 1;
        if (last.prev == null) {
            sentinel = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return r;
    }
    public void printDeque(){
        TNode p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != null) {
            System.out.print(p.item);
            p = p.next;
        }
    }

}
