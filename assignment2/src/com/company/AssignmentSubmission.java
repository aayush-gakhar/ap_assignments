package com.company;

public class AssignmentSubmission implements Submission{
    private final Person student;
    private final String file;
    private boolean graded;
    private int marks;
    private Instructor gradedBy;
    AssignmentSubmission(Person student,String file){
        this.student=student;
        this.file=file;
        this.graded=false;
    }
    @Override
    public boolean isGraded(){
        return graded;
    }
    @Override
    public int getMarks(){
        return marks;
    }
    @Override
    public String toString(){
        return "Submission: "+file+(isGraded()?"\nMarks scored: "+marks+"Graded by: "+gradedBy:"");
    }
    @Override
    public Person getStudent() {
        return student;
    }
    @Override
    public void grade(int marks,Instructor gradedBy){
        if(isGraded())return;
        graded=true;
        this.marks=marks;
        this.gradedBy=gradedBy;
    }
}
