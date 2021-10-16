package com.company;

import java.util.Date;

public class Comment {
    String text;
    Person poster;
    Date dateOfPosting;
    Comment(String text,Person poster){
        this.text=text;
        this.poster=poster;
        this.dateOfPosting=new Date();
    }
    public String toString(){
        return text+" - "+poster +"\n"+dateOfPosting;
    }
}
