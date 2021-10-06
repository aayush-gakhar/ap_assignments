package com.company;

public class Citizen {
    enum status{
        REGISTERED, PARTIALLY_VACCINATED, FULLY_VACCINATED;

    }
    String name,ID;
    int age,noOfDosesGiven,nextDueDate;
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
//        if(vaccinationStatus==status.PARTIALLY_VACCINATED){
//
//        }
//        if(slot.book()){
//
//        }
    }
    void printVaccinationStatus(){
        if(vaccinationStatus==status.REGISTERED){
            System.out.print("REGISTERED");
        }else if(vaccinationStatus==status.PARTIALLY_VACCINATED){
            System.out.print("PARTIALLY VACCINATED\n" +
                    "Vaccine Given: "+vaccineGiven+"\n" +
                    "Number of Doses given: "+noOfDosesGiven+"\n" +
                    "Next Dose due date: "+nextDueDate);
        }else {
            System.out.print("FULLY VACCINATED\n" +
                    "Vaccine Given: "+vaccineGiven+"\n" +
                    "Number of Doses given: "+noOfDosesGiven);
        }
    }
}
