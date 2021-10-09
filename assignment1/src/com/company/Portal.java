package com.company;

import java.util.*;

public class Portal {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Vaccine> vaccines = new LinkedList<>();
    private final List<Hospital> hospitals = new LinkedList<>();
//    private final List<Citizen> citizens = new LinkedList<>();
    private final Map<String, Citizen> citizenIDs = new HashMap<>();
    private final Map<String, Vaccine> vaccineName = new HashMap<>();
    private final Map<Vaccine, Set<Hospital>> searchVaccine = new HashMap<>();

    void pressEnter() {
//        try {
//            System.in.read();
//        }catch (Exception e){}
    }

    void addVaccine() {
        System.out.print("Vaccine Name: ");
        String name = scanner.next();
        if (vaccineName.containsKey(name)) {
            System.out.println("Vaccine name already exists.");
            return;
        }
        System.out.print("Number of Doses: ");
        int noOfDoses = scanner.nextInt();
        if (noOfDoses < 1) {
            System.out.println("No of doses should be positive");
            return;
        }
        int gapBetweenDoses;
        if (noOfDoses == 1) {
            gapBetweenDoses = 0;
        } else {
            System.out.print("Gap between Doses: ");
            gapBetweenDoses = scanner.nextInt();
            if (gapBetweenDoses < 0) {
                System.out.println("No of doses should be positive");
                return;
            }
        }
        Vaccine vaccine = new Vaccine(name, noOfDoses, gapBetweenDoses);
        vaccines.add(vaccine);
        vaccineName.put(name, vaccine);
        searchVaccine.put(vaccine, new HashSet<>());
        System.out.println("Vaccine Name: " + name + ", Number of Doses: " + noOfDoses + ", Gap Between Doses: " + gapBetweenDoses);
        pressEnter();
    }

    void registerHospital(String hospitalName,String pincode) {
        Hospital temp = new Hospital(hospitalName, pincode);
        hospitals.add(temp);
        System.out.println("Allocated Hospital ID is " + temp.gethID());
        System.out.println("Hospital Name: " + hospitalName + ", PinCode: " + pincode + ", Unique ID: " + temp.gethID());
        pressEnter();
    }

    void registerCitizen(String name,int age,String ID) {
        if (age < 18) {
            System.out.println("Only above 18 are allowed");
        } else if (citizenIDs.containsKey(ID)) {
            System.out.println("Already registered on this unique ID");
        } else {
            Citizen citizen = new Citizen(name, age, ID);
//            citizens.add(citizen);
            citizenIDs.put(ID, citizen);
        }
        pressEnter();

    }

    void createSlot() {
        System.out.print("Hospital ID: ");
        String hID = scanner.next();
        if (hID.length() != 6) {
            System.out.println("hospital ID length should  be 6");
            return;
        }
        int hospitalID = Integer.parseInt(hID);
        if (hospitalID >= Hospital.getCount()) {
            System.out.println("Given hospital ID does not exist");
            return;
        }
        Hospital hospital = hospitals.get(hospitalID);
        System.out.print("Enter number of Slots to be added: ");
        int n = scanner.nextInt();
        if (n < 1) {
            System.out.println("n should  be +ve");
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Day Number: ");
            int day = scanner.nextInt();
            if (day < 0) {
                System.out.println("day should  be +ve");
                return;
            }
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            if (quantity < 1) {
                System.out.println("quantity should  be +ve");
                return;
            }
            System.out.println("Select Vaccine");
            int vac = 0;
            for (Vaccine vaccine : vaccines) {
                System.out.println(vac++ + ". " + vaccine.toString());
            }
            int in = scanner.nextInt();
            if (in < 0 || in >= vaccines.size()) {
                System.out.println("incorrect input");
                return;
            }
            Vaccine vaccine = vaccines.get(in);
            Slot slot=new Slot(day, quantity, vaccine);
            hospital.addSlot(slot); // association

            searchVaccine.get(vaccine).add(hospital);
            System.out.println("Slot added by Hospital " + hospital.gethID() + " for "+slot);
        }
        pressEnter();
    }

