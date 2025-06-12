package bstmap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private class Node {
        private K key;
        private V val;
        private int size = 0;
        private Node left, right;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    public BSTMap(){

    }

    private Node Node_get(Node target, K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        if (target == null) {
            throw new RuntimeException("NoThisVal");
        }
        int cmp = key.compareTo(target.key);
        if (cmp < 0) {
            return Node_get(target.left, key);
        } else if (cmp > 0) {
            return Node_get(target.right, key);
        }
        else{
            return target;
        }
    }
    @Override
    public void clear(){
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        try{
            Node_get(root ,key);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public V get(K key){
        try{
            return Node_get(root,key).val;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public int size(){
        if(root == null){
            return 0;
        }
        else {
            return root.size;
        }
    }

    private int size(Node x){
        if(x == null){
            return 0;
        }
        else{
            return x.size;
        }
    }


    private Node put(Node x, K key, V val){
        if(x == null){
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = put(x.left, key , val);
        }
        else if(cmp > 0){
            x.right = put(x.right, key, val);
        }
        else{
            x.val = val;
            //对于一个特定的value，重复传入key，这样子会自动更新这个value的key。
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public void put(K key, V val){
        root = put(root, key, val);
    }

    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException("Not Yet..");
    }

    private boolean is_leaf(Node x){
        return x.left == null && x.right == null;
    }
    @Override
    public Node remove(Node x, K k){
        if(x == null){
            return null;
        }
        else{
            int cmp = k.compareTo(x.key);
            if(cmp < 0){
                x.left = remove(x.left, k);
            }
            else if(cmp > 0){
                x.right = remove(x.right, k);
            }
            else{
                if(x.right == null){
                    return x.left;
                }
                if(x.left == null){
                    return x.right;
                }

            }
        }
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("NOT REQUIRED");
    }
}
