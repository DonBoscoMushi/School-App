package com.donnicholaus.schoolapp;

public class UserModal {

//    public static String studentData(String regNo, String firstname, String middlename, String lastname,
////                        String email, double phone, String birthdate, String degreeProg){
//
//
//    }

    private static   String regNo;
    private static String firstname;
    private static String middlename;
    private static String lastname;
    private static String email;
    private static String phone;
    private static String birthdate;
    private static String degreeProg;

//    public  UserModal(String regNo, String firstname, String middlename, String lastname,
//                        String email, Double phone, String birthdate, String degreeProg){
//        this.regNo = regNo;
//        this.firstname = firstname;
//        this.middlename = middlename;
//        this.lastname = lastname;
//        this.email = email;
//        this.phone = phone;
//        this.birthdate = birthdate;
//        this.degreeProg = degreeProg;
//    }



    public static String getRegNo() {
        return regNo;
    }

    public static void setRegNo(String _regNo) {
        regNo = _regNo;
    }

    public static String getFirstname() {
        return firstname;
    }

    public static void setFirstname(String Firstname) {
         firstname = Firstname;
    }

    public static String getMiddlename() {
        return middlename;
    }

    public static void setMiddlename(String Middlename) {
         middlename = Middlename;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String Lastname) {
         lastname = Lastname;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String Email) {
         email = Email;
    }

    public static String getPhone() {
        return  phone;
    }

    public static void setPhone(String  Phone) {
         phone = Phone;
    }

    public static String getBirthdate() {
        return birthdate;
    }

    public static void setBirthdate(String Birthdate) {
         birthdate = Birthdate;
    }

    public static String getDegreeProg() {
        return degreeProg;
    }

    public static void setDegreeProg(String DegreeProg) {
         degreeProg = DegreeProg;
    }


}
