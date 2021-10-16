package com.company;

import java.util.LinkedList;
import java.util.List;

public class Quiz implements Assessment {
    private final String question;
    private final Instructor poster;
    private final List<Submission> submissions=new LinkedList<>();

    private boolean closed;
    Quiz(String question,Instructor poster){
        this.question=question;
        this.poster=poster;
        this.closed=false;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public String toString(){
        return "Question: "+question;
    }
    public String getQuestion(){
        return question;
    }
    public boolean isClosed(){
        return closed;
    }
    public void close(){
        closed=true;
    }
}
