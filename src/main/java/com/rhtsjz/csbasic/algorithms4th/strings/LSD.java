package com.rhtsjz.csbasic.algorithms4th.strings;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by smile on 4/17/16.
 */
public class LSD {

    public static void sort(String[] a, int W) {
        // 通过W个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            // 根据第d个字符用Key-Index count 方法排序

            int[] count = new int[R + 1];
            // 计算出现频率
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // 将频率转换成索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // 将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // 回写
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"abc", "def", "add", "fff", "aaa", "ddd", "eee"};
        int W = 3;
        sort(a, W);
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

}
