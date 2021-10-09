package com.company;

enum status {
    REGISTERED, PARTIALLY_VACCINATED, FULLY_VACCINATED
}

public class Citizen {

    private final String name;
    private final String ID;
    private final int age;
    private int noOfDosesGiven;
    private int totalDoses;
    private int nextDueDate;
    private status vaccinationStatus;
    private Vaccine vaccineGiven;

    Citizen(String name, int age, String ID) {
        this.name = name;
        this.age = age;
        this.ID = ID;
        this.vaccinationStatus = status.REGISTERED;
        this.vaccineGiven = null;
        this.noOfDosesGiven = 0;
        this.nextDueDate = -1;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public int getNoOfDosesGiven() {
        return noOfDosesGiven;
    }

    public int getTotalDoses() {
        return totalDoses;
    }

    public int getNextDueDate() {
        return nextDueDate;
    }

    public status getVaccinationStatus() {
        return vaccinationStatus;
    }

    public Vaccine getVaccineGiven() {
        return vaccineGiven;
    }

    boolean canTakeVaccine(Vaccine vaccine){
        return getVaccinationStatus() == status.PARTIALLY_VACCINATED && getVaccineGiven() != vaccine;
    }
    void bookSlot(Slot slot) {
        if (slot.getAvailable() < 1) {
            System.out.println("No vaccine available on given slot!");
            return;
        }
        switch (vaccinationStatus) {
            case REGISTERED -> {
                vaccineGiven = slot.getVaccine();
                totalDoses = vaccineGiven.getNoOfDoses();
                if (totalDoses == 0) {
                    vaccinationStatus = status.FULLY_VACCINATED;
                    return;
                }
                noOfDosesGiven = 1;
                slot.book();
                System.out.println(name + " vaccinated with " + slot.getVaccine().toString());
                if (totalDoses == 1) {
                    vaccinationStatus = status.FULLY_VACCINATED;
                } else {
                    nextDueDate = slot.getDay() + vaccineGiven.getGapBetweenDoses();
                    vaccinationStatus = status.PARTIALLY_VACCINATED;
                }
            }
            case PARTIALLY_VACCINATED -> {
                if (slot.getVaccine() != vaccineGiven || slot.getDay() < nextDueDate) {
                    System.out.println("Invalid slot");
                    return;
                }
                slot.book();
                System.out.println(name + " vaccinated with " + slot.getVaccine().toString());
                noOfDosesGiven++;
                if (noOfDosesGiven == totalDoses) {
                    vaccinationStatus = status.FULLY_VACCINATED;
                } else {
                    nextDueDate = slot.getDay() + vaccineGiven.getGapBetweenDoses();
                }
            }
            case FULLY_VACCINATED -> {
                System.out.println("Already fully vaccinated.");
            }
        }

    }

    void printVaccinationStatus() {
        if (vaccinationStatus == status.REGISTERED) {
            System.out.println("REGISTERED");
        } else if (vaccinationStatus == status.PARTIALLY_VACCINATED) {
            System.out.println("PARTIALLY VACCINATED\n" +
                    "Vaccine Given: " + vaccineGiven.toString() + "\n" +
                    "Number of Doses given: " + noOfDosesGiven + "\n" +
                    "Next Dose due date: " + nextDueDate);
        } else {
            System.out.println("FULLY VACCINATED\n" +
                    "Vaccine Given: " + vaccineGiven.toString() + "\n" +
                    "Number of Doses given: " + noOfDosesGiven);
        }
    }
}
