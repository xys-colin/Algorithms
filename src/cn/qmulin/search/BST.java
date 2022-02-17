package cn.qmulin.search;

/**
 * @description:
 * @author: muLin
 * @time: 2021/9/20 17:16
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;//该节点为根的子树中的结点总数

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) return 0;
        else return root.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return get(root.left, key);
        else if (cmp > 0) return get(root.right, key);
        else return root.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

}
