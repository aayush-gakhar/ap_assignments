package com.company;

public class Vaccine {
    private final String name;
    private final int noOfDoses;
    private final int gapBetweenDoses;

    Vaccine(String name, int noOfDoses, int gapBetweenDoses) {
        this.name = name;
        this.noOfDoses = noOfDoses;
        this.gapBetweenDoses = gapBetweenDoses;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getNoOfDoses() {
        return noOfDoses;
    }

    public int getGapBetweenDoses() {
        return gapBetweenDoses;
    }
}
