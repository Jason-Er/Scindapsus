package com.example.scindapsus.model;

import android.graphics.Bitmap;

/**
 * Created by ej on 4/28/2017.
 */

public class PlayInfoEx {
    private int id;

    private String name;

    private Bitmap still;

    private String extract;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getStill() {
        return still;
    }

    public void setStill(Bitmap still) {
        this.still = still;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }
}
