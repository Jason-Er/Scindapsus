package com.example.scindapsus.model;

/**
 * Created by ej on 5/3/2017.
 */

public class Line {
    private int id;

    private String audioURL;

    private int ordinal;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setAudioURL(String audioURL){
        this.audioURL = audioURL;
    }
    public String getAudioURL(){
        return this.audioURL;
    }
    public void setOrdinal(int ordinal){
        this.ordinal = ordinal;
    }
    public int getOrdinal(){
        return this.ordinal;
    }
}
