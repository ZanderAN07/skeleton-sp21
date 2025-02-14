package deque;

public interface Deque<T> {
    default boolean isEmpty(){
        return this.size() == 0;
    }
}
