package com.class_project.model;

import java.io.Serializable;

public class TripPlan implements Serializable {
    private String name;
    private int img;
    private String loc;

    public TripPlan(String name, String loc, int img) {
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

    @Override
    public boolean equals(Object obj) {
        return (this == obj);
    }
}
