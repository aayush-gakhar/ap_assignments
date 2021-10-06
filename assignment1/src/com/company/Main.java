package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Portal portal =new Portal();
        String menuOptions="---------------------------------\n" +
                "1. Add Vaccine\n" +
                "2. Register Hospital\n" +
                "3. Register Citizen\n" +
                "4. Add Slot for Vaccination\n" +
                "5. Book Slot for Vaccination\n" +
                "6. List all slots for a hospital\n" +
                "7. Check Vaccination Status\n" +
                "8. Exit\n" +
                "---------------------------------";
        System.out.println("CoWin Portal initialized....");
        while (true){
            System.out.println(menuOptions);
            int query=scanner.nextInt();
            if(query<0 || query>8){
                System.out.println("Wrong query!!!");
            }
            else if(query == 1){
                portal.addVaccine();
            }else if(query == 2){
                portal.registerHospital();
            }else if(query == 3){
                portal.registerCitizen();
            }else if(query == 4){
                portal.createSlot();
            }else if(query == 5){
                portal.bookSlot();
            }else if(query == 6){
                portal.getSlot();
            }else if(query == 7){
                portal.getVaccinationStatus();
            }else if(query == 8){
                System.out.println("------------------------------------------------------------------------------");
                break;
            }
        }

    }
}
