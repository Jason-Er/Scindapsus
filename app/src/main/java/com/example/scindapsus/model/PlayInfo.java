package com.example.scindapsus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ej on 4/25/2017.
 */

public class PlayInfo implements Parcelable {

    private int id;

    private String name;

    private String stillUrl;

    private String extract;

    public PlayInfo() {

    }

    protected PlayInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        stillUrl = in.readString();
        extract = in.readString();
    }

    public static final Creator<PlayInfo> CREATOR = new Creator<PlayInfo>() {
        @Override
        public PlayInfo createFromParcel(Parcel in) {
            return new PlayInfo(in);
        }

        @Override
        public PlayInfo[] newArray(int size) {
            return new PlayInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(stillUrl);
        parcel.writeString(extract);
    }

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
