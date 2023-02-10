package com.ltp.gradesubmission;

import lombok.Data;

@Data
public class Show {
    private String title;
    private String episode;
    private double rating;
    public Show(String title, String episode, double rating){

        this.title=title;
        this.rating=rating;
        this.episode=episode;

    }
}
