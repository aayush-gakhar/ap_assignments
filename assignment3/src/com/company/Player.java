package com.company;

public class Player {
    private final String name;
    private final Game game;
    private int position = -1;
    private int points = 0;
    private Floor currentFloor;

    Player(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public String toString() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void movePosition(int move) {
        position += move;
        currentFloor = game.getFloors().get(position);
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }
}
