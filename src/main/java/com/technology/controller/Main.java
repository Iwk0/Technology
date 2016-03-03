package com.technology.controller;

import com.technology.model.Test;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String... args) {
        Test test = new Test();
        test.setName("Test2");
        test.setId(1L);

        Test test2 = new Test();
        test2.setName("Test2");
        test2.setId(2L);

        Set<Test> testSet = new HashSet<>();
        testSet.add(test2);
        testSet.add(test);

        System.err.println(testSet);
    }
}