package com.ltp.gradesubmission;

import lombok.Data;

@Data
public class Staff {
    private String name;
    private int age;
    private String role;

    public Staff(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;

    }



}
