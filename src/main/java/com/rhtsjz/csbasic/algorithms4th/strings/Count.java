package com.rhtsjz.csbasic.algorithms4th.strings;

import com.rhtsjz.csbasic.algorithms4th.strings.api.Alphabet;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by smile on 4/12/16.
 */
public class Count {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet(args[0]);
        int R = alphabet.R();
        int[] count = new int[R];

        String s = StdIn.readAll();
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (alphabet.contains(s.charAt(i))) {
                count[alphabet.toIndex(s.charAt(i))]++;
            }
        }

        for (int c = 0; c < R; c++) {
            StdOut.println(alphabet.toChar(c) + " " + count[c]);
        }
    }
}
