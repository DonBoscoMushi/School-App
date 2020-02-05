package com.donnicholaus.schoolapp;

public class UserModal {

    private String regNo;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private double phone;
    private String birthdate;
    private String degreeProg;

    public UserModal(String regNo, String firstname, String middlename, String lastname,
                        String email, double phone, String birthdate, String degreeProg){
        this.regNo = regNo;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.degreeProg = degreeProg;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPhone() {
        return phone;
    }

    public void setPhone(double phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDegreeProg() {
        return degreeProg;
    }

    public void setDegreeProg(String degreeProg) {
        this.degreeProg = degreeProg;
    }


}
