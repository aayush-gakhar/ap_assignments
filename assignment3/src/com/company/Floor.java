package com.company;

public abstract class Floor {
    private static int count=0;
    private final int position;
    private final int points;
    private final int jump;

    Floor(int points,int jump){
        this.position=count++;
        this.points=points;
        this.jump=jump;
    }

    public int getPosition() {
        return position;
    }

    public abstract String toString();


    public int getPoints() {
        return points;
    }

    public int getJump() {
        return jump;
    }
}
