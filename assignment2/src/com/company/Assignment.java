package com.company;

import java.util.LinkedList;
import java.util.List;

public class Assignment implements Assessment {
    private final String problemStatement;
    private final int maxMarks;
    private final Instructor poster;
    private final List<Submission> submissions=new LinkedList<>();
    private boolean closed;
    Assignment(String problemStatement,int maxMarks,Instructor poster){
        this.problemStatement=problemStatement;
        this.maxMarks=maxMarks;
        this.poster=poster;
        this.closed=false;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public String getProblemStatement() {
        return problemStatement;
    }

    public int getMaxMarks() {
        return maxMarks;
    }
    public String toString(){
        return "Assignment: "+problemStatement+" Max Marks: "+maxMarks;
    }
    public boolean isClosed(){
        return closed;
    }
    public void close(){
        closed=true;
    }
}
