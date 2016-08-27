package com.rhtsjz.csbasic.algorithms4th.other;

/**
 * Created by zsj on 16-8-27.
 */
public class RandomArray {

    private int length;
    private int[] a;

    public RandomArray(int length) {
        this.length = length;
        generate();
    }

    public static void main(String[] args) {
        int a[] = new RandomArray(10).getA();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }

    private int[] generate() {
        int[] a = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            a[i] = -1;
        }
        int count = 0;
        for (int i = 0; i < this.length; i++) {
            int pos = new Double(Math.random() * this.length).intValue();
//            System.out.println(String.format("i: %d, pos: %d, count: %d", i, pos, count++));
            while (a[pos] != -1) {
                pos = (pos + 1) % (this.length);
//                System.out.println(String.format("i: %d, pos: %d, count: %d", i, pos, count++));
            }
            a[pos] = i;
        }
        return this.a = a;
    }

    public int[] getA() {
        return a;
    }
}
