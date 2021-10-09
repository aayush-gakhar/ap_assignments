package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Hospital {
    private static int count = 0;
    private final String hospitalName;
    private final String pincode;
    private final String hID;
    private final int hospitalID;
    private final List<Slot> slots = new LinkedList<>();

    Hospital(String hospitalName, String pincode) {
        this.hospitalName = hospitalName;
        this.pincode = pincode;
        this.hospitalID = count++;
        this.hID = String.format("%06d", hospitalID);
    }

    public static int getCount() {
        return count;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getPincode() {
        return pincode;
    }

    public String gethID() {
        return hID;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    void addSlot(Slot slot) {
        slots.add(slot);
    }

    void displaySlots() {
        for (Slot slot : slots) {
            System.out.println(slot.toString());
        }
    }

    void displaySlots(Vaccine vaccine, Citizen citizen, Set<Integer> set) {
        int s = 0, n = 0;
        if (citizen.getVaccinationStatus() == status.PARTIALLY_VACCINATED) {
            for (Slot slot : slots) {
                if (slot.getVaccine() == vaccine && citizen.getNextDueDate() <= slot.getDay()) {
                    n++;
                    System.out.println(s + "-> " + slot);
                    set.add(s);
                }
                s++;
            }
        } else {
            for (Slot slot : slots) {
                if (slot.getVaccine() == vaccine) {
                    n++;
                    System.out.println(s + "-> " + slot);
                    set.add(s);
                }
                s++;
            }
        }
        if (n == 0) {
            System.out.println("No slots available");
        }
    }

    void displaySlots(Citizen citizen, Set<Integer> set) {
        int s = 0, n = 0;
        if (citizen.getVaccinationStatus() == status.PARTIALLY_VACCINATED) {
            Vaccine vaccine = citizen.getVaccineGiven();
            displaySlots(vaccine, citizen, set);
            return;
        } else {
            for (Slot slot : slots) {
                n++;
                System.out.println(s + "-> " + slot);
                set.add(s);
                s++;
            }
        }
        if (n == 0) {
            System.out.println("No slots available");
        }
    }

}
