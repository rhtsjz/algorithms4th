package com.rhtsjz.csbasic.algorithms4th.strings;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by smile on 5/8/16.
 */
public class TST<Value> {
    private Node root; // 树的根结点

    public TST() {
    }

    public static void main(String[] args) {
        // build symbol table from standard input
        TST<Integer> st = new TST<>();
        for (int i = 0; !StdIn.isEmpty(); i += 2) {
            String key = StdIn.readString();
            int value = StdIn.readInt();
            st.put(key, value);
            StdOut.println("key: " + key + "; val: " + value);
        }

        StdOut.println("key: by, value: " + st.get("by"));
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }

    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }
        return x;
    }

    private class Node {
        char c; // 字符
        Node left, mid, right; // 左中右子三向查找树
        Value val;
    }

}
