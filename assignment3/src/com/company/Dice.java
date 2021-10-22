package com.company;

import java.util.Random;

public class Dice {
    private static final Random random=new Random();
    int numFaces;
    private int faceValue;

    Dice(int numFaces){
        this.numFaces=numFaces;
    }

    int roll(){
        faceValue=random.nextInt(numFaces)+1;
        System.out.println("Dice gave "+faceValue);
        return faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }
}
