package com.rhtsjz.csbasic.algorithms4th.strings;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by zsj on 16-4-26.
 */
public class MSD {

    private static final int M = 15; // 小数组切换阈值
    private static int R = 256; //基数
    private static String[] aux; // 数据分类的辅助数组

    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    private static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // 以第 d 个字符为键将a[lo]至a[hi]排序
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi);
            return;
        }

        int[] count = new int[R + 2];
        // 计算频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        // 将频率转换为索引
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // 数据分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        // 回写
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        // 递归的以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllLines();
        sort(a);
        StdOut.println(a);
    }

}
