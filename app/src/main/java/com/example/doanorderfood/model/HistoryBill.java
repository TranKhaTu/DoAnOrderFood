package com.example.doanorderfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HistoryBill implements Parcelable {
    private String idBill;
    private int table;
    private int people;
    private String total;
    private ArrayList<ItemMenu> arrayList;
    private String time;
    private String type;
    public HistoryBill() {
    }

    public HistoryBill(String idBill, int table, int people, String total, String time) {
        this.idBill = idBill;
        this.table = table;
        this.people = people;
        this.total = total;
        this.time = time;
    }

    protected HistoryBill(Parcel in) {
        idBill = in.readString();
        table = in.readInt();
        people = in.readInt();
        total = in.readString();
        time = in.readString();
        type = in.readString();
    }

    public static final Creator<HistoryBill> CREATOR = new Creator<HistoryBill>() {
        @Override
        public HistoryBill createFromParcel(Parcel in) {
            return new HistoryBill(in);
        }

        @Override
        public HistoryBill[] newArray(int size) {
            return new HistoryBill[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setArrayList(ArrayList<ItemMenu> arrayList) {
        this.arrayList = arrayList;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBill);
        dest.writeInt(table);
        dest.writeInt(people);
        dest.writeString(total);
        dest.writeString(time);
        dest.writeString(type);
    }
}
