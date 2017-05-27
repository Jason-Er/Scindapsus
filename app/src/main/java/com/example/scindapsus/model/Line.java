package com.example.scindapsus.model;

/**
 * Created by ej on 5/3/2017.
 */

public class Line {
    private long id;

    private String audioURL;
    private String text;

    private long ordinal;

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }
    public void setAudioURL(String audioURL){
        this.audioURL = audioURL;
    }
    public String getAudioURL(){
        return this.audioURL;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setOrdinal(long ordinal){
        this.ordinal = ordinal;
    }
    public long getOrdinal(){
        return this.ordinal;
    }
}
