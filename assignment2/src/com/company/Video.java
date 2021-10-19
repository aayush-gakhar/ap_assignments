package com.company;

import java.util.Date;

public class Video implements LectureMaterial {
    private final String title;
    private final String file;
    private final Date dateOfUpload;
    private final Person uploader;

    Video(String title, String file, Person uploader) {
        this.title = title;
        this.file = file;
        this.dateOfUpload = new Date();
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "Title of video: " + title + "\nVideo file: " + file + "\nDate of upload: " + dateOfUpload + "\nUploaded by: " + uploader;
    }
}
