package com.company;

import java.util.LinkedList;
import java.util.List;

public class Hospital {
    static int count=0;
    String hospitalName,pincode,hID;
    int hospitalID;
    List<Slot> slots=new LinkedList<>();
    Hospital(String hospitalName, String pincode){
        this.hospitalName = hospitalName;
        this.pincode=pincode;
        this.hospitalID =count++;
        this.hID=String.format("%06d",hospitalID);
    }
    void addSlot(Slot slot){
        slots.add(slot);
    }

}
