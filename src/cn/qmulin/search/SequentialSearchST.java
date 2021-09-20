package cn.qmulin.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 顺序查找（基于无序链表）
 * @author: muLin
 * @time: 2021/9/15 17:36
 */
public class SequentialSearchST<Key, Value> {
    private int n;
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 查找给定的键，返回相关的值
     *
     * @param key 查找的键
     * @return 改键的值
     */
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;//命中
        }
        return null;//未命中
    }

    /**
     * 查找给定的键，找到则更新其值，否则在表中新建结点
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next)
            //命中，更新
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        //未命中，新建结点
        first = new Node(key, val, first);
    }

    public int size() {
        return n;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public void delete(Key key){
        first=delete(first,key);
    }

    private Node delete(Node x, Key key) {
        if (x==null) return null;
        if (key.equals(x.key)){
            n--;
            return x.next;
        }
        x.next=delete(x.next,key);
        return x;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue=new LinkedList<>();
        for (Node x=first;x!=null;x=x.next){
            queue.add(x.key);
        }
        return queue;
    }
}
