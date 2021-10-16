package com.company;

public interface Submission {
    boolean isGraded();
    int getMarks();
    String toString();
    Person getStudent();
    void grade(int marks,Instructor gradedBy);
}
