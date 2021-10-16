package com.company;

public class Instructor implements Person {
    private static int count = 0;
    private final int ID;
    private final String name;
    private final Backpack backpack;


    Instructor(Backpack backpack) {
        this.ID = count++;
        this.name = "I" + ID;
        this.backpack = backpack;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return name;
    }

    public static int getCount() {
        return count;
    }

    public String getMenu() {
        return """
                1. Add class material
                2. Add assessments
                3. View lecture materials
                4. View assessments
                5. Grade assessments
                6. Close assessment
                7. View comments
                8. Add comments
                9. Logout""";
    }



    public void addAssessments() {

    }

    public void gradeAssessment() {

    }

    public void closeAssessment() {

    }

    public void viewLectureMaterial() {

    }

    public void viewAssessments() {

    }

    public void viewComments() {
        backpack.viewComments();
    }

    public void addComments() {
        backpack.addComment();
    }

    public void logout() {
        backpack.logout();
    }

    @Override
    public void query(int option) {
        if (option < 1 || option > 9) {
            System.out.println("Invalid option");
        } else if (option == 1) {
            backpack.addClassMaterial();
        } else if (option == 2) {
            addAssessments();
        } else if (option == 3) {
            viewLectureMaterial();
        } else if (option == 4) {
            viewAssessments();
        } else if (option == 5) {
            gradeAssessment();
        } else if (option == 6) {
            closeAssessment();
        } else if (option == 7) {
            viewComments();
        } else if (option == 8) {
            addComments();
        } else {
            logout();
        }
    }


}
