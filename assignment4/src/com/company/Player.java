package com.company;

public class Player {
    private final Bucket bucket=new Bucket();

    public Bucket getBucket(){
        return bucket;
    }

    public void addToy(Toy toy){
        bucket.getToys().add(toy);
    }
}
