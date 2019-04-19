package com.example.doanorderfood.model;

import java.io.Serializable;

public class Staff implements Serializable {
    private String name;
    private String id;
    private String sex;
    private String dateOfBirth;
    private String address;
    private String phone;
    private String dateStart;
    private int checkOnline;
    private String salary;

    public Staff() {
    }

    public Staff(String name, int checkOnline) {
        this.name = name;
        this.checkOnline = checkOnline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }


    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheckOnline() {
        return checkOnline;
    }

    public void setCheckOnline(int checkOnline) {
        this.checkOnline = checkOnline;
    }
}
