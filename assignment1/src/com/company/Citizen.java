package com.company;

public class Citizen {
    enum status{
        REGISTERED, PARTIALLY_VACCINATED, FULLY_VACCINATED;
    }
    String name,ID;
    int age,noOfDosesGiven,totalDoses,nextDueDate;
    status vaccinationStatus;
    Vaccine vaccineGiven;
    Citizen(String name,int age,String ID){
        this.name=name;
        this.age=age;
        this.ID=ID;
        this.vaccinationStatus=status.REGISTERED;
        this.vaccineGiven=null;
        this.noOfDosesGiven=0;
        this.nextDueDate=-1;
    }
    void bookSlot(Slot slot){
        if(slot.available<1){
            System.out.println("No vaccine available on given slot!");
            return;
        }
        switch (vaccinationStatus){
            case REGISTERED -> {
                vaccineGiven=slot.vaccine;
                totalDoses=vaccineGiven.noOfDoses;
                if(totalDoses==0){
                    vaccinationStatus=status.FULLY_VACCINATED;
                    return;
                }
                noOfDosesGiven=1;
                slot.book();
                if(totalDoses==1){
                    vaccinationStatus=status.FULLY_VACCINATED;
                }else {
                    nextDueDate=slot.day+ vaccineGiven.gapBetweenDoses;
                    vaccinationStatus=status.PARTIALLY_VACCINATED;
                }
            }
            case PARTIALLY_VACCINATED -> {
                if(slot.vaccine!=vaccineGiven || slot.day<nextDueDate){
                    System.out.println("Invalid slot");
                    return;
                }
                slot.book();
                noOfDosesGiven++;
                if(noOfDosesGiven==totalDoses){
                    vaccinationStatus=status.FULLY_VACCINATED;
                }else {
                    nextDueDate=slot.day+ vaccineGiven.gapBetweenDoses;
                }
            }
            case FULLY_VACCINATED -> {
                System.out.println("Already fully vaccinated.");

            }
        }

    }
    void printVaccinationStatus(){
        if(vaccinationStatus==status.REGISTERED){
            System.out.print("REGISTERED");
        }else if(vaccinationStatus==status.PARTIALLY_VACCINATED){
            System.out.print("PARTIALLY VACCINATED\n" +
                    "Vaccine Given: "+vaccineGiven.name+"\n" +
                    "Number of Doses given: "+noOfDosesGiven+"\n" +
                    "Next Dose due date: "+nextDueDate);
        }else {
            System.out.print("FULLY VACCINATED\n" +
                    "Vaccine Given: "+vaccineGiven.name+"\n" +
                    "Number of Doses given: "+noOfDosesGiven);
        }
    }
}
