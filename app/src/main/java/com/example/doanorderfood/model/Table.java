package com.example.doanorderfood.model;

public class Table {
    private int number;
    private int position;
    private int numberOfChair;
    private int check;
    private String note;

    public Table() {
    }

    public Table(int number, int position, int numberOfChair, int check, String note) {
        this.number = number;
        this.position = position;
        this.numberOfChair = numberOfChair;
        this.check = check;
        this.note = note;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public int getNumberOfChair() {
        return numberOfChair;
    }

    public int getCheck() {
        return check;
    }

    public String getNote() {
        return note;
    }
}
