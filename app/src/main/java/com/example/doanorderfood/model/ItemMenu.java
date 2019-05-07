package com.example.doanorderfood.model;

import java.io.Serializable;

public class ItemMenu implements Serializable {
    private String group;
    private String name;
    private String price;
    private String unit;
    private String check;
    private String image;
    private int count = 0;

    public ItemMenu() {
    }

    public ItemMenu(String group, String name, String price, String unit, String check, String image,int count) {
        this.group = group;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.check = check;
        this.image = image;
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public String getCheck() {
        return check;
    }

    public String getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
