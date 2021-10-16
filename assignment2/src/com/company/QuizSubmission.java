package com.company;

public class QuizSubmission implements Submission {
    private final Person student;
    private final String ans;
    private boolean graded;
    private int marks;
    private Instructor gradedBy;

    QuizSubmission(Person student, String ans) {
        this.student = student;
        this.ans = ans;
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
        return "Submission: " + ans + (isGraded() ? "\nMarks scored: " + marks + "Graded by: " + gradedBy : "");
    }

    @Override
    public Person getStudent() {
        return student;
    }

    @Override
    public void grade(int marks, Instructor gradedBy) {
        if (isGraded()) return;
        graded = true;
        this.marks = marks;
        this.gradedBy = gradedBy;
    }
}
