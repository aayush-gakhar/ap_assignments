package com.company;

import java.util.LinkedList;
import java.util.List;

public class Quiz implements Assessment {
    private final String question;
    private final int maxMarks;
    private final Instructor poster;
    private final List<Submission> submissions = new LinkedList<>();
    private boolean closed;

    Quiz(String question, Instructor poster) {
        this.question = question;
        this.maxMarks = 1;
        this.poster = poster;
        this.closed = false;
    }

    @Override
    public List<Submission> getSubmissions() {
        return submissions;
    }

    @Override
    public String toString() {
        return "Question: " + question;
    }

    @Override
    public int getMaxMarks() {
        return maxMarks;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        closed = true;
    }

    public String getQuestion() {
        return question;
    }
}
