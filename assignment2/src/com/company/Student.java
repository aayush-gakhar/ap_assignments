package com.company;

public class Student implements Person {
    private static int count = 0;
    private final int ID;
    private final String name;
    private final Backpack backpack;

    Student(Backpack backpack) {
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

    @Override
    public void query(int option) {
        if (option < 1 || option > 9) {
            System.out.println("Invalid option");
        } else if (option == 1) {
            viewLectureMaterial();
        } else if (option == 2) {
            viewAssessments();
        } else if (option == 3) {
            submitAssessments();
        } else if (option == 4) {
            viewGrades();
        } else if (option == 5) {
            viewComments();
        } else if (option == 6) {
            addComments();
        } else {
            logout();
        }
    }

    public String getMenu() {
        return """
                1. View lecture materials
                2. View assessments
                3. Submit assessment
                4. View grades
                5. View comments
                6. Add comments
                7. Logout""";
    }

    public static int getCount() {
        return count;
    }


    public void viewLectureMaterial() {

    }

    public void viewAssessments() {

    }

    public void submitAssessments() {

    }

    public void viewGrades() {

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
}
