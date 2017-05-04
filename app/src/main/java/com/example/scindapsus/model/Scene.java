package com.example.scindapsus.model;

import java.util.List;

/**
 * Created by ej on 5/3/2017.
 */

public class Scene {
    private int id;

    private List<Line> lines ;

    private String name;

    private int ordinal;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setLines(List<Line> lines){
        this.lines = lines;
    }
    public List<Line> getLines(){
        return this.lines;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setOrdinal(int ordinal){
        this.ordinal = ordinal;
    }
    public int getOrdinal(){
        return this.ordinal;
    }
}
