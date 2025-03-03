package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class TNode {
        private TNode prev;
        private T item;
        private TNode next;

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
    public void addFirst(T adder) {
        size += 1;
        if(sentinel == null){
            sentinel = new TNode(adder,null,null);
            last = sentinel;
        }
        sentinel = new TNode(adder, sentinel,null);
    }
    public void addLast(T adder) {
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

    public T removeFirst(){
        if(sentinel == null){
            return null;
        }
        size -= 1;
        T r = sentinel.item;
        TNode n = sentinel.next;
        if(n == null|| n == last){
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

    public T get(int index) {
        if (index < 0 || index >= size) { // 检查索引是否超出范围
            return null;
        }
        if (sentinel == null) { // 确保链表不是空的
            return null;
        }
        TNode c = sentinel; // 这里应该从 `sentinel` 开始
        for (int i = 0; i < index; i++) {  //
            c = c.next;
        }
        return c.item;
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel);  // 从 sentinel.next 开始
    }

    private T getRecursiveHelper(int index, TNode current) {
        if(current == null){
            return null;
        }
        // 如果已经到达目标节点，返回该节点的 item
        if (index == 0) {
            return current.item;
        } else {
            // 递归调用，向下一个节点查找
            return getRecursiveHelper(index - 1, current.next);
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index = 0;
        @Override
        public T next(){
            return get(index++);
        }
        @Override
        public boolean hasNext(){
            return index < size;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if(o instanceof Deque){
            int s = size;
            if(s != ((Deque<?>) o).size()){
                return false;
            }
            for(int i = 0; i<s; i++){
                if(!(get(i).equals(((Deque<?>) o).get(i)))){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

}
