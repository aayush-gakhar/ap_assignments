package com.company;

public class AssignmentSubmission implements Submission{
    private final Person student;
    private final String file;
    private boolean graded;
    private int marks;
    Instructor gradedBy;
    AssignmentSubmission(Person student,String file){
        this.student=student;
        this.file=file;
        this.graded=false;
    }
    public boolean isGraded(){
        return graded;
    }
    public int getMarks(){
        return marks;
    }
    public String toString(){
        return "Submission: "+file+(isGraded()?"\nMarks scored: "+marks:"");
    }

    @Override
    public Person getStudent() {
        return student;
    }

    public void grade(int marks,Instructor gradedBy){
        if(isGraded())return;
        graded=true;
        this.marks=marks;
        this.gradedBy=gradedBy;
    }
}
