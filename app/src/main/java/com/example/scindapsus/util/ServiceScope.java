package com.example.scindapsus.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ej on 3/9/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceScope {
}
