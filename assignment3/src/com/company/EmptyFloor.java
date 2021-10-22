package com.company;

public class EmptyFloor extends Floor{

    EmptyFloor(){
        super(1,0);
    }

    @Override
    public String toString(){
        return "Empty Floor";
    }
}
