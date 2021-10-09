package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portal portal = new Portal();
        String menuOptions = """
                ---------------------------------
                1. Add Vaccine
                2. Register Hospital
                3. Register Citizen
                4. Add Slot for Vaccination
                5. Book Slot for Vaccination
                6. List all slots for a hospital
                7. Check Vaccination Status
                8. Exit
                ---------------------------------""";
        System.out.println("CoWin Portal initialized....");
        while (true) {
            System.out.println(menuOptions);
            int query = scanner.nextInt();
            if (query < 0 || query > 8) {
                System.out.println("Wrong query!!!");
            } else if (query == 1) {
                portal.addVaccine();
            } else if (query == 2) {
                System.out.print("Hospital Name: ");
                String hospitalName = scanner.next();
                System.out.print("PinCode: ");
                String pincode = scanner.next();
                if (pincode.length() != 6) {
                    System.out.println("Pincode length should  be 6");
                    return;
                }
                portal.registerHospital(hospitalName, pincode);
            } else if (query == 3) {
                System.out.print("Citizen Name: ");
                String name = scanner.next();
                System.out.print("Age: ");
                int age = scanner.nextInt();
                if (age < 0) {
                    System.out.println("age should be positive.");
                    return;
                }
                System.out.print("Unique ID: ");
                String ID = scanner.next();
                if (ID.length() != 12) {
                    System.out.println("unique ID length should  be 12");
                    return;
                }
                System.out.println("Citizen Name: " + name + ", Age: " + age + ", Unique ID: " + ID);
                portal.registerCitizen(name,age,ID);
            } else if (query == 4) {
                portal.createSlot();
            } else if (query == 5) {
                portal.bookSlot();
            } else if (query == 6) {
                System.out.print("Enter Hospital Id: ");
                int hospitalID = scanner.nextInt();
                portal.getSlot(hospitalID);
            } else if (query == 7) {
                System.out.print("Enter Patient ID: ");
                String ID = scanner.next();
                portal.getVaccinationStatus(ID);
            } else if (query == 8) {
                System.out.println("------------------------------------------------------------------------------");
                break;
            }
        }
    }
}
/*
portal --> composition

 */
