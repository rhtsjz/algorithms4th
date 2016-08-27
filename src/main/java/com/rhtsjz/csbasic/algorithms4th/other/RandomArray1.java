package com.rhtsjz.csbasic.algorithms4th.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zsj on 16-8-27.
 */
public class RandomArray1 {

    public static int[] random(int length) {
        if (length <= 0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = list.remove(new Random().nextInt(length - i));
        }
        return array;
    }

    public static void main(String[] args) {
        int length = 1000000;
        long start1 = System.currentTimeMillis();
        int[] array = random(length);
        System.out.println("time1: " + (System.currentTimeMillis() - start1));
//        print(array);
//        System.out.println();
        long start2 = System.currentTimeMillis();
        int[] a = new RandomArray(length).getA();
        System.out.println("time2: " + (System.currentTimeMillis() - start2));
//        print(a);
    }

    public static void print(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                System.out.print(array[i] + ",");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.print("]");
    }

}
