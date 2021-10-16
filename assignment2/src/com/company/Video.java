package com.company;

import java.util.Date;

public class Video implements LectureMaterial{
    String title;
    String file;
    String[] content;
    Date dateOfUpload;
    Instructor uploader;

    Video(String title,String file,String[] content,Instructor uploader){
        this.title=title;
        this.file=file;
        this.content=content;
        this.dateOfUpload=new Date();
        this.uploader=uploader;
    }

    public String toString(){
        return "Title of video: " + title + "\nVideo file: " + file + "\nDate of upload: " + dateOfUpload + "\nUploaded by: " + uploader;
    }
}
