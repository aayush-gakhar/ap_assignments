package com.company;

public interface Submission {
    boolean isGraded();

    int getMarks();

    String toString();

    User getStudent();

    void grade(int marks, User gradedBy);
}
