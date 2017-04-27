package com.example.scindapsus.model.http;

/**
 * Created by ej on 4/27/2017.
 */

public class Sort {

    private String direction;

    private String property;

    private boolean ignoreCase;

    private String nullHandling;

    private boolean ascending;

    private boolean descending;

    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return this.direction;
    }
    public void setProperty(String property){
        this.property = property;
    }
    public String getProperty(){
        return this.property;
    }
    public void setIgnoreCase(boolean ignoreCase){
        this.ignoreCase = ignoreCase;
    }
    public boolean getIgnoreCase(){
        return this.ignoreCase;
    }
    public void setNullHandling(String nullHandling){
        this.nullHandling = nullHandling;
    }
    public String getNullHandling(){
        return this.nullHandling;
    }
    public void setAscending(boolean ascending){
        this.ascending = ascending;
    }
    public boolean getAscending(){
        return this.ascending;
    }
    public void setDescending(boolean descending){
        this.descending = descending;
    }
    public boolean getDescending(){
        return this.descending;
    }
}