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

    @Override
    public List<Submission> getSubmissions() {
        return submissions;
    }

    @Override
    public String toString(){
        return "Assignment: "+problemStatement+" Max Marks: "+maxMarks;
    }

    @Override
    public int getMaxMarks() {
        return maxMarks;
    }

    @Override
    public boolean isClosed(){
        return closed;
    }

    @Override
    public void close(){
        closed=true;
    }
}
