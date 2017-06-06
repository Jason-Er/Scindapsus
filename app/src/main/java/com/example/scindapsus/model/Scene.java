package com.example.scindapsus.model;

import java.util.List;

/**
 * Created by ej on 5/3/2017.
 */

public class Scene {
    private long id;
    private List<Line> lines ;
    private String name;
    private long ordinal;
    private long playId;

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
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
    public void setOrdinal(long ordinal){
        this.ordinal = ordinal;
    }
    public long getOrdinal(){
        return this.ordinal;
    }
    public long getPlayId() {
        return playId;
    }
    public void setPlayId(long playId) {
        this.playId = playId;
    }
}
