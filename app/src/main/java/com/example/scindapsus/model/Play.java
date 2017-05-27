package com.example.scindapsus.model;

import java.util.List;

/**
 * Created by ej on 5/3/2017.
 */

public class Play {
    private long id = 0;

    private List<Scene> scenes ;

    private String name = "";

    private String stillUrl = "";

    private String extract = "";

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }
    public void setScenes(List<Scene> scenes){
        this.scenes = scenes;
    }
    public List<Scene> getScenes(){
        return this.scenes;
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
