package com.example.scindapsus.model.http;

import com.example.scindapsus.model.PlayInfo;

import java.util.List;

/**
 * Created by ej on 4/27/2017.
 */

public class PageResult<T> {

    private T content ;

    private boolean last;

    private int totalPages;

    private int totalElements;

    private int size;

    private int number;

    private List<Sort> sort ;

    private int numberOfElements;

    private boolean first;

    public void setContent(T content){
        this.content = content;
    }
    public T getContent(){
        return this.content;
    }
    public void setLast(boolean last){
        this.last = last;
    }
    public boolean getLast(){
        return this.last;
    }
    public void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }
    public int getTotalPages(){
        return this.totalPages;
    }
    public void setTotalElements(int totalElements){
        this.totalElements = totalElements;
    }
    public int getTotalElements(){
        return this.totalElements;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return this.number;
    }
    public void setSort(List<Sort> sort){
        this.sort = sort;
    }
    public List<Sort> getSort(){
        return this.sort;
    }
    public void setNumberOfElements(int numberOfElements){
        this.numberOfElements = numberOfElements;
    }
    public int getNumberOfElements(){
        return this.numberOfElements;
    }
    public void setFirst(boolean first){
        this.first = first;
    }
    public boolean getFirst(){
        return this.first;
    }
}