    void bookSlot() {
        System.out.print("Enter patient Unique ID: ");
        String in = scanner.next();
        if (!citizenIDs.containsKey(in)) {
            System.out.println("ID not registered.");
            return;
        }
        Citizen citizen = citizenIDs.get(in);
        if (citizen.getVaccinationStatus() == status.FULLY_VACCINATED) {
            System.out.println("FULLY VACCINATED");
            return;
        }
        System.out.print("""
                1. Search by area
                2. Search by Vaccine
                3. Exit
                Enter option:\s""");
        int option = scanner.nextInt();
        while (option > 3 || option < 1) {
            System.out.print("Enter option(1,2 or 3): ");
            option = scanner.nextInt();
        }
        Hospital hospital;
        if (option == 1) {
            System.out.print("Enter PinCode: ");
            String pincode = scanner.next();
            if (pincode.length() != 6) {
                System.out.println("Pincode length should  be 6");
                return;
            }
            Set<Integer> set = new HashSet<>();
            int ID = 0;
            for (Hospital h : hospitals) {
                if (h.getPincode().equals(pincode)) {
                    System.out.println(ID + " " + h.getHospitalName());
                    set.add(ID);
                }
                ID++;
            }
            if (set.size() == 0) {
                System.out.println("No hospital for this pincode.");
                return;
            }
            System.out.print("Enter option: ");
            ID = scanner.nextInt();
            if (!set.contains(ID)) {
                System.out.println("Invalid option");
                return;
            }
            hospital = hospitals.get(ID);
            set = new HashSet<>();
            hospital.displaySlots(citizen,set);
            if(set.size()==0)return;
            chooseSlot(citizen, hospital, set);
        } else if (option == 2) {
            System.out.print("Enter Vaccine name: ");
            String str=scanner.next();
            if(!vaccineName.containsKey(str)){
                System.out.println("incorrect name");
                return;
            }
            Vaccine vaccine = vaccineName.get(str);
            if (citizen.canTakeVaccine(vaccine)) {
                System.out.println("Choose the correct vaccine.");
                return;
            }
            Set<Integer> set = new HashSet<>();
            for (Hospital h : searchVaccine.get(vaccine)) {
                System.out.println(h.getHospitalID() + " " + h.getHospitalName());
                set.add(h.getHospitalID());
            }
            if (set.size() == 0) {
                System.out.println("No hosp available");
                return;
            }
            System.out.print("Enter option: ");
            int ID = scanner.nextInt();
            if (!set.contains(ID)) {
                System.out.println("Invalid option");
                return;
            }
            hospital = hospitals.get(ID);
            set = new HashSet<>();
            hospital.displaySlots(vaccine,citizen,set);
            if(set.size()==0){
//                System.out.println("No slots available");
                return;
            }
            chooseSlot(citizen, hospital, set);
        }
    }

    private void chooseSlot(Citizen citizen, Hospital hospital, Set<Integer> set) {
        int ID;
        System.out.print("Choose Slot: ");
        ID = scanner.nextInt();
        if (!set.contains(ID)) {
            System.out.println("Invalid option");
            return;
        }
        citizen.bookSlot(hospital.getSlots().get(ID));
        pressEnter();
    }

    void getSlot(int hospitalID) {
        if (hospitalID < 0 || hospitalID >= hospitals.size()) {
            System.out.println("Invalid ID");
            return;
        } Hospital hospital = hospitals.get(hospitalID);
        hospital.displaySlots();
        pressEnter();
    }

    void getVaccinationStatus(String ID) {
        if (!citizenIDs.containsKey(ID)) {
            System.out.println("Invalid ID");
            return;
        }
        System.out.print("Status: ");
        citizenIDs.get(ID).printVaccinationStatus();
        pressEnter();
    }
}
