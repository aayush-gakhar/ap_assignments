package com.company;

import java.util.Date;

public class Comment {
    private final String text;
    private final User poster;
    private final Date dateOfPosting;

    Comment(String text, User poster) {
        this.text = text;
        this.poster = poster;
        this.dateOfPosting = new Date();
    }

    public String toString() {
        return text + " - " + poster + "\n" + dateOfPosting;
    }
}
