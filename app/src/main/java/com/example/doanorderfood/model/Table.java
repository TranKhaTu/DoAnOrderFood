package com.example.doanorderfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table implements Parcelable {
    private int number;
    private int position;
    private int numberOfChair;
    private int check;
    private String note;
    private ArrayList<ItemMenu> arrayList;
    public Table() {
    }

    protected Table(Parcel in) {
        number = in.readInt();
        position = in.readInt();
        numberOfChair = in.readInt();
        check = in.readInt();
        note = in.readString();
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel in) {
            return new Table(in);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNumberOfChair(int numberOfChair) {
        this.numberOfChair = numberOfChair;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<ItemMenu> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ItemMenu> arrayList) {
        this.arrayList = arrayList;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(position);
        dest.writeInt(numberOfChair);
        dest.writeInt(check);
        dest.writeString(note);
    }
}
