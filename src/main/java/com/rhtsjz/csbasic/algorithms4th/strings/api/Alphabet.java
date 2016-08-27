package com.rhtsjz.csbasic.algorithms4th.strings.api;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Alphabet.java
 *  Execution:    java Alphabet
 *  Dependencies: StdOut.java
 *
 *  A data type for alphabets, for use with string-processing code
 *  that must convert between an alphabet of size R and the integers
 *  0 through R-1.
 *
 *  Warning: supports only the basic multilingual plane (BMP), i.e,
 *           Unicode characters between U+0000 and U+FFFF.
 *
 ******************************************************************************/

public class Alphabet {

    /**
     * The binary alphabet { 0, 1 }.
     */
    public static final Alphabet BINARY = new Alphabet("01");

    /**
     * The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final Alphabet OCTAL = new Alphabet("01234567");

    /**
     * The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    /**
     * The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    /**
     * The DNA alphabet { A, C, T, G }.
     */
    public static final Alphabet DNA = new Alphabet("ACTG");

    /**
     * The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     * The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     * The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     * The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     * The ASCII alphabet (0-127).
     */
    public static final Alphabet ASCII = new Alphabet(128);

    /**
     * The extended ASCII alphabet (0-255).
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /**
     * The Unicode 16 alphabet (0-65,535).
     */
    public static final Alphabet UNICODE16 = new Alphabet(65536);


    private char[] alphabet;     // the characters in the alphabet
    private int[] inverse;       // indices
    private int R;               // the radix of the alphabet

    /**
     * Initializes a new alphabet from the given set of characters.
     *
     * @param alpha the set of characters
     */
    public Alphabet(String alpha) {

        // check that alphabet contains no duplicate chars
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c])
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            unicode[c] = true;
        }

        alphabet = alpha.toCharArray();
        R = alpha.length();
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++)
            inverse[i] = -1;

        // can't use char since R can be as big as 65,536
        for (int c = 0; c < R; c++)
            inverse[alphabet[c]] = c;
    }

    /**
     * Initializes a new alphabet using characters 0 through R-1.
     *
     * @param R the number of characters in the alphabet (the radix)
     */
    private Alphabet(int R) {
        alphabet = new char[R];
        inverse = new int[R];
        this.R = R;

        // can't use char since R can be as big as 65,536
        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        for (int i = 0; i < R; i++)
            inverse[i] = i;
    }

    /**
     * Initializes a new alphabet using characters 0 through 255.
     */
    public Alphabet() {
        this(256);
    }

    /**
     * Unit tests the <tt>Alphabet</tt> data type.
     */
    public static void main(String[] args) {
        int[] encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);

        int[] encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }

    /**
     * Returns true if the argument is a character in this alphabet.
     *
     * @param c the character
     * @return <tt>true</tt> if <tt>c</tt> is a character in this alphabet; <tt>false</tt> otherwise
     */
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    /**
     * Returns the number of characters in this alphabet (the radix).
     *
     * @return the number of characters in this alphabet
     */
    public int R() {
        return R;
    }

    /**
     * Returns the binary logarithm of the number of characters in this alphabet.
     *
     * @return the binary logarithm (rounded up) of the number of characters in this alphabet
     */
    public int lgR() {
        int lgR = 0;
        for (int t = R - 1; t >= 1; t /= 2)
            lgR++;
        return lgR;
    }

    /**
     * Returns the index corresponding to the argument character.
     *
     * @param c the character
     * @return the index corresponding to the character <tt>c</tt>
     * @throws IllegalArgumentException unless <tt>c</tt> is a character in this alphabet
     */
    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    /**
     * Returns the indices corresponding to the argument characters.
     *
     * @param s the characters
     * @return the indices corresponding to the characters <tt>s</tt>
     * @throws IllegalArgumentException unless every character in <tt>s</tt> is a character in this
     *                                  alphabet
     */
    public int[] toIndices(String s) {
        char[] source = s.toCharArray();
        int[] target = new int[s.length()];
        for (int i = 0; i < source.length; i++)
            target[i] = toIndex(source[i]);
        return target;
    }

    /**
     * Returns the character corresponding to the argument index.
     *
     * @param index the index
     * @return the character corresponding to the index <tt>index</tt>
     * @throws IllegalArgumentException unless <tt>index</tt> is between <tt>0</tt> and <tt>R -
     *                                  1</tt>
     */
    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new IndexOutOfBoundsException("Alphabet index out of bounds");
        }
        return alphabet[index];
    }

    /**
     * Returns the characters corresponding to the argument indices.
     *
     * @param indices the indices
     * @return the characters corresponding to the indices <tt>indices</tt>
     * @throws IllegalArgumentException unless every index is between <tt>0</tt> and <tt>R - 1</tt>
     */
    public String toChars(int[] indices) {
        StringBuilder s = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++)
            s.append(toChar(indices[i]));
        return s.toString();
    }
}
