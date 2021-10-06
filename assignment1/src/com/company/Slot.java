package com.company;

public class Slot {
    int day,quantity,available;
    Vaccine vaccine;
    Slot(int day,int quantity,Vaccine vaccine){
        this.day=day;
        this.quantity=quantity;
        this.available=quantity;
        this.vaccine=vaccine;
    }
    boolean book(){
        if(available>0){
            available--;
            return true;
        }return false;
    }
}
