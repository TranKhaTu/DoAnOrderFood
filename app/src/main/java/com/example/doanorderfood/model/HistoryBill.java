package com.example.doanorderfood.model;

import java.util.ArrayList;

public class HistoryBill {
    private String idBill;
    private int table;
    private int people;
    private String total;
    private ArrayList<ItemMenu> arrayList;
    private String time;

    public HistoryBill(String idBill, int table, int people, String total, String time) {
        this.idBill = idBill;
        this.table = table;
        this.people = people;
        this.total = total;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getIdBill() {
        return idBill;
    }

    public int getTable() {
        return table;
    }

    public int getPeople() {
        return people;
    }

    public String getTotal() {
        return total;
    }

    public ArrayList<ItemMenu> getArrayList() {
        return arrayList;
    }
}
