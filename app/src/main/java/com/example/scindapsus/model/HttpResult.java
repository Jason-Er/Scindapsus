package com.example.scindapsus.model;

/**
 * Created by ej on 2/28/2017.
 */

public class HttpResult<T> {
    private int code;// status code
    private String message;
    private T subjects;
}
