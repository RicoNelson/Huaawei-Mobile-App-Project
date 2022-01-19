package com.class_project.model;

public class Mall {
    private String name;
    private int img;
    private String loc;

    public Mall(String name, String loc, int img) {
        this.name = name;
        this.loc = loc;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }

    public int getImg() {
        return img;
    }
}
