package com.example.scindapsus.model;

/**
 * Created by ej on 4/25/2017.
 */

public class PlayInfo {

    private int id;

    private String name;

    private String stillUrl;

    private String extract;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setStillUrl(String stillUrl){
        this.stillUrl = stillUrl;
    }
    public String getStillUrl(){
        return this.stillUrl;
    }
    public void setExtract(String extract){
        this.extract = extract;
    }
    public String getExtract(){
        return this.extract;
    }
}
