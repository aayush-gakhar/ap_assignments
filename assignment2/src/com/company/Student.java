package com.company;

import java.util.HashMap;
import java.util.Map;

public class Student implements User {
    private static int count = 0;
    private final int ID;
    private final String name;
    private final Backpack backpack;
    private final Map<Assessment, Submission> submitted = new HashMap<>();

    Student(Backpack backpack) {
        this.ID = count++;
        this.name = "S" + ID;
        this.backpack = backpack;
    }

    public static int getCount() {
        return count;
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

    public Map<Assessment, Submission> getSubmitted() {
        return submitted;
    }

    @Override
    public void query(int option) {
        if (option < 1 || option > 9) {
            System.out.println("Invalid option");
        } else if (option == 1) {
            backpack.viewLectureMaterial();
        } else if (option == 2) {
            backpack.viewAssessments();
        } else if (option == 3) {
            backpack.submitAssessments();
        } else if (option == 4) {
            viewGrades();
        } else if (option == 5) {
            backpack.viewComments();
        } else if (option == 6) {
            backpack.addComment();
        } else {
            backpack.logout();
        }
    }

    public void viewGrades() {
        System.out.println("Graded submissions");
        for (Submission submission : submitted.values()) {
            if (submission.isGraded()) {
                System.out.println(submission);
            }
        }
        System.out.println("----------------------------\n" +
                "Ungraded submissions");
        for (Submission submission : submitted.values()) {
            if (!submission.isGraded()) {
                System.out.println(submission);
            }
        }
    }
}
