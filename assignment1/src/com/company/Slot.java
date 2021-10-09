package com.company;

public class Slot {
    private final int day;
    private final int quantity;
    private final Vaccine vaccine;
    private int available;

    Slot(int day, int quantity, Vaccine vaccine) {
        this.day = day;
        this.quantity = quantity;
        this.available = quantity;
        this.vaccine = vaccine;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDay() {
        return day;
    }

    public int getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Day: " + getDay() + " Vaccine: " + getVaccine().toString() + " Available Qty: " + getAvailable();
    }

    boolean book() {
        if (available > 0) {
            available--;
            return true;
        }
        return false;
    }
}
