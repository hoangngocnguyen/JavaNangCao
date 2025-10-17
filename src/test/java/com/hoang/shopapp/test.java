package com.hoang.shopapp;

interface Adder {
    int apply(int x);   // tuong duong public abstract int apply(int x);
}

public class test {
    public static Adder makeAdder(int n) {
        return x -> x + n;
    }
    public static void main(String[] args) {
        ham();
    }

    private static void ham() {
        System.out.println(makeAdder(3).apply(4));      // 7
        System.out.println(makeAdder(3).apply(7));      // 10
    }
}
