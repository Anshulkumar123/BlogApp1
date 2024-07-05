package com.blogapp1;

import java.util.function.Predicate;

public class TestClass {
    public static void main(String[] args) {
        Predicate<String> result = n->n.equalsIgnoreCase("anshul");
        boolean anshul = result.test("Anshul");
        System.out.println(anshul);
    }
}
