package com.company;

public interface Submission {
    boolean isGraded();
    int getMarks();
    Person getStudent();
    void grade(int marks,Instructor gradedBy);
}
