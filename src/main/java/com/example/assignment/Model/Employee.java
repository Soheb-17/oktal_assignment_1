package com.example.assignment.Model;

import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.StringProperty;

public class Employee {


    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String joiningDate;


    public Employee(String firstName, String lastName, String dateOfBirth, String joiningDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.joiningDate = joiningDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }






}
