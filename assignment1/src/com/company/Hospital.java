package com.company;

import java.util.LinkedList;
import java.util.List;

public class Hospital {
    static int count=0;
    String hospitalName,pincode;
    int hospitalID;
    List<Slot> slots=new LinkedList<>();
    Hospital(String hospitalName, String pincode){
        this.hospitalName = hospitalName;
        this.pincode=pincode;
        this.hospitalID =count++;
    }
    void addSlot(Slot slot){
        slots.add(slot);
    }

}
