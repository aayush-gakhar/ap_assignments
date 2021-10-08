package com.company;

import java.util.*;

public class Portal {
    Scanner scanner=new Scanner(System.in);
    List<Vaccine> vaccines=new LinkedList<>();
    List<Hospital> hospitals=new LinkedList<>();
    List<Citizen> citizens=new LinkedList<>();
    Map<String,Citizen> citizenIDs=new HashMap<>();
    Map<String,Vaccine> vaccineName=new HashMap<>();
    Map<Vaccine,Set<Hospital>> searchVaccine=new HashMap<>();

    void pressEnter(){
//        try {
//            System.in.read();
//        }catch (Exception e){}
    }
    void addVaccine(){
        System.out.print("Vaccine Name: ");
        String name=scanner.next();
        System.out.print("Number of Doses: ");
        int noOfDoses=scanner.nextInt();
        int gapBetweenDoses;
        if(noOfDoses==1){
            gapBetweenDoses = 0;
        } else {
            System.out.print("Gap between Doses: ");
            gapBetweenDoses = scanner.nextInt();
        }
        Vaccine vaccine=new Vaccine(name,noOfDoses,gapBetweenDoses);
        vaccines.add(vaccine);
        vaccineName.put(name,vaccine);
        System.out.println("Vaccine Name: "+name+", Number of Doses: "+noOfDoses+", Gap Between Doses: "+gapBetweenDoses);
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
        System.out.println("Hospital Name: "+hospitalName+", PinCode: "+pincode+", Unique ID: "+(Hospital.count-1));
        pressEnter();
    }
    void registerCitizen(){
        System.out.print("Citizen Name: ");
        String name=scanner.next();
        System.out.print("Age: ");
        int age=scanner.nextInt();
        System.out.print("Unique ID: ");
        String ID=scanner.next();
        System.out.println("Citizen Name: "+name+", Age: "+age+", Unique ID: "+ID);
        if(age<18) {
            System.out.println("\nOnly above 18 are allowed");
        }else if(citizenIDs.containsKey(ID)){
            System.out.println("\nAlready registered on this unique ID");
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
            Vaccine vaccine=vaccines.get(scanner.nextInt());
            hospital.addSlot(new Slot(day,quantity, vaccine));
            if(!searchVaccine.containsKey(vaccine)){
                searchVaccine.put(vaccine,new HashSet<>());
            }
            searchVaccine.get(vaccine).add(hospital);
            System.out.println("Slot added by Hospital "+hospitalID+" for Day: "+day+", Available Quantity: "+quantity+" of Vaccine "+
                    vaccine.name);
        }pressEnter();
    }
    void bookSlot(){
        System.out.print("Enter patient Unique ID: ");
        Citizen citizen=citizenIDs.get(scanner.next());
        if(citizen.vaccinationStatus== Citizen.status.FULLY_VACCINATED){
            System.out.println("FULLY VACCINATED");
            return;
        }
        System.out.print("1. Search by area\n" +
                "2. Search by Vaccine\n" +
                "3. Exit\n" +
                "Enter option: ");
        int option= scanner.nextInt();
        while (option>3 || option<1){
            System.out.print("Enter option(1,2 or 3): ");
            option= scanner.nextInt();
        }
        Hospital hospital;
        if(option==1){
            System.out.print("Enter PinCode: ");
            String pincode=scanner.next();
            int ID=0;
            for (Hospital h : hospitals) {
                if(h.pincode.equals(pincode)){
                    System.out.println(ID+" "+h.hospitalName);
                }ID++;
            }
            System.out.print("Enter hospital id: ");
            hospital=hospitals.get(scanner.nextInt());
            int s=0,n=0;
            if(citizen.vaccinationStatus== Citizen.status.PARTIALLY_VACCINATED){
                Vaccine vaccine=citizen.vaccineGiven;
                for (Slot slot : hospital.slots) {
                    if (slot.vaccine == vaccine && citizen.nextDueDate<=slot.day) {
                        n++;
                        System.out.println(s + "-> Day: " + slot.day + " Available Qty: " + slot.available + " Vaccine: " + slot.vaccine.name);
                    }
                    s++;
                }
            }else {
                for (Slot slot : hospital.slots) {
                    n++;
                    System.out.println(s + "-> Day: " + slot.day + " Available Qty: " + slot.available + " Vaccine: " + slot.vaccine.name);

                    s++;
                }
            }if(n==0){
                System.out.println("No slots available");
                return;
            }
//            for (Slot slot : hospital.slots) {
//                System.out.println(s+"-> Day: "+slot.day+" Available Qty: "+slot.available+" Vaccine: "+slot.vaccine.name);
//                s++;
//            }
            System.out.print("Choose Slot: ");
            citizen.bookSlot(hospital.slots.get(scanner.nextInt()));
            pressEnter();
        }else if(option==2){
            System.out.print("Enter Vaccine name: ");
            Vaccine vaccine=vaccineName.get(scanner.next());
            if(citizen.vaccinationStatus== Citizen.status.PARTIALLY_VACCINATED && citizen.vaccineGiven!=vaccine){
                System.out.println("Choose the correct vaccine.");
                return;
            }
            for (Hospital h : searchVaccine.get(vaccine)) {
                System.out.println(h.hospitalID+" "+h.hospitalName);
            }
            System.out.print("Enter hospital id: ");
            hospital=hospitals.get(scanner.nextInt());
            int s=0,n=0;
            if(citizen.vaccinationStatus== Citizen.status.PARTIALLY_VACCINATED){
                for (Slot slot : hospital.slots) {
                    if (slot.vaccine == vaccine && citizen.nextDueDate<=slot.day) {
                        n++;
                        System.out.println(s + "-> Day: " + slot.day + " Available Qty: " + slot.available + " Vaccine: " + slot.vaccine.name);
                    }
                    s++;
                }
            }else {
                for (Slot slot : hospital.slots) {
                    if (slot.vaccine == vaccine) {
                        n++;
                        System.out.println(s + "-> Day: " + slot.day + " Available Qty: " + slot.available + " Vaccine: " + slot.vaccine.name);
                    }
                    s++;
                }
            }
            if(n==0){
                System.out.println("No slots available");
                return;
            }
            System.out.print("Choose Slot: ");
            citizen.bookSlot(hospital.slots.get(scanner.nextInt()));
            pressEnter();
        }else return;

    }
    void getSlot(){
        System.out.print("Enter Hospital Id: ");
        int hospitalID= scanner.nextInt();
        Hospital hospital=hospitals.get(hospitalID);
        for (Slot slot : hospital.slots) {
            System.out.println("Day: "+slot.day+" Vaccine: "+slot.vaccine.name+" Available Qty: "+slot.available);
        }pressEnter();
    }
    void getVaccinationStatus(){
        System.out.print("Enter Patient ID: ");
        String ID=scanner.next();
        System.out.print("Status: ");
        citizenIDs.get(ID).printVaccinationStatus();
        pressEnter();
    }
}
