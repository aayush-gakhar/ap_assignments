package com.company;

public class Toy implements Cloneable{
    private final String name;

    Toy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public Toy clone() {
        try {
            return (Toy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
