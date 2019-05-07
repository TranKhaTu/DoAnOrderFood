package com.example.doanorderfood.model;

public class Cooker {
    private String table;
    private String number;
    private String nameFood;
    private String unit;
    private String check;
    private String image;
    private int count = 0;
    private int daCheBien;




    public Cooker(String table, String number, String nameFood, String unit, String check, String image, int count, int daCheBien) {
        this.table = table;
        this.number = number;
        this.nameFood = nameFood;
        this.unit = unit;
        this.check = check;
        this.image = image;
        this.count = count;
        this.daCheBien = daCheBien;
    }

    public Cooker(String table, String number, String nameFood, String unit, String check, String image, int count) {
        this.table = table;
        this.number = number;
        this.nameFood = nameFood;
        this.unit = unit;
        this.check = check;
        this.image = image;
        this.count = count;
    }

    public int getDaCheBien() {
        return daCheBien;
    }

    public void setDaCheBien(int daCheBien) {
        this.daCheBien = daCheBien;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
