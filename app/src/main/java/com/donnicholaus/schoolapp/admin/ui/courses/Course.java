package com.donnicholaus.schoolapp.admin.ui.courses;

public class Course {
    private int id;
    private String name;
    private String code;
    private String credit;

    public Course(int id, String name, String code, String credit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}

