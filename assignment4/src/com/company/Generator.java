package com.company;

import java.util.Random;

public class Generator {
    private final Random random = new Random();

    public Integer[] generateIntegers() {
        Integer[] array = new Integer[2];
        array[1] = random.nextInt(Integer.MAX_VALUE/16);
        int a=random.nextInt(15)+1;
        array[0] = a*array[1];
        return array;
    }

    public String[] generateStrings() {
        String[] array = new String[2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append((char)(random.nextInt(26) + 'A'+random.nextInt(2)*32));
        }
        array[0] = sb.toString();
        sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append((char)(random.nextInt(26) + 'A'+random.nextInt(2)*32));
        }
        array[1] = sb.toString();
        return array;
    }
}
