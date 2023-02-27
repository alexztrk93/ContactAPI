package com.ltp.gradesubmission;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class Grade {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 21)
    private String name;
    @Size(min = 2, max = 21)
    @NotBlank(message = "Subject cannot be blank")
    private String subject;
    @Score(message = "Score must be letter grade")
    private String score;
    private String id;


    public Grade() {
        this.id = UUID.randomUUID().toString();

    }

    public Grade(String name, String subject, String score) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.score = score;
        this.subject = subject;
    }

}
