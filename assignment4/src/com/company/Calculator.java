package com.company;

public class Calculator<T> {
    public int calculate(Integer[] array) {
        return array[0]/array[1];
    }

    public String calculate(String[] array) {
        return array[0] + array[1];
    }
}
