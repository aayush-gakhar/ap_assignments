package com.company;

import java.util.Date;

public class Slides implements LectureMaterial {
    private final String title;
    private final int noOfSlides;
    private final String[] content;
    private final Date dateOfUpload;
    private final Person uploader;

    Slides(String title, int noOfSlides, String[] content, Person uploader) {
        this.title = title;
        this.noOfSlides = noOfSlides;
        this.content = content;
        this.dateOfUpload = new Date();
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Title: " + title);
        int i = 1;
        for (String s : content) {
            stringBuilder.append("\nSlide ").append(i++).append(": ").append(s);
        }
        stringBuilder.append("\nNumber of slides: ").append(noOfSlides).append("\nDate of upload: ").append(dateOfUpload).append("\nUploaded by: ").append(uploader);
        return stringBuilder.toString();
    }
}
