package com.company;

import java.util.*;

public class Portal {
    Scanner scanner=new Scanner(System.in);
    List<Vaccine> vaccines=new LinkedList<>();
    List<Hospital> hospitals=new LinkedList<>();
    List<Citizen> citizens=new LinkedList<>();
    Map<String,Citizen> citizenIDs=new HashMap<>();
    Map<Integer,List<Hospital>> searchPincode=new HashMap<>();
    Map<Vaccine,Hospital> searchVaccine=new HashMap<>();

    void pressEnter(){
        try {
            System.in.read();
        }catch (Exception e){}
    }
    void addVaccine(){
        System.out.print("Vaccine Name: ");
        String name=scanner.next();
        System.out.print("Number of Doses: ");
        int noOfDoses=scanner.nextInt();
        System.out.print("Gap between Doses: ");
        int gapBetweenDoses=scanner.nextInt();
        vaccines.add(new Vaccine(name,noOfDoses,gapBetweenDoses));
        System.out.print("Vaccine Name: "+name+", Number of Doses: "+noOfDoses+", Gap Between Doses: "+gapBetweenDoses);
        pressEnter();
    }
    void registerHospital(){
        System.out.print("Hospital Name: ");
        String hospitalName=scanner.next();
        System.out.print("PinCode: ");
        String pincode=scanner.next();
        Hospital temp=new Hospital(hospitalName,pincode);
        hospitals.add(temp);
        System.out.println("Allocated Hospital ID is "+(Hospital.count-1));
        System.out.print("Hospital Name: "+hospitalName+", PinCode: "+pincode+", Unique ID: "+(Hospital.count-1));
        pressEnter();
    }
    void registerCitizen(){
        System.out.print("Citizen Name: ");
        String name=scanner.next();
        System.out.print("Age: ");
        int age=scanner.nextInt();
        System.out.print("Unique ID: ");
        String ID=scanner.next();
        System.out.print("Citizen Name: "+name+", Age: "+age+", Unique ID: "+ID);
        if(age<18) {
            System.out.println("Only above 18 are allowed");
        }else if(citizenIDs.containsKey(ID)){
            System.out.print("\nAlready registered on this unique ID");
        }else{
            Citizen citizen=new Citizen(name,age,ID);
            citizens.add(citizen);
            citizenIDs.put(ID,citizen);
        }pressEnter();

    }
    void createSlot(){
        System.out.print("Hospital ID: ");
        int hospitalID=scanner.nextInt();
        Hospital hospital=hospitals.get(hospitalID);
        System.out.print("Enter number of Slots to be added: ");
        int n=scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Day Number: ");
            int day=scanner.nextInt();
            System.out.print("Enter Quantity: ");
            int quantity= scanner.nextInt();
            System.out.println("Select Vaccine");
            int vac=0;
            for (Vaccine vaccine : vaccines) {
                System.out.println(vac++ + ". "+vaccine.name);
            }
            vac= scanner.nextInt();
            hospital.addSlot(new Slot(day,quantity, vaccines.get(vac)));
            System.out.println("Slot added by Hospital "+hospitalID+" for Day: "+day+", Available Quantity: "+quantity+" of Vaccine "+
                    vaccines.get(vac).name);
        }pressEnter();
    }
    void bookSlot(){
        System.out.print("Enter patient Unique ID: ");
        String ID= scanner.next();
        System.out.print("1. Search by area\n" +
                "2. Search by Vaccine\n" +
                "3. Exit\n" +
                "Enter option: ");
        int option= scanner.nextInt();
        if()

    }
    void getSlot(){
        System.out.print("Enter Hospital Id: ");
        int hospitalID= scanner.nextInt();
        Hospital hospital=hospitals.get(hospitalID);
        for (Slot slot : hospital.slots) {
            System.out.println("Day: "+slot.day+" Vaccine: "+slot.vaccine+" Available Qty: "+slot.quantity);
        }pressEnter();
    }
    void getVaccinationStatus(){
        System.out.print("Enter Patient ID: ");
        String ID=scanner.next();
        citizenIDs.get(ID).printVaccinationStatus();
    }
}
