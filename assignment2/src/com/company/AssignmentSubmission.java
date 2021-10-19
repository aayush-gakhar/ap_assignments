package com.company;

public class AssignmentSubmission implements Submission {
    private final User student;
    private final String file;
    private boolean graded;
    private int marks;
    private User gradedBy;

    AssignmentSubmission(User student, String file) {
        this.student = student;
        this.file = file;
        this.graded = false;
    }

    @Override
    public boolean isGraded() {
        return graded;
    }

    @Override
    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Submission: " + file + (isGraded() ? "\nMarks scored: " + marks + "\nGraded by: " + gradedBy : "");
    }

    @Override
    public User getStudent() {
        return student;
    }

    @Override
    public void grade(int marks, User gradedBy) {
        if (isGraded()) return;
        graded = true;
        this.marks = marks;
        this.gradedBy = gradedBy;
    }
}
