package com.company;

public class ElevatorFloor extends LadderFloor{

    ElevatorFloor(){
        super(4,8);
    }

    @Override
    public String toString(){
        return "Elevator Floor";
    }
}
